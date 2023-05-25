package com.example.webmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Time;

public class Album {
    public int id;//专辑id
    public String name;//专辑名
    public String singer;//歌手
    public Time time;//发行时间
}
