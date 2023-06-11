package com.example.webmusic.controller.user;

import com.example.webmusic.controller.user.out.AdminUser.*;
import com.example.webmusic.controller.user.out.User.OutApi_addUserInfo;
import com.example.webmusic.models.user.AdminUser;
import com.example.webmusic.models.user.User;
import com.example.webmusic.service.user.AdminUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private AdminUserService adminUserService;


    //管理员登录
    @GetMapping("/adminUser/login")
    public OutApi_adminLogin adminLogin (@RequestParam("adminName")String adminname,@RequestParam("password") String password) {
        OutApi_adminLogin out = new OutApi_adminLogin();
        adminUserService.adminLogin(adminname, password, out);
        return out;
    }

    //根据adminId获取管理员信息
    @GetMapping("/adminUser/profile")
    public OutApi_getAdminProfile getAdminProfile(@RequestParam("adminId") int adminID) {
        OutApi_getAdminProfile out = new OutApi_getAdminProfile();
        adminUserService.getAdminProfile(adminID,out);
        return out;
    }
    //获取特定页管理员信息
    @GetMapping("/adminUser/pageAllInfo")
    public OutApi_getAllAdminUserInfo getAllAdminUserInfo(@RequestParam("currentPage") int currentpage,@RequestParam("pageSize") int pagesize) {
        OutApi_getAllAdminUserInfo out = new OutApi_getAllAdminUserInfo();
        adminUserService.getAllAdminUserInfo(currentpage,pagesize,out);
        return out;
    }

    //获取特定管理员信息
    @GetMapping("/adminUser/theInfo")
    public OutApi_getAdminUserInfo getAdminUserInfo(@RequestParam("adminname") String adminname) {
        OutApi_getAdminUserInfo out = new OutApi_getAdminUserInfo();
        adminUserService.getAdminUserInfo(adminname,out);
        return out;
    }

    //修改管理员信息
    @PostMapping("/adminUser/modifyInfo")
    public Map<String,Object> modifyAdminUserInfo(@RequestBody AdminUser adminUser) {
        int code = adminUserService.modifyAdminUserInfo(adminUser);
        Map<String,Object> m = new HashMap<>();
        m.put("code",code);
        return m;
    }

    //添加管理员信息
    @PostMapping("/adminUser/addInfo")
    public OutApi_addAdminUserInfo addAdminUserInfo(@RequestBody AdminUser adminuser) {
        OutApi_addAdminUserInfo out = new OutApi_addAdminUserInfo();
        adminUserService.addAdminUserInfo(adminuser, out);
        return out;
    }

    //删除管理员信息
    @GetMapping("/adminUser/deleteInfo")
    public Map<String,Object> deleteAdminUser(@RequestParam(value = "adminId") long adminID) {
        int code = adminUserService.deleteAdminUserInfo(adminID);
        Map<String,Object> m = new HashMap<>();
        m.put("code",code);
        return m;
    }


}
