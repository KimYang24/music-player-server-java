package com.example.helloworld.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.helloworld.entity.User;
import com.example.helloworld.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class UserController {


    @PostMapping(value = "/upload")
    public void uploadUser(String nickname, MultipartFile file){
        System.out.println("nickname = " + nickname);
        System.out.println("file.getName() = " + file.getName());
        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
    }

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user")
    public List<User> query(){
        List<User> ans = userMapper.selectList(null);
        System.out.println(ans);
        return ans;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //条件查询
    @GetMapping("/user/find")
    public String queryById(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name","lpt");
        List<User> ans =userMapper.selectList(queryWrapper);
        System.out.println(ans);
        return "success";
    }

    //分页查询
    @GetMapping("/user/findByPage")
    public IPage<User> findByPage(){
        Page<User> page = new Page<User>(0,2);
        IPage<User> iPage = userMapper.selectPage(page,null);
        return iPage;
    }
}
