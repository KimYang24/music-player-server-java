package com.example.webmusic.service.album;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.album.in.*;
import com.example.webmusic.controller.album.out.*;
import com.example.webmusic.controller.song.out.OutApiGetPageSong;
import com.example.webmusic.models.album.Album;

public interface AlbumService extends IService<Album> {
    //获取特定页专辑
    void getPageAlbum(long currentPage,long pageSize, OutApiGetPageAlbum outApiGetPageAlbum);

    //获取特定专辑
    void getAlbum(String albumName, OutApiGetAlbum outApiGetAlbum);

    //添加专辑
    void addAlbum(Album album, OutApiAddAlbum outApiAddAlbum);

    //修改专辑信息
    void modifyAlbumInfo(InApiModifyAlbumInfo inApiModifyAlbumInfo, OutApiModifyAlbum outApiModifyAlbumInfo);

    //删除专辑
    void deleteAlbum(long albumId, OutApiDeleteAlbum outApiDeleteAlbum);

    //歌曲推荐；随机返回歌曲
    void getRandomAlbum (OutApiGetRecommendAlbum out);

    //专辑详情页
    void albumDetail(long albumId, OutApiAlbumDetail outApiAlbumDetail);

    //分页获取歌手专辑
    void getPageAlbumByArtist(long artistId,long currentPage,long pageSize,OutApiGetPageAlbumByArtist outApiGetPageAlbumByArtist);

    //根据歌手获取专辑
    void getAlbumByArtist(long artistId, OutApiGetAlbumByArtist outApiGetAlbumByArtist);
}
