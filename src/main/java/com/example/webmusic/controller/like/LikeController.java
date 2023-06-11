package com.example.webmusic.controller.like;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webmusic.controller.like.out.OutApi_getUserLike;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.artist.Artist;
import com.example.webmusic.models.like.Like;
import com.example.webmusic.models.playlist.PlayList;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.service.album.AlbumService;
import com.example.webmusic.service.artist.ArtistService;
import com.example.webmusic.service.like.LikeService;
import com.example.webmusic.service.playlist.PlayListService;
import com.example.webmusic.service.song.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private PlayListService playListService;
    @GetMapping("/user/like")
    public OutApi_getUserLike getUserLikes(@RequestParam("userId") long userID){
        OutApi_getUserLike out = new OutApi_getUserLike();
        List<Like> likeList = likeService.getUserLike(userID);
        System.out.println("likeList.size() = " + likeList.size());
        List<Song> songs = new ArrayList<>();
        List<Artist> artists = new ArrayList<>();
        List<Album> albums = new ArrayList<>();
        List<PlayList> playLists = new ArrayList<>();
        for(Like like : likeList) {
            if(like.getType() == 1){
                Song song = songService.getById(like.getSong_id());
                songs.add(song);
            }
            if(like.getType() == 2) {
                Artist artist = artistService.getById(like.getArtist_id());
                artists.add(artist);
            }
            if(like.getType() == 3) {
               Album album = albumService.getById(like.getAlbum_id());
                albums.add(album);
            }
            if(like.getType() == 4) {
                PlayList playList = playListService.getById(like.getPlaylist_id());
                playLists.add(playList);
            }
        }
        out.setCode(200);
        out.setSongs(songs);
        out.setArtists(artists);
        out.setAlbums(albums);
        out.setPlaylists(playLists);
        return out;
    }

    @PostMapping("/user/like")
    public Map<String,Object> addLike(@RequestBody Like like){
        int code = likeService.addUserLike(like);
        Map<String,Object> m = new HashMap<>();
        m.put("code",code);
        return m;
    }

    @PostMapping("/user/like/delete")
    public Map<String,Object> deleteLike(@RequestBody Like like){
        int code = likeService.deleteUserLike(like);
        Map<String,Object> m = new HashMap<>();
        m.put("code",code);
        return m;
    }
}
