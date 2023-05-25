package com.example.webmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Time;

public class Song {
    public int id;//歌曲id
    public String name;//歌名
    public String singer;//歌手
    public String album;//专辑
    public String style;//风格
    public Time time;//发行时间

}
