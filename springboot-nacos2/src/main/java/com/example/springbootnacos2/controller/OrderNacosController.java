package com.example.springbootnacos2.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id) {
        return restTemplate.getForObject(serverURL + "/payment/nacos/" + id, String.class);
    }

    //注入服务发现组件
    @Autowired
    private DiscoveryClient discoveryClient;

    //http://localhost:8003/discovery?serviceId=nacos-payment-provider
    @GetMapping("discovery")
    public String discoveryService(String serviceId) {
        // 根据实例名称拿到实例集合
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        // 从实例集合列表中获取一个实例对象
        ServiceInstance serviceInstance = instances.get(0);
        System.out.println(serviceInstance.getHost() + ":" + serviceInstance.getPort());
        return serviceInstance.getHost() + ":" + serviceInstance.getPort() ;
    }
}
