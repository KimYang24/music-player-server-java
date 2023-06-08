package com.example.webmusic.models.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "adminUser")
public class AdminUser {
    @TableId(type = IdType.AUTO)
    private Integer admin_id;
    private String adminname;
    private String password;
}
