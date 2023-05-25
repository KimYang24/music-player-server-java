package com.example.webmusic.mapper.user;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webmusic.models.user.AdminUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {
}
