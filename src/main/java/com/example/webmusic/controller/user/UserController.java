package com.example.webmusic.controller.user;


import com.example.webmusic.controller.user.in.User.*;
import com.example.webmusic.controller.user.out.User.*;
import com.example.webmusic.models.user.User;
import com.example.webmusic.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    //获得特定页用户信息
    @GetMapping("/User/pageAllInfo")
    public OutApi_getAllUserInfo allUserInfo(@RequestParam(value = "currentPage") int pagenum, @RequestParam(value = "pageSize") int pagesize){
        OutApi_getAllUserInfo out = new OutApi_getAllUserInfo();
        userService.getAllUserInfo(pagenum,pagesize,out);
        return out;
    }

    //获取特定用户信息
    @GetMapping("/User/theInfo")
    public OutApi_getUserInfo userInfo(@RequestParam(value = "name") String name) {
        OutApi_getUserInfo out = new OutApi_getUserInfo();
        userService.getUserInfo(name,out);
        return out;
    }

    //添加用户信息
    @PostMapping("/User/addInfo")
    public OutApi_addUserInfo addUserInfo(@RequestBody User user) {
        OutApi_addUserInfo out = new OutApi_addUserInfo();
        userService.addUserInfo(user, out);
        return out;
    }

    //修改用户信息
    @PostMapping("/User/modifyInfo")
    public Map<String,Object> modifyUser(@RequestBody User user) {
        int code = userService.modifyUserInfo(user);
        Map<String,Object> m = new HashMap<>();
        m.put("code",code);
        return m;
    }

    //删除用户信息
    @GetMapping("/User/deleteInfo")
    public Map<String,Object> deleteUser(@RequestParam(value = "userId") int userID) {
        int code = userService.deleteUserInfo(userID);
        Map<String,Object> m = new HashMap<>();
        m.put("code",code);
        return m;
    }

    //上传用户照片
    @PostMapping("/User/modifyUploadPic")
    public Map<String,Object> uploadUserPic(@RequestParam(value = "file") MultipartFile file,@RequestAttribute(value = "ID") int userID) {
        System.out.println("进入controller成功");
        int code = userService.updateUserPic(10, file);
        Map<String,Object> m = new HashMap<>();
        m.put("code",code);
        return m;
    }

    //获取用户个人信息（用户端）
    @GetMapping("/user/profile")
    public OutApiGetUserProfile getUserProfile(@RequestParam(value = "userId") long userId){
        OutApiGetUserProfile outApiGetUserProfile = new OutApiGetUserProfile();
        userService.getUserProfile(userId,outApiGetUserProfile);
        return outApiGetUserProfile;
    }
}
