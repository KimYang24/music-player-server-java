package com.example.webmusic.controller.user.in.AdminUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InApi_adminLogin {
    private String adminname;
    private String password;
}
