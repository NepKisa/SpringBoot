package org.nepkisa.application.service;

import lombok.extern.slf4j.Slf4j;
import org.nepkisa.application.configuration.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Nepkisa
 * @date 2023/6/21 23:51
 */
@Slf4j
@Service
public class WrapperMail {

    @Autowired
    MailClient mailClient;

    /**
     * 发送邮件的方法
     */
    public void send() {
        File dir = new File(mailClient.getAttachement());
        String resume = mailClient.resume();
        //判断断点位置
        boolean flag = true;
        //走断点续传（文件发送完成后记录的是最后一个文件位置，此时断点续传失效）
        if (mailClient.requireBreakpointResume() && dir.getName().equals(resume.split("><")[0])) {
            for (File file : dir.listFiles()) {
                if (flag) {
                    //断点文件名
                    String breakFileName = mailClient.resume().split("><")[1];
                    //判断文件名是否是断点的文件，是则不再进行判断，不是则继续判断
                    flag = !file.getName().equals(breakFileName);
                } else {
                    //实际发送文件
                    actuallySend(dir.getName(), file);
                }
            }
        } else {
            for (File file : dir.listFiles()) {
                actuallySend(dir.getName(), file);
            }
        }
        log.info("The directory "+dir.getName()+" >>>>>>>> has been sent ending");
    }

    private void actuallySend(String dir, File file) {
        mailClient.sendMail(mailClient.getReceiver(), file);
        log.info(file.getName() + " ======> send successfully!");
        //记录断点（记录的是最后一个成功发送的邮件）
        mailClient.breakpoint(dir + "><" + file.getName());
        //发送完毕等待
        try {
            Thread.sleep(mailClient.getSleep());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}