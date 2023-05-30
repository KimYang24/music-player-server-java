package com.example.webmusic.service.song;

import com.example.webmusic.mapper.song.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddSong {
    @Autowired
    private SongMapper songMapper;

    public boolean addSong(){

        return true;
    }
}
