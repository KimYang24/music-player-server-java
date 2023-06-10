package com.example.webmusic.service.album;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.album.in.*;
import com.example.webmusic.controller.album.out.*;
import com.example.webmusic.controller.song.out.OutApiGetPageSong;
import com.example.webmusic.mapper.album.AlbumMapper;
import com.example.webmusic.mapper.song.SongMapper;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.song.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.column;

@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;
    private SongMapper songMapper;

    @Override
    //获取特定页专辑
    public void getPageAlbum(InApiGetPageAlbum inApiGetPageAlbum, OutApiGetPageSong outApiGetPageSong){

    }

    @Override
    //获取特定专辑
    public void getAlbum(InApiGetAlbum inApiGetAlbum, OutApiGetAlbum outApiGetAlbum){
        QueryWrapper<Album> qw = new QueryWrapper<>();
        qw.like("albumName",inApiGetAlbum.getAlbumName());
        List<Album> albumList = albumMapper.selectList(qw);
        if (albumList.size() == 0)
            outApiGetAlbum.setCode(300);
        else
            outApiGetAlbum.setCode(200);
        outApiGetAlbum.setData(albumList);
    }

    @Override
    //添加专辑
    public void addAlbum(InApiAddAlbum inApiAddAlbum, OutApiAddAlbum outApiAddAlbum){

    }

    @Override
    //修改专辑信息
    public void modifyAlbumInfo(InApiModifyAlbumInfo inApiModifyAlbumInfo, OutApiModifyAlbum outApiModifyAlbumInfo){

    }

    @Override
    //删除专辑
    public void deleteAlbum(InApiDeleteAlbum inApiDeleteAlbum, OutApiDeleteAlbum outApiDeleteAlbum){

    }

    //专辑详情页
    public void albumDetail(InApiAlbumDetail inApiAlbumDetail, OutApiAlbumDetail outApiAlbumDetail){
        QueryWrapper<Album> qw=new QueryWrapper<>();
        QueryWrapper<Song> qww=new QueryWrapper<>();
        qw.like("album_id",inApiAlbumDetail.getAlbumId());
        qww.like("album_id",inApiAlbumDetail.getAlbumId());
        List<Album> albumList=albumMapper.selectList(qw);
        List<Song> songList=songMapper.selectList(qww);
        if(albumList.size()==0){
            outApiAlbumDetail.setCode(200);
        }
        else{
            outApiAlbumDetail.setCode(300);
            outApiAlbumDetail.setAlbums(albumList);
            outApiAlbumDetail.setSongs(songList);
        }
    }

    //分页获取歌手专辑
    public void getAlbumByArtist(InApiGetAlbumByArtist inApiGetAlbumByArtist, OutApiGetAlbumByArtist outApiGetAlbumByArtist) {
        QueryWrapper<Album> qw = new QueryWrapper<>();
        qw.like("artist_id", inApiGetAlbumByArtist.getArtistId());
        List<Album> albumList = albumMapper.selectList(qw);
        int total = albumList.size();
        long totalPages = (total + inApiGetAlbumByArtist.getPageSize() - 1) / inApiGetAlbumByArtist.getPageSize();
        if (total == 0) {
            outApiGetAlbumByArtist.setCode(200);
        } else {
            outApiGetAlbumByArtist.setCode(300);
            outApiGetAlbumByArtist.setPageTotal(String.valueOf(totalPages));//强制转换成string类型
            outApiGetAlbumByArtist.setData(albumList);
        }
    }
}
