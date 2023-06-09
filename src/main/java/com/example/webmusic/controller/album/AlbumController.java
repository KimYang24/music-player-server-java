package com.example.webmusic.controller.album;

import com.example.webmusic.controller.user.in.User.InApiRegister;
import com.example.webmusic.controller.user.out.User.OutApiRegister;
import com.example.webmusic.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import com.example.webmusic.controller.album.in.*;
import com.example.webmusic.controller.album.out.*;
import com.example.webmusic.service.album.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    //根据歌手获取专辑
    @GetMapping(value = "/detail/artist/describe")
    public OutApiGetAlbumByArtist getAlbumByArtist(@RequestParam InApiGetAlbumByArtist inApiGetAlbumByArtist){
        OutApiGetAlbumByArtist outApiGetAlbumByArtist=new OutApiGetAlbumByArtist();
        albumService.getAlbumByArtist(inApiGetAlbumByArtist,outApiGetAlbumByArtist);
        return outApiGetAlbumByArtist;
    }

    //专辑详情页信息
    @GetMapping(value = "/detail/album")
    public OutApiAlbumDetail albumDetail(@RequestParam InApiAlbumDetail inApiAlbumDetail){
        OutApiAlbumDetail outApiAlbumDetail =new OutApiAlbumDetail();
        albumService.albumDetail(inApiAlbumDetail,outApiAlbumDetail);
        return outApiAlbumDetail;
    }

    //分页获取歌手专辑
    @GetMapping(value = "/detail/artist/album")
    public OutApiGetAlbumByArtist outApiGetAlbumByArtist(@RequestParam InApiGetAlbumByArtist inApiGetAlbumByArtist){
        OutApiGetAlbumByArtist outApiGetAlbumByArtist=new OutApiGetAlbumByArtist();
        albumService.getAlbumByArtist(inApiGetAlbumByArtist,outApiGetAlbumByArtist);
        return outApiGetAlbumByArtist;
    }

}
