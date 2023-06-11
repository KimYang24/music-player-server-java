package com.example.webmusic.controller.search.out;

import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.artist.Artist;
import com.example.webmusic.models.song.Song;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OutApiHotByKeyword {
    private List<Album> albums;
    private List<Artist> artists;
    private long code;
    private List<Song> songs;
}