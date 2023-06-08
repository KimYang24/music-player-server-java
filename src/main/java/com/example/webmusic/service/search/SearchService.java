package com.example.webmusic.service.search;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.search.In.InApiSearchByKeyword;
import com.example.webmusic.controller.search.out.OutApiSearchSongs;
import com.example.webmusic.models.artist.Artist;

public interface SearchService{
    public void searchSongsByKeyword(InApiSearchByKeyword inApiSearchByKeyword, OutApiSearchSongs outApiSearchSongs);
}
