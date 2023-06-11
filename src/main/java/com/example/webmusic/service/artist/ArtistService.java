package com.example.webmusic.service.artist;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.artist.out.OutApiArtistDetail;
import com.example.webmusic.controller.artist.out.OutApiGetArtistDescribe;
import com.example.webmusic.controller.artist.in.InApi_getSelectedArtist;
import com.example.webmusic.controller.artist.out.OutApi_getRecommendArtist;
import com.example.webmusic.controller.artist.out.OutApi_getSelectedArtist;
import com.example.webmusic.models.artist.Artist;

public interface ArtistService extends IService<Artist> {
    //歌手详情页
    void artistDetail(long artistId, OutApiArtistDetail outApiSingerDetail);

    void getArtistDescribe(InApiGetArtistDescribe inApiGetArtistDescribe, OutApiGetArtistDescribe outApiGetArtistDescribe);
    void getArtistByCon(InApi_getSelectedArtist in, OutApi_getSelectedArtist out);

    void getRandomArtist(OutApi_getRecommendArtist out);
    void getArtistDescribe(long artistId, OutApiGetArtistDescribe outApiGetArtistDescribe);
}
