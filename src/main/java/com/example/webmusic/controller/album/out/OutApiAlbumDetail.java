package com.example.webmusic.controller.album.out;


import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.song.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutApiAlbumDetail {//专辑详情页
    private List<Album> albums;
    private List<Song> songs;
    private int code;
}
