package com.example.webmusic.controller.song.out;

import com.example.webmusic.models.song.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiGetSongsByArtist {
    private long code;
    /**
     * 有多少页数据
     */
    private long pageTotal;
    private List<Song> songs;
}
