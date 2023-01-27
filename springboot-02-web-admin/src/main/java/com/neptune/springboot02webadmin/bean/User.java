package com.neptune.springboot02webadmin.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
//@TableName("user_tbl")
public class User {

    /**
     * 所有属性都应该在数据库中
     */
    @TableField(exist = false)  //当前属性表中不存在
    private String userName;
    @TableField(exist = false)
    private String password;


    //以下是数据库字段
    private Long id;
    private String name;
    private Integer age;
    private String email;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
