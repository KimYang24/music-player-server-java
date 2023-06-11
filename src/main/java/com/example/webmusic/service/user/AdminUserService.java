package com.example.webmusic.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.user.in.AdminUser.*;
import com.example.webmusic.controller.user.out.AdminUser.*;
import com.example.webmusic.models.user.AdminUser;
public interface AdminUserService extends IService<AdminUser> {
    void adminLogin(String adminname, String password, OutApi_adminLogin out);
    void getAdminProfile(int adminID,OutApi_getAdminProfile out);
    void getAllAdminUserInfo(int currentpage, int pagesize, OutApi_getAllAdminUserInfo out);
    void getAdminUserInfo(String adminname,OutApi_getAdminUserInfo out);
    void addAdminUserInfo(AdminUser adminuser,OutApi_addAdminUserInfo out);
    int deleteAdminUserInfo(long adminID);
    int modifyAdminUserInfo(AdminUser adminuser);
}
