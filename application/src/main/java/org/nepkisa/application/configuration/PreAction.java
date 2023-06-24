package org.nepkisa.application.configuration;

import jakarta.annotation.PreDestroy;
import org.nepkisa.application.controller.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Nepkisa
 * @date 2023/6/22 0:40
 */
@Component
public class PreAction implements ApplicationRunner {

    @Autowired
    SendMail sendMail;

    @Autowired
    MailClient mailClient;


    /**
     * 应用启动后自动发送一次
     *
     * @param args incoming application arguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        mailClient.clean();
        sendMail.send();
    }

    /**
     * 应用关闭时
     */
    @PreDestroy
    public void destory() {
    }
}
