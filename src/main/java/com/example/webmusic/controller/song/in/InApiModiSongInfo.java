package com.example.webmusic.controller.song.in;

import com.example.webmusic.models.song.Song;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class InApiModiSongInfo {
    private List<Song> data;
}
