package com.example.webmusic.service.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webmusic.controller.user.in.User.*;
import com.example.webmusic.controller.user.out.User.*;
import com.example.webmusic.mapper.user.UserMapper;
import com.example.webmusic.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

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

    @Override
    public void getAllUserInfo(int pagenum, int pagesize, OutApi_getAllUserInfo out) {
        Page<User> page = new Page<>(pagenum, pagesize);
        // 执行分页查询，使用 IPage<User> 接收分页结果
        IPage<User> userPage = userMapper.selectPage(page, null);
        List<User> userlist = userPage.getRecords();
        long totals = userPage.getTotal();
        out.setData(userlist);
        out.setCode(200);
        out.setTotals(totals);
    }

    @Override
    public void getUserInfo(String name,OutApi_getUserInfo out) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.like("username",name).or().like("nickname",name);
        List<User> userlist = userMapper.selectList(qw);
        if (userlist.size() == 0)
            out.setCode(300);
        else
            out.setCode(200);
        out.setData(userlist);
    }

    @Override
    public int modifyUserInfo(User user){
        int code;
        int ok = userMapper.updateById(user);
        if (ok == 1)
            code = 200;
        else
            code = 300;
        return code;
    }

    @Override
    public int deleteUserInfo(int userID) {
        int code;
        int ok = userMapper.deleteById(userID);
        if (ok == 1)
            code = 200;
        else
            code = 300;
        return code;
    }
}
