package com.example.webmusic.service.song;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.song.in.*;
import com.example.webmusic.controller.song.out.*;
import com.example.webmusic.frontend_model.SongFront;
import com.example.webmusic.models.song.Song;


public interface SongService extends IService<Song> {
    //添加歌曲
    void addSong(SongFront songFront, OutApiAddSong outApiAddSong);

    //获得特定歌曲
    void getSong(String songName, OutApiGetSong outApiGetSong);

    //获得特定页歌曲
    void getPageSong(InApiGetPageSong inApiGetPageSong, OutApiGetPageSong outApiGetPageSong);

    //修改歌曲信息
    void modifySongInfo(SongFront songFront, OutApiModiSongInfo outApiModiSongInfo);

    //根据歌手获取歌
    void getSongBySinger(long artistId, OutApiGetSongBySinger outApiGetSongBySinger);

    //根据专辑获取歌
    void getSongByAlbum(long albumId, OutApiGetSongByAlbum outApiGetSongByAlbum);

    //删除歌曲
    long deleteSong(long songId);

    //根据songId获取单首歌曲
    void getOneSongById(long songID,OutApiGetOneSong out);
    void getSongListByArtist(long artistId,String order,long currentPage,long pageSize,OutApiGetSongsByArtist outApiGetSongsByArtist);

    //歌曲推荐；随机返回歌曲
    void getRandomSong (OutApiGetRecommendSong out);
}
