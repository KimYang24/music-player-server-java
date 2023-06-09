package com.example.webmusic.controller.playlist;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webmusic.controller.playlist.out.OutApi_getOnePlayList;
import com.example.webmusic.models.playlist.PlayListSong;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.service.playlist.PlayListService;
import com.example.webmusic.service.playlist.PlayListSongServiceImpl;
import com.example.webmusic.service.song.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayListController {
    @Autowired
    private PlayListService playListService;

    @Autowired
    private PlayListSongServiceImpl playListSongService;

    @Autowired
    private SongService songService;


    @GetMapping("/detail/playlist")
    public OutApi_getOnePlayList getOnePlayList(@RequestParam("playListId") int playListID) {
        OutApi_getOnePlayList out = new OutApi_getOnePlayList();
        playListService.getOnePlayListById(playListID,out);
        List<PlayListSong> playListSongs = playListSongService.getSongIdByPlayListId(playListID);
        List<Song> songs = new ArrayList<>();
        QueryWrapper<Song> qw = new QueryWrapper<>();
        for(PlayListSong pls : playListSongs) {
            qw.clear();
            qw.eq("song_id", pls.getSong_id());
            Song song = songService.getOne(qw);
            songs.add(song);
        }
        out.setSongs(songs);
        return out;
    }
}
