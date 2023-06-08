package com.example.webmusic.controller.search.out;

import com.example.webmusic.frontend_model.AlbumFront;
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
    private List<AlbumFront> albumFronts;
    private long code;
    private long pageTotal;
}