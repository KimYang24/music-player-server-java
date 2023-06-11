package com.example.webmusic.service.playlist;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.playlist.OutApiGetHotPlaylist;
import com.example.webmusic.controller.playlist.in.InApiGetHotPlaylist;
import com.example.webmusic.controller.playlist.out.OutApi_getOnePlayList;
import com.example.webmusic.models.playlist.PlayList;

public interface PlayListService extends IService<PlayList> {
    void getOnePlayListById(int playListID, OutApi_getOnePlayList out);
    void getHotPlaylist(int currentPage,int pageSize, OutApiGetHotPlaylist outApiGetHotPlaylist);
}
