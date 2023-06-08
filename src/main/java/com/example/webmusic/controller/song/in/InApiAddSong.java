package com.example.webmusic.controller.song.in;


import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class InApiAddSong {
    private String songName;
    private String artistName;
    private String albumName;
    private long duration;
    private String lyricUrl;
}
