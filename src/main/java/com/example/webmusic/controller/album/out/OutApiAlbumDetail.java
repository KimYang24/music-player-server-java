package com.example.webmusic.controller.album.out;

import com.example.webmusic.frontend_model.SongFront;
import com.example.webmusic.frontend_model.AlbumFront;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.song.Song;
import lombok.Data;

import java.util.List;

@Data
public class OutApiAlbumDetail {//专辑详情页
    private List<Album> albums;
    private List<Song> songs;
    private int code;
}
