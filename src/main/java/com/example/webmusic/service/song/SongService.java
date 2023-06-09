package com.example.webmusic.service.song;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.song.in.*;
import com.example.webmusic.controller.song.in.InApiAddSong;
import com.example.webmusic.controller.song.out.*;
import com.example.webmusic.models.song.Song;
import io.swagger.models.auth.In;


public interface SongService extends IService<Song> {
    //添加歌曲
    void addSong(InApiAddSong inApiAddSong,OutApiAddSong outApiAddSong);

    //获得特定歌曲
    void getSong(InApiGetSong inApiGetSong, OutApiGetSong outApiGetSong);

    //获得特定页歌曲
    void getPageSong(InApiGetPageSong inApiGetPageSong, OutApiGetPageSong outApiGetPageSong);

    //修改歌曲信息
    void modifySongInfo(Song song, OutApiModiSongInfo outApiModiSongInfo);

    //根据歌手获取歌
    void getSongBySinger(InApiGetSongBySinger inApiGetSongBySinger, OutApiGetSongBySinger outApiGetSongBySinger);

    //根据专辑获取歌
    void getSongByAlbum(InApiGetSongByAlbum inApiGetSongByAlbum, OutApiGetSongByAlbum outApiGetSongByAlbum);

    //删除歌曲
    void deleteSong(InApiDeleteSong inApiDeleteSong,OutApiDeleteSong outApiDeleteSong);

    //根据songId获取单首歌曲
    void getOneSongById(int songID,OutApiGetOneSong out);
}
