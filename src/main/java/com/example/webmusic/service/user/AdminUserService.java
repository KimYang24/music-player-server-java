package com.example.webmusic.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.user.in.AdminUser.*;
import com.example.webmusic.controller.user.out.AdminUser.*;
import com.example.webmusic.models.user.AdminUser;
public interface AdminUserService extends IService<AdminUser> {
    void adminLogin(InApi_adminLogin in, OutApi_adminLogin out);
    void getAllAdminUserInfo(OutApi_getAllAdminUserInfo out);
    void getAdminUserInfo(String adminname,OutApi_getAdminUserInfo out);
    void addAdminUserInfo(InApi_addAdminUserInfo in,OutApi_addAdminUserInfo out);
    void deleteAdminUserInfo(String adminname,OutApi_deleteAdminUserInfo out);
    void editAdminUserInfo(InApi_editAdminUserInfo in, OutApi_editAdminUserInfo out);
}
