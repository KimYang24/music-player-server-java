package com.example.webmusic.service.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.user.in.InApiLogin;
import com.example.webmusic.controller.user.in.InApiRegister;
import com.example.webmusic.controller.user.out.OutApiLogin;
import com.example.webmusic.controller.user.out.OutApiRegister;
import com.example.webmusic.mapper.user.UserMapper;
import com.example.webmusic.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void userLogin(InApiLogin inApiLogin, OutApiLogin outApiLogin) {
        User user = User.builder()
                .phone(inApiLogin.getPhone())
                .password(inApiLogin.getPassword())
                .build();

        // 创建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", user.getPhone());
        // 查询数据库是否存在符合条件的记录
        User _user = getOne(queryWrapper);
        //用户不存在
        if(_user==null){
            outApiLogin.setCode(300);
        } else{
            //登陆成功
            if(_user.getPassword().equals(user.getPassword())){
                outApiLogin.setCode(200);
                outApiLogin.setToken("123123");
                outApiLogin.setUserId(_user.getUser_id());
            }
        }
    }

    @Override
    public void userRegister(InApiRegister inApiRegister, OutApiRegister outApiRegister) {
        User user = User.builder()
                .phone(inApiRegister.getPhone())
                .username(inApiRegister.getUsername())
                .nickname(inApiRegister.getNickname())
                .email(inApiRegister.getEmail())
                .password(inApiRegister.getPassword())
                .build();

        // 创建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", user.getPhone());
        // 查询数据库是否存在符合条件的记录
        User _user = getOne(queryWrapper);
        //手机号不存在
        if(_user==null){
            outApiRegister.setCode(200);
            save(user);
            long id = user.getUser_id();
            updateById(user);
            outApiRegister.setToken("123123213");
            outApiRegister.setUserId(id);
        } else{
            //手机号已存在
            outApiRegister.setCode(300);
        }
    }


}
