package com.example.webmusic.controller.album;

import com.example.webmusic.controller.album.out.OutApiGetRecommendAlbum;
import com.example.webmusic.service.album.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.webmusic.controller.album.in.*;
import com.example.webmusic.controller.album.out.*;
import org.springframework.web.bind.annotation.*;


@RestController
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    //专辑推荐，随机返回30张专辑
    @GetMapping("/discover/album")
    public OutApiGetRecommendAlbum getRecommendAlbum() {
        OutApiGetRecommendAlbum out = new OutApiGetRecommendAlbum();
        albumService.getRandomAlbum(out);
        return out;
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
