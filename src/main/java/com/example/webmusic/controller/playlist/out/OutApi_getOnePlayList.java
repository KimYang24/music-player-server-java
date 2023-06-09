package com.example.webmusic.controller.playlist.out;

import com.example.webmusic.models.playlist.PlayList;
import com.example.webmusic.models.song.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApi_getOnePlayList {
    int code;
    PlayList playlist;
    List<Song> songs;
}
