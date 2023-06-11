package com.example.webmusic.controller.album;

import com.example.webmusic.controller.album.out.OutApiGetRecommendAlbum;
import com.example.webmusic.controller.user.out.User.OutApi_addUserInfo;
import com.example.webmusic.controller.user.out.User.OutApi_getAllUserInfo;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.user.User;
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
    public OutApiGetPageAlbumByArtist outApiGetPageAlbumByArtist(@RequestParam InApiGetPageAlbumByArtist inApiGetPageAlbumByArtist){
        OutApiGetPageAlbumByArtist outApiGetPageAlbumByArtist=new OutApiGetPageAlbumByArtist();
        albumService.getPageAlbumByArtist(inApiGetPageAlbumByArtist,outApiGetPageAlbumByArtist);
        return outApiGetPageAlbumByArtist;
    }

    //获得特定页专辑信息
    @GetMapping("/admin/pageAllAlbum")
    public OutApiGetPageAlbum getPageAlbum(@RequestParam InApiGetPageAlbum inApiGetPageAlbum){
        OutApiGetPageAlbum outApiGetPageAlbum = new OutApiGetPageAlbum();
        albumService.getPageAlbum(inApiGetPageAlbum,outApiGetPageAlbum);
        return outApiGetPageAlbum;
    }

    //管理端：根据歌手获取专辑
    @GetMapping(value = "/admin/getAlbumByArtist")
    public  OutApiGetAlbumByArtist outApiGetAlbumByArtist(@RequestParam InApiGetAlbumByArtist inApiGetAlbumByArtist){
        OutApiGetAlbumByArtist outApiGetAlbumByArtist=new OutApiGetAlbumByArtist();
        albumService.getAlbumByArtist(inApiGetAlbumByArtist,outApiGetAlbumByArtist);
        return outApiGetAlbumByArtist;
    }

    //添加专辑
    @PostMapping(value = "/admin/addAlbum")
    public OutApiAddAlbum addAlbum(@RequestBody Album album) {
        OutApiAddAlbum outApiAddAlbum=new OutApiAddAlbum();
        albumService.addAlbum(album, outApiAddAlbum);
        return outApiAddAlbum;
    }

    //删除专辑
    @GetMapping(value = "/admin/deleteAlbum")
    public  OutApiDeleteAlbum deleteAlbum(@RequestParam InApiDeleteAlbum inApiDeleteAlbum){
        OutApiDeleteAlbum outApiDeleteAlbum=new OutApiDeleteAlbum();
        albumService.deleteAlbum(inApiDeleteAlbum,outApiDeleteAlbum);
        return outApiDeleteAlbum;
    }

    //修改专辑信息
    @PostMapping(value = "/admin/modifyAlbum")
    public OutApiModifyAlbum modifyAlbum(@RequestBody InApiModifyAlbumInfo inApiModifyAlbumInfo){
        OutApiModifyAlbum outApiModifyAlbum=new OutApiModifyAlbum();
        albumService.modifyAlbumInfo(inApiModifyAlbumInfo,outApiModifyAlbum);
        return outApiModifyAlbum;
    }
}
