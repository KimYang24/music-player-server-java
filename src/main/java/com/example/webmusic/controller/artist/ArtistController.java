package com.example.webmusic.controller.artist;

import com.example.webmusic.controller.artist.out.OutApiArtistDetail;
import com.example.webmusic.service.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ArtistController {
    @Autowired
    private ArtistService artistService;
    // TODO 不要没测试过就推上来!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //获取歌手详情页
    @GetMapping(value = "/detail/artist")
    public OutApiArtistDetail artistDetail(@RequestParam(value = "artistId") long artistId){
        OutApiArtistDetail outApiArtistDetail=new OutApiArtistDetail();
        artistService.artistDetail(artistId,outApiArtistDetail);
        return outApiArtistDetail;
    }
    // TODO 不要没测试过就推上来!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //获取歌手描述
//    @GetMapping(value = "/detail/artist/describe")
//    public OutApiGetArtistDescribe getArtistDescribe(@RequestParam(value = "artistId") long artistId ){
//        OutApiGetArtistDescribe outApiGetArtistDescribe=new OutApiGetArtistDescribe();
//        artistService.getArtistDescribe(artistId,outApiGetArtistDescribe);
//        return outApiGetArtistDescribe;
//    }
}
