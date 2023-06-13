package com.example.webmusic.service.album;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.album.out.*;
import com.example.webmusic.frontend_model.AlbumFront;
import com.example.webmusic.mapper.album.AlbumMapper;
import com.example.webmusic.mapper.song.SongMapper;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.song.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;
    private SongMapper songMapper;

    @Autowired
    public AlbumServiceImpl(SongMapper songMapper) {//构造函数。确认在该类的构造函数中将songMapper注入
        this.songMapper = songMapper;
    }

    @Override
    //获取特定页专辑
    public void getPageAlbum(long currentPage, long pageSize, OutApiGetPageAlbum outApiGetPageAlbum) {
        Page<Album> page = new Page<>(currentPage, pageSize);
        // 执行分页查询，使用 IPage<User> 接收分页结果

        IPage<Album> albumPage = albumMapper.selectPage(page, null);
        List<Album> albumlist = albumPage.getRecords();
        outApiGetPageAlbum.setData(albumlist);
        outApiGetPageAlbum.setCode(200);
        int totals = albumMapper.selectCount(null);
        outApiGetPageAlbum.setTotals(totals);
    }

    @Override
    //获取特定专辑
    public void getAlbum(String albumName, OutApiGetAlbum outApiGetAlbum) {
        QueryWrapper<Album> qw = new QueryWrapper<>();
        qw.like("name", albumName);
        List<Album> albumList = albumMapper.selectList(qw);
        if (albumList.size() == 0) {
            outApiGetAlbum.setCode(300);
        } else {
            outApiGetAlbum.setCode(200);
            outApiGetAlbum.setData(albumList);
        }
    }

    @Override
    //添加专辑
    public void addAlbum(AlbumFront album, OutApiAddAlbum outApiAddAlbum) {
        QueryWrapper<Album> qw = new QueryWrapper<>();
        //排除同一歌手的同一个专辑
        qw.eq("Name", album.getAlbum()).and(albumQueryWrapper -> albumQueryWrapper.eq("artist", album.getArtist()));
        Album temp = albumMapper.selectOne(qw);
        if (temp != null) {
            outApiAddAlbum.setCode(300);
            return;
        }
        Album album1 = Album.builder()
                .name(album.getAlbum())
                .artist(album.getArtist())
                .pic_url(album.getPicUrl())
                .profile(album.getProfile())
                .type(album.getType())
                .size(album.getSize())
                .publish_time(album.getPublishTime())
                .build();
        int result = albumMapper.insert(album1);
        if (result == 0) {
            outApiAddAlbum.setCode(300);
            return;
        }
        int totals = albumMapper.selectCount(null);
        int totalPages = totals % 5 == 0 ? totals / 5 : totals / 5 + 1;
        IPage<Album> page = new Page<>(totalPages, 10);
        List<Album> albumList = albumMapper.selectPage(page, null).getRecords();
        outApiAddAlbum.setData(albumList);
        outApiAddAlbum.setCode(200);
        outApiAddAlbum.setTotals(totals);
        outApiAddAlbum.setTotalPage(totalPages);
    }

    @Override
    //修改专辑信息
    public void modifyAlbumInfo(AlbumFront albumFront, OutApiModifyAlbum outApiModifyAlbumInfo) {
        System.out.println("albumFront.getAlbumId() = " + albumFront.getAlbumId());
        Album album = Album.builder()
                .publish_time(albumFront.getPublishTime())
                .album_id(albumFront.getAlbumId())
                .size(albumFront.getSize())
                .type(albumFront.getType())
                .profile(albumFront.getProfile())
                .pic_url(albumFront.getPicUrl())
                .artist(albumFront.getArtist())
                .name(albumFront.getAlbum())
                .artist_id(albumFront.getArtistId())
                .build();
        String oldName = album.getName();
        int ok = albumMapper.updateById(album);
        if (ok == 0)
            outApiModifyAlbumInfo.setCode(300);
        else {
            outApiModifyAlbumInfo.setCode(200);
            String newName = album.getName();
            List<Song> songlist = songMapper.selectList(new QueryWrapper<Song>().eq("album_id", album.getAlbum_id()));
            for (Song s : songlist) {
                s.setAlbum(newName);
                songMapper.updateById(s);
            }

        }
    }

    @Override
    //删除专辑
    public void deleteAlbum(long albumId, OutApiDeleteAlbum outApiDeleteAlbum) {
        int ok = albumMapper.deleteById(albumId);
        if (ok == 1)
            outApiDeleteAlbum.setCode(200);
        else {
            outApiDeleteAlbum.setCode(300);
            QueryWrapper<Song> qw = new QueryWrapper<>();
            qw.eq("album_id", albumId);
            List<Song> songlist = songMapper.selectList(qw);
            if (songlist.size() == 0)
                return;
            else {
                for (Song song : songlist) {
                    QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("song_id", song.getSong_id());
                    songMapper.delete(queryWrapper);
                }
            }
        }
    }

    //管理端：获取歌手的所有专辑
    public void getAlbumByArtist(long artistId, OutApiGetAlbumByArtist outApiGetAlbumByArtist) {
        QueryWrapper<Album> qw = new QueryWrapper<>();
        qw.eq("artist_id", artistId);
        List<Album> albumList = albumMapper.selectList(qw);
        if (albumList.size() == 0)
            outApiGetAlbumByArtist.setCode(300);
        else
            outApiGetAlbumByArtist.setCode(200);
        outApiGetAlbumByArtist.setData(albumList);
    }

    @Override
    //专辑详情页
    public void albumDetail(long albumId, OutApiAlbumDetail outApiAlbumDetail) {
        Album album = albumMapper.selectById(albumId);
        if (album == null) {
            outApiAlbumDetail.setCode(300);
        } else {
            outApiAlbumDetail.setCode(200);
            List<Song> songlist = songMapper.selectList(new QueryWrapper<Song>().eq("album_id", albumId));
//            List<Song> songlist = songMapper.selectList(qw);//不知道查询的是<Song>这个表,默认<Album>
            outApiAlbumDetail.setAlbum(album);
            outApiAlbumDetail.setSongs(songlist);
        }
    }

    @Override
    //分页获取歌手专辑
    public void getPageAlbumByArtist(long artistId, long currentPage, long pageSize, OutApiGetPageAlbumByArtist outApiGetPageAlbumByArtist) {

        Page<Album> page = new Page<>(currentPage, pageSize);
        // 执行分页查询，使用 IPage<User> 接收分页结果
        QueryWrapper<Album> qw = new QueryWrapper<>();
        qw.eq("artist_id", artistId);
        IPage<Album> albumPage = albumMapper.selectPage(page, qw);
        List<Album> albumlist = albumPage.getRecords();
        long totalPage = albumPage.getPages();
        long totals = albumPage.getTotal();
        if (totals == 0) {
            outApiGetPageAlbumByArtist.setCode(300);
        } else {
            outApiGetPageAlbumByArtist.setCode(200);
            outApiGetPageAlbumByArtist.setPageTotal(totalPage);
            outApiGetPageAlbumByArtist.setAlbums(albumlist);
        }
    }

    @Override
    //专辑推荐，随机返回30张专辑
    public void getRandomAlbum(OutApiGetRecommendAlbum out) {
        List<Album> albums = albumMapper.selectList(new QueryWrapper<Album>().orderByAsc("rand()").last("limit 30"));
        if (albums == null) {
            out.setCode(300);
        } else {
            out.setCode(200);
        }
        out.setAlbums(albums);
    }
}
