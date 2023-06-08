package com.example.webmusic.service.album;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.album.in.*;
import com.example.webmusic.controller.album.out.OutApiAddAlbum;
import com.example.webmusic.controller.album.out.OutApiDeleteAlbum;
import com.example.webmusic.controller.album.out.OutApiGetAlbum;
import com.example.webmusic.controller.album.out.OutApiModifyAlbum;
import com.example.webmusic.controller.song.out.OutApiDeleteSong;
import com.example.webmusic.controller.song.out.OutApiGetPageSong;
import com.example.webmusic.models.album.Album;

public interface AlbumService extends IService<Album> {
    //获取特定页专辑
    void getPageAlbum(InApiGetPageAlbum inApiGetPageAlbum, OutApiGetPageSong outApiGetPageSong);

    //获取特定专辑
    void getAlbum(InApiGetAlbum inApiGetAlbum, OutApiGetAlbum outApiGetAlbum);

    //添加专辑
    void addAlbum(InApiAddAlbum inApiAddAlbum, OutApiAddAlbum outApiAddAlbum);

    //修改专辑信息
    void modifyAlbumInfo(InApiModifyAlbumInfo inApiModifyAlbumInfo, OutApiModifyAlbum outApiModifyAlbumInfo);

    //删除专辑
    void deleteAlbum(InApiDeleteAlbum inApiDeleteAlbum, OutApiDeleteAlbum outApiDeleteAlbum);
}
