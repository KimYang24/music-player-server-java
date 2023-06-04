package com.example.webmusic.service.user;

import com.baomidou.mybatisplus.extension.service.IService;

import com.example.webmusic.controller.user.in.User.*;
import com.example.webmusic.controller.user.out.User.*;
import com.example.webmusic.models.user.User;

public interface UserService extends IService<User> {
    void userLogin(InApiLogin inApiLogin, OutApiLogin outApiLogin);
    void userRegister(InApiRegister inApiRegister, OutApiRegister outApiRegister);
    void getAllUserInfo(int page, int pagesize, OutApi_getAllUserInfo out);
    void getUserInfo(String name,OutApi_getUserInfo out);
    //void addUserInfo(InApi_addUserInfo in,OutApi_addUserInfo out);
    int modifyUserInfo(User user);
    int deleteUserInfo(int userID);
}
