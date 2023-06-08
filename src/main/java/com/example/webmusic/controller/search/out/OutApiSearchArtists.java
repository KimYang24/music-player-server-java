package com.example.webmusic.controller.search.out;


import com.example.webmusic.frontend_model.ArtistFront;

import java.util.List;


@lombok.Data
public class OutApiSearchArtists {
    private List<ArtistFront> artistFronts;
    private long code;
    private long pageTotal;
}