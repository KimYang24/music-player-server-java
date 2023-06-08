package com.example.webmusic.controller.search.out;

import com.example.webmusic.frontend_model.AlbumFront;
import com.example.webmusic.frontend_model.ArtistFront;
import com.example.webmusic.frontend_model.SongFront;

import java.util.List;

@lombok.Data
public class OutApiHotByKeyword {
    private List<AlbumFront> albumFronts;
    private List<ArtistFront> artistFronts;
    private long code;
    private List<SongFront> songFronts;
}