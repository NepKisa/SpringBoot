package com.neptune.springboot02webadmin.bean;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Student {
    private Long id;
    private String name;
    private Integer age;
}
