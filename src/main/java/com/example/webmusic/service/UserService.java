package com.example.webmusic.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.example.webmusic.mapper.user.UserMapper;
import com.example.webmusic.models.user.User;

@Service
public class UserService {
    private UserMapper usm;

    public boolean UserLogin(String username, String password){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);
        User user = usm.selectOne(wrapper);
        if(user != null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean UserRegister(String username, String password){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);
        User user = usm.selectOne(wrapper);
        if(user == null){
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            return usm.insert(user) == 1;
        }
        else{
            return false;
        }
    }
}
