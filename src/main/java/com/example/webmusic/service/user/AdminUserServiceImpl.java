package com.example.webmusic.service.user;

import com.example.webmusic.controller.user.in.AdminUser.InApi_addAdminUserInfo;
import com.example.webmusic.controller.user.in.AdminUser.InApi_adminLogin;
import com.example.webmusic.controller.user.in.AdminUser.InApi_editAdminUserInfo;
import com.example.webmusic.controller.user.out.AdminUser.*;
import com.example.webmusic.mapper.user.AdminUserMapper;
import com.example.webmusic.models.user.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService  {

    @Autowired
    AdminUserMapper adminUserMapper;

    @Override
    public void adminLogin(InApi_adminLogin in, OutApi_adminLogin out){

    }

    @Override
    public void getAllAdminUserInfo(OutApi_getAllAdminUserInfo out){

    }

    @Override
    public void getAdminUserInfo(String adminname, OutApi_getAdminUserInfo out){

    }

    @Override
    public void addAdminUserInfo(InApi_addAdminUserInfo in, OutApi_addAdminUserInfo out){

    }

    @Override
    public void deleteAdminUserInfo(String adminname, OutApi_deleteAdminUserInfo out){

    }

    @Override
    public void editAdminUserInfo(InApi_editAdminUserInfo in, OutApi_editAdminUserInfo out){

    }
}
