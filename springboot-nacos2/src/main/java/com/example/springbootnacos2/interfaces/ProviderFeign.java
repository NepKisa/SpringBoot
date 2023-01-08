package com.example.springbootnacos2.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//添加一个 feign 的接口，注意和提供者一致
@FeignClient(value = "nacos-payment-provider")
public interface ProviderFeign {

    //远程调用provider接口
    @GetMapping(value = "/payment/nacos/{id}")
    String getPayment(@PathVariable("id") Integer id);
}