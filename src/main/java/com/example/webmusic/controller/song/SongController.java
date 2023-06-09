package com.example.webmusic.controller.song;

import com.example.webmusic.controller.song.in.*;
import com.example.webmusic.controller.song.out.*;
import com.example.webmusic.controller.user.in.User.InApiRegister;
import com.example.webmusic.controller.user.out.User.OutApiRegister;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.service.song.SongService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class SongController {
    @Autowired
    private SongService songService;

    //添加歌曲
    @PostMapping(value = "/admin/addSong")
    public OutApiAddSong addSong(@RequestBody InApiAddSong inApiAddSong){
        OutApiAddSong outApiAddSong = new OutApiAddSong();
        songService.addSong(inApiAddSong,outApiAddSong);
        return outApiAddSong;
    }

    //获得特定歌曲
    @GetMapping(value = "/admin/getSongByName")
    public OutApiGetSong getSong(@RequestParam InApiGetSong inApiGetSong){
        System.out.println("inApiGetSong = " + inApiGetSong);
        OutApiGetSong outApiGetSong=new OutApiGetSong();
        songService.getSong(inApiGetSong,outApiGetSong);
        return outApiGetSong;
    }

    //获得特定页歌曲
    @GetMapping(value = "/admin/getPageSong")
    public OutApiGetPageSong getPageSong(@RequestParam InApiGetPageSong inApiGetPageSong){
        OutApiGetPageSong outApiGetPageSong=new OutApiGetPageSong();
        songService.getPageSong(inApiGetPageSong,outApiGetPageSong);
        return outApiGetPageSong;
    }

    //修改歌曲信息
    @PostMapping(value = "/admin/modifySong")
    public OutApiModiSongInfo modifySongInfo(@RequestBody Song song){
        OutApiModiSongInfo outApiModiSongInfo=new OutApiModiSongInfo();
        songService.modifySongInfo(song,outApiModiSongInfo);
        return outApiModiSongInfo;
    }

    //根据歌手获取歌
    @GetMapping(value = "/admin/getSongByArtist")
    public OutApiGetSongBySinger outApiGetSongBySinger(@RequestParam InApiGetSongBySinger inApiGetSongBySinger){
        OutApiGetSongBySinger outApiGetSongBySinger=new OutApiGetSongBySinger();
        songService.getSongBySinger(inApiGetSongBySinger,outApiGetSongBySinger);
        return outApiGetSongBySinger;
    }

    //根据专辑获取歌
    @GetMapping(value = "/admin/getSongByAlbum")
    public OutApiGetSongByAlbum outApiGetSongByAlbum(@RequestParam InApiGetSongByAlbum inApiGetSongByAlbum){
        OutApiGetSongByAlbum outApiGetSongByAlbum=new OutApiGetSongByAlbum();
        songService.getSongByAlbum(inApiGetSongByAlbum,outApiGetSongByAlbum);
        return outApiGetSongByAlbum;
    }

    //删除歌曲
    @GetMapping(value = "/admin/deleteSong")
    public OutApiDeleteSong outApiDeleteSong(@RequestParam InApiDeleteSong inApiDeleteSong){
        OutApiDeleteSong outApiDeleteSong=new OutApiDeleteSong();
        songService.deleteSong(inApiDeleteSong,outApiDeleteSong);
        return outApiDeleteSong;
    }

    //分页获取歌手歌曲
    @GetMapping(value = "/detail/artist/songs")
    public OutApiGetSongsByArtist outApiGetSongsByArtist(@RequestParam InApiGetSongsByArtist inApiGetSongsByArtist){
        OutApiGetSongsByArtist outApiGetSongsByArtist=new OutApiGetSongsByArtist();
        songService.getSongListByArtist(inApiGetSongsByArtist,outApiGetSongsByArtist);
        return outApiGetSongsByArtist;
    }
}
