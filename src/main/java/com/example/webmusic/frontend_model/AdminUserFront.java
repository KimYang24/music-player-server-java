package com.example.webmusic.frontend_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理前端管理者模型
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserFront {
    private long adminId;
    private String adminName;
    private String password;
}