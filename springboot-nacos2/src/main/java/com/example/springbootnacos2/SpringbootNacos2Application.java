package com.example.springbootnacos2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient //自动注册到nacos，默认为true
@SpringBootApplication
@EnableFeignClients //开启 feign 的客户端
public class SpringbootNacos2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNacos2Application.class, args);
    }



}
