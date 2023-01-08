package com.neptune.springboot.bean;

import lombok.*;

@NoArgsConstructor//无参构造器
//@AllArgsConstructor//全参构造器
@Data//setter and getter
@ToString//toString
@EqualsAndHashCode//hashcode
public class User {
    private String name;

    private int age;

    private Pet pet;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
