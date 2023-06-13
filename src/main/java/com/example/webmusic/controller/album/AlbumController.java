package com.example.webmusic.controller.album;

import com.example.webmusic.controller.album.out.OutApiGetRecommendAlbum;
import com.example.webmusic.controller.user.out.User.OutApi_addUserInfo;
import com.example.webmusic.controller.user.out.User.OutApi_getAllUserInfo;
import com.example.webmusic.frontend_model.AlbumFront;
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
    public OutApiAlbumDetail albumDetail(@RequestParam (value = "albumId")long albumId){
        OutApiAlbumDetail outApiAlbumDetail =new OutApiAlbumDetail();
        albumService.albumDetail(albumId,outApiAlbumDetail);
        return outApiAlbumDetail;
    }

    //分页获取歌手专辑
    @GetMapping(value = "/detail/artist/album")
    public OutApiGetPageAlbumByArtist outApiGetPageAlbumByArtist(@RequestParam ("artistId")long artistId,
                                                                 @RequestParam("currentPage")long currentPage,
                                                                 @RequestParam("pageSize")long pageSize){
        OutApiGetPageAlbumByArtist outApiGetPageAlbumByArtist=new OutApiGetPageAlbumByArtist();
        albumService.getPageAlbumByArtist(artistId,currentPage,pageSize,outApiGetPageAlbumByArtist);
        return outApiGetPageAlbumByArtist;
    }

    //获取特定专辑
    @GetMapping(value = "/admin/theAlbum")
    public OutApiGetAlbum outApigetAlbum(@RequestParam("albumName")String albumName){
        OutApiGetAlbum outApiGetAlbum=new OutApiGetAlbum();
        albumService.getAlbum(albumName,outApiGetAlbum);
        return outApiGetAlbum;
    }

    //获得特定页专辑信息
    @GetMapping("/admin/pageAllAlbum")
    public OutApiGetPageAlbum getPageAlbum(@RequestParam ("currentPage")long currentPage,
                                           @RequestParam("pageSize")long pageSize){
        OutApiGetPageAlbum outApiGetPageAlbum = new OutApiGetPageAlbum();
        albumService.getPageAlbum(currentPage,pageSize,outApiGetPageAlbum);
        return outApiGetPageAlbum;
    }

    //管理端：根据歌手获取专辑
    @GetMapping(value = "/admin/getAlbumByArtist")
    public  OutApiGetAlbumByArtist outApiGetAlbumByArtist(@RequestParam ("artistId")long artistId){
        OutApiGetAlbumByArtist outApiGetAlbumByArtist=new OutApiGetAlbumByArtist();
        albumService.getAlbumByArtist(artistId,outApiGetAlbumByArtist);
        return outApiGetAlbumByArtist;
    }

    //添加专辑
    @PostMapping(value = "/admin/addAlbum")
    public OutApiAddAlbum addAlbum(@RequestBody AlbumFront album) {
        OutApiAddAlbum outApiAddAlbum=new OutApiAddAlbum();
        albumService.addAlbum(album, outApiAddAlbum);
        return outApiAddAlbum;
    }

    //删除专辑
    @GetMapping(value = "/admin/deleteAlbum")
    public  OutApiDeleteAlbum deleteAlbum(@RequestParam ("albumId")long albumId){
        OutApiDeleteAlbum outApiDeleteAlbum=new OutApiDeleteAlbum();
        albumService.deleteAlbum(albumId,outApiDeleteAlbum);
        return outApiDeleteAlbum;
    }

    //修改专辑信息
    @PostMapping(value = "/admin/modifyAlbum")
    public OutApiModifyAlbum modifyAlbum(@RequestBody InApiModifyAlbumInfo inApiModifyAlbumInfo){
        OutApiModifyAlbum outApiModifyAlbum=new OutApiModifyAlbum();
        albumService.modifyAlbumInfo(inApiModifyAlbumInfo.getData(),outApiModifyAlbum);
        return outApiModifyAlbum;
    }
}
