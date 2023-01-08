package com.neptune.springboot.controller;

import com.neptune.springboot.bean.Car;
import com.neptune.springboot.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    //自动注入，将容器中的car对象注入（赋值）到这里
    @Autowired
    Car car;


    @RequestMapping("/car")
    public Car car(){
        return car;
    }

    @RequestMapping("/hello2")
    public String hello() {
        log.info("请求进来了");
        return "Hello,Spring Boot 2! 你好";
    }

}