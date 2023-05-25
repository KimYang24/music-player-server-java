package com.example.webmusic.models.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "admin_user")
public class AdminUser {
    @TableId(type = IdType.AUTO)
    private Integer admin_id;
    private String admin_name;
    private String admin_nicname;
    private String admin_password;
}
