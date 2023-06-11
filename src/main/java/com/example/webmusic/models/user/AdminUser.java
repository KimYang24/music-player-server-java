package com.example.webmusic.models.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@TableName(value = "admin_user")
public class AdminUser {
    @TableId(type = IdType.AUTO)
    @JsonProperty("adminId")
    private Integer admin_id;
    @JsonProperty("adminName")
    private String adminname;
    private String password;
}
