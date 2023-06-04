package com.example.webmusic.controller.user.in.AdminUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InApi_editAdminUserInfo {
    private int admin_id;
    private String adminname;
    private String password;
}
