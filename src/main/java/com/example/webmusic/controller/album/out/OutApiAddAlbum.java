package com.example.webmusic.controller.album.out;

import com.example.webmusic.models.album.Album;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiAddAlbum {
    private int code;
    private int totals;
    private int totalPage;
    private List<Album> data;
}
