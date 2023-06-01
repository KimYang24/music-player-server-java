package com.example.webmusic.controller.user;


import com.example.webmusic.controller.user.in.InApiRegister;
import com.example.webmusic.controller.user.out.OutApiLogin;
import com.example.webmusic.controller.user.out.OutApiRegister;
import com.example.webmusic.service.user.UserService;
import com.example.webmusic.controller.user.in.InApiLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //用户注册
    @PostMapping(value = "/register")
    public OutApiRegister userRegister(@RequestBody InApiRegister inApiRegister){
        OutApiRegister outApiRegister = new OutApiRegister();
        userService.userRegister(inApiRegister,outApiRegister);
        return outApiRegister;
    }

    //用户登录
    @GetMapping(value="/login/cellphone")
    public OutApiLogin userLogin(@RequestParam(value = "phone") String phone,@RequestParam(value = "password")String password){
        InApiLogin inApiLogin = InApiLogin.builder()
                .phone(phone)
                .password(password)
                .build();
        OutApiLogin outApiLogin = new OutApiLogin();
        userService.userLogin(inApiLogin,outApiLogin);
        return outApiLogin;
    }

}
