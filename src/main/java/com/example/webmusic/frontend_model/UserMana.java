package com.example.webmusic.frontend_model;

/**
 * 管理前端用户模型
 */
@lombok.Data
public class UserMana {
    private Long age;
    private String email;
    private String gender;
    private String nickname;
    private String password;
    private String phone;
    /**
     * 用户头像
     */
    private String picUrl;
    private long userId;
    private String username;
}
