package com.example.webmusic.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webmusic.models.user.User;
import org.apache.ibatis.annotations.Mapper;

//继承BaseMapper这个类才可以不用写sql语句
//继承了insert、deleteById、deleteByMap等方法，就不用写sql了！
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
