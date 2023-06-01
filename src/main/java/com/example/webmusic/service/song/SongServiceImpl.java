package com.example.webmusic.service.song;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.mapper.song.SongMapper;
import com.example.webmusic.models.song.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService{

    @Autowired
    private SongMapper songMapper;

    //添加一首歌曲
    public boolean addSingleSong(Song song){

        return true;
    }


}
