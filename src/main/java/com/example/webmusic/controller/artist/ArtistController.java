package com.example.webmusic.controller.artist;

import com.example.webmusic.controller.album.out.OutApiGetAlbumByArtist;
import com.example.webmusic.controller.artist.in.InApiArtistDetail;
import com.example.webmusic.controller.artist.in.InApiGetArtistDescribe;
import com.example.webmusic.controller.artist.in.InApi_getSelectedArtist;
import com.example.webmusic.controller.artist.out.OutApiArtistDetail;
import com.example.webmusic.controller.artist.out.OutApiGetArtistDescribe;
import com.example.webmusic.controller.artist.out.OutApi_getRecommendArtist;
import com.example.webmusic.controller.artist.out.OutApi_getSelectedArtist;
import com.example.webmusic.controller.song.out.OutApiGetSongsByArtist;
import com.example.webmusic.service.artist.ArtistService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ArtistController {
    @Autowired
    private ArtistService artistService;

    //获取歌手详情页
    @GetMapping(value = "/detail/artist")
    public OutApiArtistDetail artistDetail(@RequestParam InApiArtistDetail inApiArtistDetail){
        OutApiArtistDetail outApiArtistDetail=new OutApiArtistDetail();
        artistService.artistDetail(inApiArtistDetail,outApiArtistDetail);
        return outApiArtistDetail;
    }

    //获取歌手描述
    @GetMapping(value = "/detail/artist/describe")
    public OutApiGetArtistDescribe outApiGetArtistDescribe(@RequestParam InApiGetArtistDescribe inApiGetArtistDescribe){
        OutApiGetArtistDescribe outApiGetArtistDescribe=new OutApiGetArtistDescribe();
        artistService.getArtistDescribe(inApiGetArtistDescribe,outApiGetArtistDescribe);
        return outApiGetArtistDescribe;
    }

    //获取对应筛选条件歌手
    @GetMapping("/library/artist")
    public OutApi_getSelectedArtist getSelectedArtist(@ModelAttribute InApi_getSelectedArtist in) {
        OutApi_getSelectedArtist out = new OutApi_getSelectedArtist();
        artistService.getArtistByCon(in,out);
        return out;
    }

    //歌手推荐，随机返回30个歌手
    @GetMapping("/discover/artist")
    public OutApi_getRecommendArtist getRecommendArtist() {
        OutApi_getRecommendArtist out = new OutApi_getRecommendArtist();
        artistService.getRandomArtist(out);
        return out;
    }
}
