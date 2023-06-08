package com.example.webmusic.controller.song.in;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class InApiGetSongByAlbum {
    private int albumId;
}
