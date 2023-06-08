package com.example.webmusic.service.album;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.album.in.*;
import com.example.webmusic.controller.album.out.OutApiAddAlbum;
import com.example.webmusic.controller.album.out.OutApiDeleteAlbum;
import com.example.webmusic.controller.album.out.OutApiGetAlbum;
import com.example.webmusic.controller.album.out.OutApiModiAlbumInfo;
import com.example.webmusic.controller.song.out.OutApiGetPageSong;
import com.example.webmusic.mapper.album.AlbumMapper;
import com.example.webmusic.models.album.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    //获取特定页专辑
    public void getPageAlbum(InApiGetPageAlbum inApiGetPageAlbum, OutApiGetPageSong outApiGetPageSong){

    }

    @Override
    //获取特定专辑
    public void getAlbum(InApiGetAlbum inApiGetAlbum, OutApiGetAlbum outApiGetAlbum){

    }

    @Override
    //添加专辑
    public void addAlbum(InApiAddAlbum inApiAddAlbum, OutApiAddAlbum outApiAddAlbum){

    }

    @Override
    //修改专辑信息
    public void modifyAlbumInfo(InApiModifyAlbumInfo inApiModifyAlbumInfo, OutApiModiAlbumInfo outApiModiAlbumInfo){

    }

    @Override
    //删除专辑
    public void deleteAlbum(InApiDeleteAlbum inApiDeleteAlbum, OutApiDeleteAlbum outApiDeleteAlbum){

    }

}
