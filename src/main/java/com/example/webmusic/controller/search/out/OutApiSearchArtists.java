package com.example.webmusic.controller.search.out;


import com.example.webmusic.models.artist.Artist;

import java.util.List;


@lombok.Data
public class OutApiSearchArtists {
    private List<Artist> artistFronts;
    private long code;
    private long pageTotal;
}