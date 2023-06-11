package com.example.webmusic.controller.user.out.AdminUser;

import com.example.webmusic.models.user.AdminUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApi_getAllAdminUserInfo {
    int code;
    List<AdminUser> data;
    long totals;

}
