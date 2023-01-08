package com.neptune.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Consumer;

@SpringBootApplication
public class Springboot02WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02WebApplication.class, args);
//        Consumer consumer = System.out::println;
//        consumer.accept("string");
    }

}
