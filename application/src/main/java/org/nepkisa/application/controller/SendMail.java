package org.nepkisa.application.controller;

import org.nepkisa.application.service.WrapperMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nepkisa
 * @date 2023/6/21 23:18
 */
@RestController
public class SendMail {

    @Autowired
    WrapperMail wrapperMail;


    //未发送完成可以调用接口断点续传
    @RequestMapping("/send")
    public void send() {
        wrapperMail.send();
    }
}
