package com.example.webmusic.controller.song.in;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class InApiGetPageSong {
    private int currentPage;
    private int pageSize;
}
