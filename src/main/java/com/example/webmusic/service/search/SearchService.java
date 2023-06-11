package com.example.webmusic.service.search;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.search.In.InApiSearchByKeyword;
import com.example.webmusic.controller.search.out.*;
import com.example.webmusic.models.artist.Artist;

public interface SearchService{
    public void searchArtistsByKeyword(InApiSearchByKeyword inApiSearchByKeyword, OutApiSearchArtists outApiSearchArtists);
    public void searchAlbumsByKeyword(InApiSearchByKeyword inApiSearchByKeyword, OutApiSearchAlbums outApiSearchAlbums);
    public void searchSongsByKeyword(InApiSearchByKeyword inApiSearchByKeyword, OutApiSearchSongs outApiSearchSongs);
    public void searchHot(OutApiSearchHot outApiSearchHot);
    public void searchHotByKeyWord(String keyword, OutApiHotByKeyword outApiHotByKeyword);
}
