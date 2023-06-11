package com.example.webmusic.controller.artist;

import com.example.webmusic.controller.album.out.OutApiGetAlbumByArtist;
//import com.example.webmusic.controller.artist.in.InApiArtistDetail;
// com.example.webmusic.controller.artist.in.InApiGetArtistDescribe;
import com.example.webmusic.controller.artist.in.InApi_getSelectedArtist;
import com.example.webmusic.controller.artist.in.InApi_modifyArtist;
import com.example.webmusic.controller.artist.out.*;
import com.example.webmusic.controller.song.out.OutApiGetSongsByArtist;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.artist.Artist;
import com.example.webmusic.models.user.User;
import com.example.webmusic.service.album.AlbumService;
import com.example.webmusic.service.artist.ArtistService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController

public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @Autowired
    private AlbumService albumService;
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
//    public OutApiGetArtistDescribe outApiGetArtistDescribe(@RequestParam InApiGetArtistDescribe inApiGetArtistDescribe){
//        OutApiGetArtistDescribe outApiGetArtistDescribe=new OutApiGetArtistDescribe();
//        artistService.getArtistDescribe(inApiGetArtistDescribe,outApiGetArtistDescribe);
//        return outApiGetArtistDescribe;
//    }

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

    //获得特定页歌手信息
    @GetMapping("/admin/getPageArtist")
    public OutApi_getPageArtist getPageArtist(@RequestParam(value = "currentPage") int pagenum, @RequestParam(value = "pageSize") int pagesize) {
        OutApi_getPageArtist out = new OutApi_getPageArtist();
        artistService.getPageArtist(pagenum, pagesize,out);
        return out;
    }

    //获得特定歌手信息
    @GetMapping("/admin/getArtist")
    public OutApi_getArtist getArtist(@RequestParam("name") String name) {
        OutApi_getArtist out = new OutApi_getArtist();
        artistService.getArtist(name,out);
        return out;
    }

    //添加歌手
    @PostMapping("/admin/addArtist")
    public OutApi_addArtist addArtist(@RequestBody Artist artist) {
        OutApi_addArtist out =  new OutApi_addArtist();
        artistService.addArtist(artist, out);
        return out;
    }

    //删除歌手
    @GetMapping("/admin/deleteArtist")
    public Map<String,Object> deleteArtist(@RequestParam(value = "artistId") int artistID) {
        int code = artistService.deleteArtist(artistID);
        Map<String,Object> m = new HashMap<>();
        m.put("code",code);
        return m;
    }

    //修改歌手基础信息
    @PostMapping("/admin/modifyAritst")
    public Map<String,Object> modifyArtist(@RequestBody InApi_modifyArtist in) {
        Artist artist = in.getData();
        int code = artistService.modifyArtist(artist);
        Map<String,Object> m = new HashMap<>();
        m.put("code",code);
        return m;
    }

    //根据专辑获取歌手
    @GetMapping("/admin/getArtistByAlbum")
    public OutApi_getArtistByAlbum getArtistByAlbum(@RequestParam("albumId") int albumID) {
        OutApi_getArtistByAlbum out = new OutApi_getArtistByAlbum();
        Album album = albumService.getById(albumID);
        if(album == null) {
            out.setCode(300);
            return out;
        }
        long artistID = album.getArtist_id();
        Artist artist = artistService.getById(artistID);
        if(artist == null) {
            out.setCode(300);
            return out;
        }
        out.setData(artist);
        out.setCode(200);
        return out;
    }
}
