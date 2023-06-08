package com.example.webmusic.controller.search.out;


import com.example.webmusic.models.song.Song;
import lombok.Data;

import java.util.List;

@Data
public class OutApiSearchSongs {
    private long code;
    private long pageTotal;
    private List<Song> songFronts;
}
