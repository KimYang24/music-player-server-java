package com.example.webmusic.controller.search;


import com.example.webmusic.controller.song.in.InApiGetSong;
import com.example.webmusic.controller.song.out.OutApiGetSong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    //未输入时显示热搜信息
    @GetMapping(value = "/search/hot")
    public void showHot(@RequestParam InApiGetSong inApiGetSong){

    }

    //动态搜索推荐
    @GetMapping(value = "/search/suggest")
    public void hotByKeyword(@RequestParam InApiGetSong inApiGetSong){

    }

    //关键词搜索歌曲
    @GetMapping(value = "/search/song")
    public void searchSongs(@RequestParam InApiGetSong inApiGetSong){

    }


    //关键词搜索歌手
    @GetMapping(value = "/search/artist")
    public void searchArtists(@RequestParam InApiGetSong inApiGetSong){

    }


    //关键词搜索专辑
    @GetMapping(value = "/search/album")
    public void searchAlbums(@RequestParam InApiGetSong inApiGetSong){

    }
}
