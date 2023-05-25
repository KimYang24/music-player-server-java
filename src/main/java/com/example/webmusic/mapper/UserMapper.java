package com.example.webmusic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webmusic.models.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
//    @Select("select * from user")
//    public List<User> query();

}
