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
public class OutApiGetPageAlbumByArtist {//用户端：分页获取歌手专辑
    private List<Album> data;
    private int code;
    private String pageTotal;
}
