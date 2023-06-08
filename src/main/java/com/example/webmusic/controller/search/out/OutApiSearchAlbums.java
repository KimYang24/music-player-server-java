package com.example.webmusic.controller.search.out;

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
public class OutApiSearchAlbums {
    private List<Album> albumFronts;
    private long code;
    private long pageTotal;
}