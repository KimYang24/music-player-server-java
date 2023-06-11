package com.example.webmusic.controller.song;

import com.example.webmusic.controller.song.in.*;
import com.example.webmusic.controller.song.out.*;
import com.example.webmusic.controller.user.in.User.InApiRegister;
import com.example.webmusic.controller.user.out.User.OutApiRegister;
import com.example.webmusic.frontend_model.SongFront;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.service.song.SongService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class SongController {
    @Autowired
    private SongService songService;

    //添加歌曲
    @PostMapping(value = "/admin/addSong")
    public OutApiAddSong addSong(@RequestBody SongFront songFront){
        OutApiAddSong outApiAddSong = new OutApiAddSong();
        songService.addSong(songFront,outApiAddSong);
        return outApiAddSong;
    }

    //获得特定歌曲
    @GetMapping(value = "/admin/getSongByName")
    public OutApiGetSong getSong(@RequestParam(value = "songName") String songName){
        OutApiGetSong outApiGetSong=new OutApiGetSong();
        songService.getSong(songName,outApiGetSong);
        return outApiGetSong;
    }

    //获得特定页歌曲
    @GetMapping(value = "/admin/getPageSong")
    public OutApiGetPageSong getPageSong(@RequestParam(value = "pageSize")long pageSize,
                                         @RequestParam(value = "currentPage")long currentPage){
        OutApiGetPageSong outApiGetPageSong=new OutApiGetPageSong();
        InApiGetPageSong in = InApiGetPageSong.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();
        songService.getPageSong(in,outApiGetPageSong);
        return outApiGetPageSong;
    }

    //修改歌曲信息
    @PostMapping(value = "/admin/modifySong")
    public OutApiModiSongInfo modifySongInfo(@RequestBody InApiModiSong inApiModiSong){
        OutApiModiSongInfo outApiModiSongInfo=new OutApiModiSongInfo();
        songService.modifySongInfo(inApiModiSong.getData(),outApiModiSongInfo);
        return outApiModiSongInfo;
    }

    //根据歌手ID获取歌曲，管理端
    @GetMapping(value = "/admin/getSongByArtist")
    public OutApiGetSongBySinger outApiGetSongBySinger(@RequestParam(value = "artistId")long artistId){
        OutApiGetSongBySinger outApiGetSongBySinger=new OutApiGetSongBySinger();
        songService.getSongBySinger(artistId,outApiGetSongBySinger);
        return outApiGetSongBySinger;
    }

    //根据专辑获取歌曲
    @GetMapping(value = "/admin/getSongByAlbum")
    public OutApiGetSongByAlbum outApiGetSongByAlbum(@RequestParam(value = "albumId")long albumId){
        OutApiGetSongByAlbum outApiGetSongByAlbum=new OutApiGetSongByAlbum();
        songService.getSongByAlbum(albumId,outApiGetSongByAlbum);
        return outApiGetSongByAlbum;
    }

    //删除歌曲
    @GetMapping(value = "/admin/deleteSong")
    public Map<String, Object> outApiDeleteSong(@RequestParam(value = "songId")long songId){
        long code = songService.deleteSong(songId);
        Map map = new HashMap<>();
        map.put("code",code);
        return map;
    }

    //根据songid获得一首歌曲
    @GetMapping("/detail/song")
    public OutApiGetOneSong getOneSong(@RequestParam("songId") long songID) {
        OutApiGetOneSong out = new OutApiGetOneSong();
        songService.getOneSongById(songID,out);
        return out;
    }
    //分页获取歌手歌曲
    @GetMapping(value = "/detail/artist/songs")
    public OutApiGetSongsByArtist outApiGetSongsByArtist(@RequestParam InApiGetSongsByArtist inApiGetSongsByArtist){
        OutApiGetSongsByArtist outApiGetSongsByArtist=new OutApiGetSongsByArtist();
        songService.getSongListByArtist(inApiGetSongsByArtist,outApiGetSongsByArtist);
        return outApiGetSongsByArtist;
    }

    //歌曲推荐
    @GetMapping("/discover/song")
    public OutApiGetRecommendSong getRecommendSong() {
        OutApiGetRecommendSong out = new OutApiGetRecommendSong();
        songService.getRandomSong(out);
        return out;
    }
}
