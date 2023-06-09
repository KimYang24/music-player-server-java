package com.example.webmusic.service.playlist;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.playlist.out.OutApi_getOnePlayList;
import com.example.webmusic.mapper.playlist.PlayListSongMapper;
import com.example.webmusic.models.playlist.PlayListSong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListSongServiceImpl extends ServiceImpl<PlayListSongMapper, PlayListSong> implements PlayListSongService {
    @Autowired
    private PlayListSongMapper playListSongMapper;

    @Override
    public List<PlayListSong> getSongIdByPlayListId(int playListId) {
        QueryWrapper<PlayListSong> qw = new QueryWrapper<>();
        qw.eq("playlist_id",playListId);
        return playListSongMapper.selectList(qw);
    }
}
