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
public class OutApiGetAlbumByArtist {
    private List<Album> data;
    private long code;
    /**
     * 有多少页数据
     */
    private String pageTotal;
}
