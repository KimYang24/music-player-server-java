package com.example.webmusic.service.log;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.log.in.InApiSaveLog;
import com.example.webmusic.mapper.log.LogMapper;
import com.example.webmusic.models.log.Log;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.models.user.User;
import com.example.webmusic.service.song.SongService;
import com.example.webmusic.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogServiceImppl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private SongService songService;
    @Autowired
    private UserService userService;

    @Override
    public long savePlayLog(InApiSaveLog inApiSaveLog) {
        Log log = Log.builder()
                .user_id(inApiSaveLog.getUserId())
                .song_id(inApiSaveLog.getSongId())
                .time(new Date().toString())
                .type(2)
                .build();
        User user = userService.getOne(new QueryWrapper<User>().eq("userId",inApiSaveLog.getUserId()));
        Song song =songService.getOne(new QueryWrapper<Song>().eq("song_id",inApiSaveLog.getSongId()));
        log.setSongname(song.getName());
        log.setUsername(user.getUsername());
        if (save(log)){
            return 200;
        }else{
            return 300;
        }
    }

    @Override
    public long saveDownloadLog(InApiSaveLog inApiSaveLog) {
        Log log = Log.builder()
                .user_id(inApiSaveLog.getUserId())
                .song_id(inApiSaveLog.getSongId())
                .time(new Date().toString())
                .type(3)
                .build();
        User user = userService.getOne(new QueryWrapper<User>().eq("userId",inApiSaveLog.getUserId()));
        Song song =songService.getOne(new QueryWrapper<Song>().eq("song_id",inApiSaveLog.getSongId()));
        log.setSongname(song.getName());
        log.setUsername(user.getUsername());
        if (save(log)){
            return 200;
        }else{
            return 300;
        }
    }

    @Override
    public long saveRegisterLog(User user) {
        Log log = Log.builder()
                .type(1)
                .username(user.getUsername())
                .user_id(user.getUser_id())
                .build();
        if (save(log)){
            return 200;
        }else{
            return 300;
        }

    }
}
