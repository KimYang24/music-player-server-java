package com.example.webmusic.service.playlist;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.playlist.out.OutApi_getOnePlayList;
import com.example.webmusic.models.playlist.PlayListSong;

import java.util.List;

public interface PlayListSongService extends IService<PlayListSong> {
    List<PlayListSong> getSongIdByPlayListId(int playListId);
}
