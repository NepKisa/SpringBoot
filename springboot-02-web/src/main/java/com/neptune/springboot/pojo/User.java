package com.neptune.springboot.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
