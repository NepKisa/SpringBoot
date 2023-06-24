package org.nepkisa.application.configuration;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author Nepkisa
 * @date 2023/6/21 23:16
 */
@Component
@Data
@Slf4j
public class MailClient {
    /**
     * 发送邮件的核心组件 Spring容器管理
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发件人:properties文件中已经配置，可直接使用
     */
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 收件人:properties文件中已经配置，可直接使用
     */
    @Value("${spring.mail.receiver}")
    private String receiver;

    /**
     * 附件:properties文件中已经配置，可直接使用
     */
    @Value("${spring.mail.attachement}")
    private String attachement;

    @Value("${spring.mail.resend}")
    private Boolean resend;

    @Value("${spring.mail.sleep}")
    private Long sleep;

    /**
     * 发送邮件的方法，外部调用
     *
     * @param to   发送目的地
     * @param file 附件
     */
    public void sendMail(String to, File file) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(file.getName());
            helper.addAttachment(file.getName(), file);
            // 支持html文本
            helper.setText(file.getName(), true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送附件时保存点文件的位置
     *
     * @param position 最后一个发送成功的附件名字
     */
    public void breakpoint(String position) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(attachement + File.separator + "breakpoint.log")) {
            fileOutputStream.write(position.getBytes());
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 应用恢复时读取断点文件内容
     *
     * @return
     */
    public String resume() {
        if (!requireBreakpointResume()) return null;
        try (FileInputStream fileInputStream = new FileInputStream(attachement + File.separator + "breakpoint.log")) {
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            return new String(bytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断是否需要进行断点续传（文件是否存在）
     *
     * @return
     */
    public Boolean requireBreakpointResume() {
        File file = new File(attachement + File.separator + "breakpoint.log");
        return file.exists();
    }

    /**
     * 删除记录断点的文件（设置重新发送resend时调用）
     */
    public void clean() {
        File file = new File(attachement + File.separator + "breakpoint.log");
        if (resend && file.exists()) {
            file.delete();
            log.warn("breakpoint is deleted: " + file.getAbsolutePath());
        } else {
            if (file.exists()) {
                log.info("breakpoint resuming using " + file.getAbsolutePath());
                return;
            }
        }
        log.info("first send, breakpoint will record at " + file.getAbsolutePath());
    }
}