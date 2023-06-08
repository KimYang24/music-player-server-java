package com.example.webmusic.controller.search.out;

import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.artist.Artist;
import com.example.webmusic.models.song.Song;

import java.util.List;

@lombok.Data
public class OutApiHotByKeyword {
    private List<Album> albumFronts;
    private List<Artist> artistFronts;
    private long code;
    private List<Song> songFronts;
}