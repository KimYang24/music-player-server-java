package com.example.webmusic.controller.like.out;

import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.artist.Artist;
import com.example.webmusic.models.like.Like;
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
public class OutApi_getUserLike {
    long code;
    List<Song> songs;
    List<Album> albums;
    List<Artist> artists;
    List<PlayList> playlists;
}
