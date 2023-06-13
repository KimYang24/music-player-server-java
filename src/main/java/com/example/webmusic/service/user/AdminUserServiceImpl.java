package com.example.webmusic.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webmusic.controller.user.in.AdminUser.InApi_addAdminUserInfo;
import com.example.webmusic.controller.user.in.AdminUser.InApi_adminLogin;
import com.example.webmusic.controller.user.in.AdminUser.InApi_editAdminUserInfo;
import com.example.webmusic.controller.user.out.AdminUser.*;
import com.example.webmusic.mapper.user.AdminUserMapper;
import com.example.webmusic.models.user.AdminUser;
import com.example.webmusic.models.user.User;
import com.example.webmusic.utils.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService  {

    @Autowired
    AdminUserMapper adminUserMapper;

    @Override
    public void adminLogin(String adminname, String password, OutApi_adminLogin out){
            QueryWrapper<AdminUser> qw = new QueryWrapper<>();
            qw.eq("adminname",adminname);
            AdminUser temp = adminUserMapper.selectOne(qw);
            if(temp == null){
                out.setCode(300);
                return;
            }
            else if (!(temp.getPassword().equals(password))) {
                out.setCode(301);
                return;
            }
            else {
                String token = JwtUtil.generateToken(temp.getAdmin_id());
                out.setToken(token);
                System.out.println("token = " + token);
                out.setAdminId(temp.getAdmin_id());
                out.setCode(200);
                return;
            }


    }

    @Override
    public void getAllAdminUserInfo(int currentpage, int pagesize, OutApi_getAllAdminUserInfo out){
        Page<AdminUser> page = new Page<>(currentpage, pagesize);
        // 执行分页查询，使用 IPage<AdminUser> 接收分页结果
        IPage<AdminUser> adminuserPage = adminUserMapper.selectPage(page, null);
        List<AdminUser> adminuserlist = adminuserPage.getRecords();
        long totals = adminuserPage.getTotal();
        out.setData(adminuserlist);
        out.setCode(200);
        out.setTotals(totals);
    }

    @Override
    public void getAdminUserInfo(String adminname, OutApi_getAdminUserInfo out){
        QueryWrapper<AdminUser> qw = new QueryWrapper<>();
        qw.like("adminname",adminname);
        List<AdminUser> adminuserlist = adminUserMapper.selectList(qw);
        if (adminuserlist.size() == 0)
            out.setCode(300);
        else
            out.setCode(200);
        out.setData(adminuserlist);
    }

    @Override
    public void addAdminUserInfo(AdminUser adminuser, OutApi_addAdminUserInfo out){
        QueryWrapper<AdminUser> qw = new QueryWrapper<>();
        qw.eq("adminname",adminuser.getAdminname());
        AdminUser temp = adminUserMapper.selectOne(qw);
        if(temp != null) {
            out.setCode(300);
            return;
        }
        int result = adminUserMapper.insert(adminuser);
        if(result == 0){
            out.setCode(300);
            return;
        }
        int totals = adminUserMapper.selectCount(null);
        int totalPages = totals % 10 == 0 ? totals / 10 : totals / 10 + 1;
        IPage<AdminUser> page = new Page<>(totalPages, 10);
        List<AdminUser> adminUserlist = adminUserMapper.selectPage(page, null).getRecords();
        out.setData(adminUserlist);
        out.setCode(200);
        out.setCurrentPage(totalPages);
        out.setTotals(totals);
    }

    @Override
    public int deleteAdminUserInfo(long adminID){
        int code;
        int ok = adminUserMapper.deleteById(adminID);
        if (ok == 1)
            code = 200;
        else
            code = 300;
        return code;
    }

    @Override
    public int modifyAdminUserInfo(AdminUser adminuser){
        int code;
        int ok = adminUserMapper.updateById(adminuser);
        if (ok == 1)
            code = 200;
        else
            code = 300;
        return code;
    }

    @Override
    public void getAdminProfile(int adminID,OutApi_getAdminProfile out) {
        AdminUser adminUser = adminUserMapper.selectById(adminID);
        if(adminUser == null){
            out.setCode(300);
            return;
        }
        out.setCode(200);
        out.setAdminName(adminUser.getAdminname());
        return;
    }
}
