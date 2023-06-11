package com.example.webmusic.controller.user.out.AdminUser;


import com.example.webmusic.models.user.AdminUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutApi_addAdminUserInfo {
    int code;
    List<AdminUser> data;
    long totals;
    long currentPage;
}
