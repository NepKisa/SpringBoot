package com.example.springbootnacos2.controller;

import com.example.springbootnacos2.interfaces.ProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
    @Autowired
    private ProviderFeign providerFeign;

    //测试远程调用
    @GetMapping("rpc")
    public String testRpc() {
        String id = providerFeign.getPayment(789);
        System.out.println(id);
        return id;
    }

}
