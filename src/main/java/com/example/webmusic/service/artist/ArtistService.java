package com.example.webmusic.service.artist;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.artist.in.InApiArtistDetail;
import com.example.webmusic.controller.artist.in.InApiGetArtistDescribe;
import com.example.webmusic.controller.artist.out.OutApiArtistDetail;
import com.example.webmusic.controller.artist.out.OutApiGetArtistDescribe;
import com.example.webmusic.models.artist.Artist;

public interface ArtistService extends IService<Artist> {
    //歌手详情页
    void artistDetail(InApiArtistDetail inApiSingerDetail, OutApiArtistDetail outApiSingerDetail);

    void getArtistDescribe(InApiGetArtistDescribe inApiGetArtistDescribe, OutApiGetArtistDescribe outApiGetArtistDescribe);
}
