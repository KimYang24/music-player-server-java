package com.example.webmusic.service.playlist;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.playlist.out.OutApiGetHotPlaylist;
import com.example.webmusic.controller.playlist.out.OutApi_getOnePlayList;
import com.example.webmusic.mapper.playlist.PlayListMapper;
import com.example.webmusic.models.playlist.PlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlayListServiceImpl extends ServiceImpl<PlayListMapper, PlayList> implements PlayListService {
    @Autowired
    private PlayListMapper playListMapper;
    @Override
    //获取歌单元数据
    public void getOnePlayListById(int playListID, OutApi_getOnePlayList out) {
        PlayList playList = playListMapper.selectById(playListID);
        if(playList == null){
            out.setCode(300);
        } else {
            out.setCode(200);
        }
        out.setPlaylist(playList);
    }

    public void getHotPlaylist(long currentPage,long pageSize, OutApiGetHotPlaylist outApiGetHotPlaylist){
        List<PlayList> playlists = playListMapper.selectList(new QueryWrapper<PlayList>()
                .orderByAsc("rand()")
                .last("LIMIT 5"));
        if(playlists == null){
            outApiGetHotPlaylist.setCode(300);
        } else {
            outApiGetHotPlaylist.setCode(200);
        }
        outApiGetHotPlaylist.setData(playlists);
        outApiGetHotPlaylist.setPageTotal(1);
    }
}
