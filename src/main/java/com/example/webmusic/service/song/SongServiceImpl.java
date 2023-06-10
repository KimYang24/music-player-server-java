package com.example.webmusic.service.song;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.song.in.*;
import com.example.webmusic.controller.song.out.*;
import com.example.webmusic.mapper.song.SongMapper;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    //添加一首歌曲
    public void addSong(InApiAddSong inApiAddSong, OutApiAddSong outApiAddSong) {
        Song song = Song.builder()
                .name(inApiAddSong.getSongName())
                .album(inApiAddSong.getAlbumName())
                .artist(inApiAddSong.getArtistName())
                .duration(inApiAddSong.getDuration())
                .lyric_url(inApiAddSong.getLyricUrl())
                .build();
        // 创建查询条件
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id", song.getSong_id());
        // 查询数据库是否存在符合条件的记录
        Song _song = getOne(queryWrapper);
        //song_id不存在
        if(_song==null){
            outApiAddSong.setCode(200);
            save(song);
            long id = song.getSong_id();
            updateById(song);
        } else{
            //song_id已存在
            outApiAddSong.setCode(300);
        }
    }

    @Override
    //获得特定歌曲
    public void getSong(InApiGetSong inApiGetSong, OutApiGetSong outApiGetSong) {
        QueryWrapper<Song> qw = new QueryWrapper<>();
        qw.like("songName",inApiGetSong.getName());
        List<Song> songlist = songMapper.selectList(qw);
        if (songlist.size() == 0)
            outApiGetSong.setCode(300);
        else
            outApiGetSong.setCode(200);
        outApiGetSong.setData(songlist);
    }

    @Override
    //获得特定页歌曲
    public void getPageSong(InApiGetPageSong inApiGetPageSong, OutApiGetPageSong outApiGetPageSong){
        Page<Song> songPage = new Page<>(inApiGetPageSong.getCurrentPage(),inApiGetPageSong.getPageSize());
        outApiGetPageSong.setData(songMapper.selectPage(songPage,null).getRecords());;//null 是用来指定查询条件的参数。如果查询需要指定某些查询条件，可以将这些查询条件封装成一个对象，然后将其传递给 selectPage 方法的第二个参数。
        outApiGetPageSong.setTotal(songPage.getTotal());
        outApiGetPageSong.setCode(200);
    }

    @Override
    //修改歌曲信息
    public void modifySongInfo(Song song, OutApiModiSongInfo outApiModiSongInfo){
        int ok = songMapper.updateById(song);
        if (ok == 1)
            outApiModiSongInfo.setCode(200);
        else
            outApiModiSongInfo.setCode(300);

    }
    @Override
    //根据歌手获取歌
    public void getSongBySinger(InApiGetSongBySinger inApiGetSongBySinger, OutApiGetSongBySinger outApiGetSongBySinger){
        QueryWrapper<Song> qw = new QueryWrapper<>();
        qw.like("artist_id",inApiGetSongBySinger.getArtistId());
        List<Song> songlist = songMapper.selectList(qw);
        if (songlist.size() == 0)
            outApiGetSongBySinger.setCode(300);
        else
            outApiGetSongBySinger.setCode(200);
        outApiGetSongBySinger.setData(songlist);
    }

    @Override
    //根据专辑获取歌
    public void getSongByAlbum(InApiGetSongByAlbum inApiGetSongByAlbum, OutApiGetSongByAlbum outApiGetSongByAlbum){
        QueryWrapper<Song> qw = new QueryWrapper<>();
        qw.like("album_id",inApiGetSongByAlbum.getAlbumId());
        List<Song> songlist = songMapper.selectList(qw);
        if (songlist.size() == 0)
            outApiGetSongByAlbum.setCode(300);
        else
            outApiGetSongByAlbum.setCode(200);
        outApiGetSongByAlbum.setData(songlist);
    }

    @Override
    //删除歌曲
    public void deleteSong(InApiDeleteSong inApiDeleteSong, OutApiDeleteSong outApiDeleteSong){
        int ok = songMapper.deleteById(inApiDeleteSong.getSongId());
        if (ok == 1)
            outApiDeleteSong.setCode(200);
        else
            outApiDeleteSong.setCode(300);
    }

    @Override
    //根据songId获取单首歌曲
    public void getOneSongById(int songID,OutApiGetOneSong out) {
        Song song = songMapper.selectById(songID);
        if(song != null) {
            out.setCode(200);
        }
        else {
            out.setCode(300);
        }
        out.setSong(song);
    }

    @Override
    public void getSongListByArtist(InApiGetSongsByArtist inApiGetSongsByArtist,OutApiGetSongsByArtist outApiGetSongsByArtist){
        QueryWrapper<Song> qw = new QueryWrapper<>();
        qw.like("artist_id",inApiGetSongsByArtist.getArtistId());
        List<Song> songList = songMapper.selectList(qw);
        int total = songList.size();
        long totalPages = (total + inApiGetSongsByArtist.getPageSize() - 1) / inApiGetSongsByArtist.getPageSize();
        if(total ==0){
            outApiGetSongsByArtist.setCode(200);
        }
        else{
            outApiGetSongsByArtist.setCode(300);
            outApiGetSongsByArtist.setPageTotal(totalPages);
            outApiGetSongsByArtist.setData(songList);
        }
    }

    @Override
    //随机返回30首歌曲
    public void getRandomSong (OutApiGetRecommendSong out) {
        List<Song> songs = songMapper.selectList(new QueryWrapper<Song>().orderByAsc("rand()").last("limit 30"));
        if(songs == null){
            out.setCode(300);
        } else {
            out.setCode(200);
        }
        out.setSongs(songs);
    }
}