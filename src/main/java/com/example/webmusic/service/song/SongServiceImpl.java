package com.example.webmusic.service.song;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.song.in.*;
import com.example.webmusic.controller.song.out.*;
import com.example.webmusic.frontend_model.SongFront;
import com.example.webmusic.mapper.song.SongMapper;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.artist.Artist;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.service.album.AlbumService;
import com.example.webmusic.service.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

    @Autowired
    private SongMapper songMapper;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;

    @Override
    //获得特定歌曲
    public void getSong(String songName, OutApiGetSong outApiGetSong) {
        QueryWrapper<Song> qw = new QueryWrapper<>();
        qw.like("name", songName);
        List<Song> songlist = songMapper.selectList(qw);
        if (songlist.size() == 0)
            outApiGetSong.setCode(300);
        else{
            outApiGetSong.setCode(200);
            outApiGetSong.setData(songlist);
        }
    }

    @Override
    //获得特定页歌曲
    public void getPageSong(InApiGetPageSong inApiGetPageSong, OutApiGetPageSong outApiGetPageSong){
        Page<Song> songPage = new Page<>(inApiGetPageSong.getCurrentPage(),inApiGetPageSong.getPageSize());
        outApiGetPageSong.setData(songMapper.selectPage(songPage,null).getRecords());
        //null 是用来指定查询条件的参数。如果查询需要指定某些查询条件，可以将这些查询条件封装成一个对象，然后将其传递给 selectPage 方法的第二个参数。
        outApiGetPageSong.setTotals(songPage.getTotal());
        outApiGetPageSong.setCode(200);
    }

    @Override
    //修改歌曲信息
    public void modifySongInfo(SongFront songFront, OutApiModiSongInfo outApiModiSongInfo){
        Song song = songFrontToSong(songFront);
        if (updateById(song)){
            outApiModiSongInfo.setCode(200);
        }else{
            outApiModiSongInfo.setCode(300);
        }
    }
    @Override
    //根据歌手获取歌曲
    public void getSongBySinger(long artistId, OutApiGetSongBySinger outApiGetSongBySinger){
        QueryWrapper<Song> qw = new QueryWrapper<>();
        qw.eq("artist_id", artistId);
        List<Song> songlist = list(qw);
        if (songlist.size() == 0){
            outApiGetSongBySinger.setCode(300);
        }
        else{
            outApiGetSongBySinger.setCode(200);
            outApiGetSongBySinger.setData(songlist);
        }

    }

    @Override
    //根据专辑获取歌
    public void getSongByAlbum(long albumId, OutApiGetSongByAlbum outApiGetSongByAlbum){
        QueryWrapper<Song> qw = new QueryWrapper<>();
        qw.eq("album_id", albumId);
        List<Song> songlist = list(qw);
        if (songlist.size() == 0){
            outApiGetSongByAlbum.setCode(300);
        }
        else{
            outApiGetSongByAlbum.setCode(200);
            outApiGetSongByAlbum.setData(songlist);
        }

    }

    @Override
    //删除歌曲
    public long deleteSong(long songId){
        Song song = getById(songId);
        //不存在
        if (song==null){
            return 300;
        }
        long albumId = song.getAlbum_id();
        Album album = albumService.getById(albumId);
        long size = album.getSize();
        if (size==1){
            removeById(songId);
            albumService.removeById(albumId);
        }else{
            removeById(songId);
            album.setSize(size-1);
            albumService.updateById(album);
        }
       return 200;
    }

    @Override
    //根据songId获取单首歌曲
    public void getOneSongById(long songID, OutApiGetOneSong out) {
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
    public void getSongListByArtist(long artistId,String order,long currentPage,long pageSize,OutApiGetSongsByArtist outApiGetSongsByArtist){
        QueryWrapper<Song> qw = new QueryWrapper<>();
        qw.like("artist_id",artistId);
        List<Song> songList = songMapper.selectList(qw);
        int total = songList.size();
        long totalPages = (total + pageSize - 1) / pageSize;
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



    public Song songFrontToSong(SongFront songFront){
        Song song = Song.builder()
                .album(songFront.getAlbum())
                .album_id(songFront.getAlbumId())
                .artist(songFront.getArtist())
                .artist_id(songFront.getArtistId())
                .duration(songFront.getDuration())
                .lyric_url(songFront.getLyricUrl())
                .name(songFront.getName())
                .mark(songFront.getMark())
                .pic_url(songFront.getPicUrl())
                .pop(songFront.getPop())
                .publish_time(songFront.getPublishTime())
                .type(songFront.getType())
                .url(songFront.getUrl())
                .song_id(songFront.getSongId())
                .build();
        return song;
    }




    /*-------------------------------------------------------------------------------------*/

    /*-------------------------------------------------------------------------------------*/

    /*-------------------------------------------------------------------------------------*/

    @Override
    //添加一首歌曲
    public void addSong(SongFront songFront, OutApiAddSong outApiAddSong) {
        //传过来的数据只有歌曲类型、歌曲名、歌手名、专辑名、音乐时长、点赞数、评论数、picUrl有用
        Song song = Song.builder()
                .album(songFront.getAlbum())
                .artist(songFront.getArtist())
                .duration(songFront.getDuration())
                .name(songFront.getName())
                .mark(songFront.getMark())
                .pic_url(songFront.getPicUrl())
                .pop(songFront.getPop())
                .url("")
                .build();

        save(song);
        /*插入歌手表*/
        checkArtistAndCreate("name",song.getArtist(),song);
        //更新歌曲表的歌手id
        updateById(song);
        /*插入专辑表*/
        checkAlbumAndCreate("name",song.getAlbum(),song);
        //更新歌曲的专辑id
        updateById(song);

        //返回数据
        Page<Song> page = new Page<>(1, 10); // 从第一页开始查询
        IPage<Song> resultPage = songMapper.selectPage(page,null);
        List<Song> records = resultPage.getRecords();
        long pageTotal = resultPage.getPages();
        // 找到目标数据的索引
        long sid = song.getSong_id();
        List<Song> data=new ArrayList<>();
        int i=0;
        for (;i<10;i++){
            if (getById(sid)!=null){
                data.add(getById(sid));
            }
            sid--;
        }
        outApiAddSong.setCode(200);
        outApiAddSong.setTotals(pageTotal);
        outApiAddSong.setCurrentPage(pageTotal);
        outApiAddSong.setData(data);
    }

    //如果不存在就create专辑
    public void checkAlbumAndCreate(String columnName, String columnValue,Song song) {
        // 创建查询条件
        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(columnName, columnValue);
        // 查询数据库是否存在符合条件的记录
        Album album = albumService.getOne(queryWrapper);

        if (album == null) {
            // 数据库中不存在符合条件的记录，创建新的记录
            Album newAlbum = Album.builder()
                    .name(columnValue)
                    .size(1)
                    .artist_id(song.getArtist_id())
                    .artist(song.getArtist())
                    .publish_time(song.getPublish_time())
                    .build();
            // 插入新的记录
            albumService.save(newAlbum);
            //返回album id
            song.setAlbum_id(newAlbum.getAlbum_id());
            //歌手专辑数量加1
            // 创建查询条件
            QueryWrapper<Artist> qw = new QueryWrapper<>();
            qw.eq("name", song.getArtist());
            Artist art = artistService.getOne(qw);
            art.setAlbum_size(art.getAlbum_size()+1);
            artistService.updateById(art);
        } else {
            // 数据库中已存在符合条件的记录，不做任何操作
            album.setSize(album.getSize()+1);
            albumService.updateById(album);
            song.setAlbum_id(album.getAlbum_id());
        }
    }

    public void checkArtistAndCreate(String columnName, String columnValue,Song song) {
        // 创建查询条件
        QueryWrapper<Artist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(columnName, columnValue);

        // 查询数据库是否存在符合条件的记录
        Artist artist = artistService.getOne(queryWrapper);

        if (artist == null) {
            // 数据库中不存在符合条件的记录，创建新的记录
            Artist newArtist = Artist.builder()
                    .name(columnValue)
                    .song_size(1)
                    .build();
            // 插入新的记录
            artistService.save(newArtist);
            //返回artist id
            song.setArtist_id(newArtist.getArtist_id());
        } else {
            // 数据库中已存在符合条件的记录，不做任何操作
            artist.setSong_size(artist.getSong_size()+1);
            artistService.updateById(artist);
            song.setArtist_id(artist.getArtist_id());
        }
    }
}