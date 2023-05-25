package com.example.webmusic.controller.user;

import com.example.webmusic.models.user.AdminUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController{
    @PostMapping(value = "/admin/login")
    public void login(String admin_name,String admin_password){
        System.out.println("admin_name = " + admin_name);
        System.out.println("admin_password = " + admin_password);
        return ;
    }
}
