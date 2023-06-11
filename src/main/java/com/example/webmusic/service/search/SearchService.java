package com.example.webmusic.service.search;

import com.example.webmusic.controller.search.In.InApiSearchByKeyword;
import com.example.webmusic.controller.search.out.*;

public interface SearchService{
    void searchArtistsByKeyword(InApiSearchByKeyword inApiSearchByKeyword, OutApiSearchArtists outApiSearchArtists);
    void searchAlbumsByKeyword(InApiSearchByKeyword inApiSearchByKeyword, OutApiSearchAlbums outApiSearchAlbums);
    void searchSongsByKeyword(InApiSearchByKeyword inApiSearchByKeyword, OutApiSearchSongs outApiSearchSongs);
    void searchHot(OutApiSearchHot outApiSearchHot);
    void searchHotByKeyWord(String keyword, OutApiHotByKeyword outApiHotByKeyword);
}
