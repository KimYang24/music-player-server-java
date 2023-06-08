package com.example.webmusic.frontend_model;

/**
 * 管理前端管理者模型
 */
@lombok.Data
public class AdminUser {
    private long adminId;
    private String adminName;
    private String password;
}