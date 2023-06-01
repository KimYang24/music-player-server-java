package com.example.webmusic.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.user.in.InApiLogin;
import com.example.webmusic.controller.user.in.InApiRegister;
import com.example.webmusic.controller.user.out.OutApiLogin;
import com.example.webmusic.controller.user.out.OutApiRegister;
import com.example.webmusic.models.user.User;

public interface UserService extends IService<User> {
    void userLogin(InApiLogin inApiLogin, OutApiLogin outApiLogin);
    void userRegister(InApiRegister inApiRegister, OutApiRegister outApiRegister);
}
