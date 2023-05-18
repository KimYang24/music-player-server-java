package com.example.helloworld.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

//假如类名与表名不同
@TableName("user")
public class User {
    @TableId(type=IdType.AUTO)
    String id;
    String username;
    String password;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    //也可以多表查询




}
