package com.example.webmusic.controller.user;

import com.example.webmusic.models.user.AdminUser;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Data
class A{
    String admin_name;
    String admin_password;
}
@RestController
public class AdminController{


    @PostMapping(value = "/admin/login")
    public String login(@RequestBody A sssss){
        System.out.println("sssss.admin_name = " + sssss.admin_name);
        System.out.println("sssss.admin_password = " + sssss.admin_password);
        return sssss.admin_name;
    }
}
