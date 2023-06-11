package com.example.webmusic.controller.playlist.in;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InApiGetHotPlaylist {
    private int currentPage;
    private int pageSize;
}
