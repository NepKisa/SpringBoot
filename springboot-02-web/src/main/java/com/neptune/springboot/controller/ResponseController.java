package com.neptune.springboot.controller;

import com.neptune.springboot.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResponseController {

    /**
     * 1、浏览器发请求直接返回xmL  [application/xml]   jacksonXmlConverter
     * 2、如果是ajax请求返回json  [application/json]  jacksonXmlConverter
     * 3、如果IDEA发请求，返回自定义协议数据    [application/neptune]   xxxConverter
     *
     * 步骤：
     * 1、添加自定义的Messageconverter进系统底层
     * 2、系统底层就会统计出所有Messageconverter能操作哪些类型
     * 3、客户端内容协商[neptune--->neptune]
     * @return
     */
    @ResponseBody
    @GetMapping("/newuser")
    public User user(){
        User user = new User();
        user.setAge(17);
        user.setName("ircus");
        return user;
    }
}
