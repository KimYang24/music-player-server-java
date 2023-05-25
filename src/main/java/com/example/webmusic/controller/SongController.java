package com.example.webmusic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webmusic.entity.User;
import com.example.webmusic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.webmusic.entity.Song;

import java.sql.Time;


public class SongController {
    Song song;
    {
        song = new Song();
    }

    public void addSong(String name, String artist, String album, Time time, String style){
        song.name=name;
        song.album=album;
        song.singer=artist;
        song.time=time;
        song.style=style;
    }

    public int addSong(){

    }

    public void modiSong(int id,String name, String artist, String album, Time time, String style){
        song.id=id;
        song.name=name;
        song.album=album;
        song.singer=artist;
        song.time=time;
        song.style=style;
    }

}
