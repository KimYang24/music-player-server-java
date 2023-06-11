package com.example.webmusic.service.artist;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.artist.out.*;
import com.example.webmusic.controller.artist.in.InApi_getSelectedArtist;
import com.example.webmusic.models.artist.Artist;

public interface ArtistService extends IService<Artist> {
    //歌手详情页
    void artistDetail(long artistId, OutApiArtistDetail outApiSingerDetail);

    void getArtistByCon(InApi_getSelectedArtist in, OutApi_getSelectedArtist out);

    void getRandomArtist(OutApi_getRecommendArtist out);

    void getArtistDescribe(long artistId, OutApiGetArtistDescribe outApiGetArtistDescribe);
    void getPageArtist(int pagenum, int pagesize, OutApi_getPageArtist out);
    void getArtist(String name,OutApi_getArtist out);
    void addArtist(Artist artist, OutApi_addArtist out);
    int deleteArtist(int artistID);
    int modifyArtist(Artist artist);

    void getArtistDescribe(int artistId, OutApiGetArtistDescribe outApiGetArtistDescribe);

}
