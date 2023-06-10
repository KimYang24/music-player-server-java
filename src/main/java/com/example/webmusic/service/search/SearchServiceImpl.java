package com.example.webmusic.service.search;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webmusic.controller.search.In.InApiSearchByKeyword;
import com.example.webmusic.controller.search.out.OutApiSearchAlbums;
import com.example.webmusic.controller.search.out.OutApiSearchArtists;
import com.example.webmusic.controller.search.out.OutApiSearchSongs;
import com.example.webmusic.mapper.album.AlbumMapper;
import com.example.webmusic.mapper.artist.ArtistMapper;
import com.example.webmusic.mapper.song.SongMapper;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.artist.Artist;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.service.album.AlbumService;
import com.example.webmusic.service.artist.ArtistService;
import com.example.webmusic.service.song.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;

    @Autowired
    private SongMapper songMapper;
    @Autowired
    private ArtistMapper artistMapper;
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public void searchArtistsByKeyword(InApiSearchByKeyword inApiSearchByKeyword, OutApiSearchArtists outApiSearchArtists) {
        // 执行分页查询，使用 IPage<User> 接收分页结果
        Page<Artist> page = new Page<>(inApiSearchByKeyword.getCurrentPage(), inApiSearchByKeyword.getPageSize());
        String keyword = inApiSearchByKeyword.getKeyWord(); // 搜索关键字
        QueryWrapper<Artist> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", keyword); // 使用like进行模糊匹配，指定name字段包含关键字

        IPage<Artist> artistPge = artistMapper.selectPage(page, queryWrapper);
        List<Artist> artistList = artistPge.getRecords();//// 获取查询结果列表
        long pageTotal = artistPge.getTotal();
        System.out.println("artistList.size() = " + artistList.size());
        //执行完毕
        outApiSearchArtists.setPageTotal(pageTotal);
        outApiSearchArtists.setCode(200);
        outApiSearchArtists.setArtists(artistList);
    }

    @Override
    public void searchAlbumsByKeyword(InApiSearchByKeyword inApiSearchByKeyword, OutApiSearchAlbums outApiSearchAlbums) {
        // 执行分页查询，使用 IPage<User> 接收分页结果
        Page<Album> page = new Page<>(inApiSearchByKeyword.getCurrentPage(), inApiSearchByKeyword.getPageSize());
        String keyword = inApiSearchByKeyword.getKeyWord(); // 搜索关键字
        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", keyword); // 使用like进行模糊匹配，指定name字段包含关键字

        IPage<Album> albumPage = albumMapper.selectPage(page, queryWrapper);
        List<Album> albumList = albumPage.getRecords();//// 获取查询结果列表
        long pageTotal = albumPage.getTotal();
        //执行完毕
        outApiSearchAlbums.setPageTotal(pageTotal);
        outApiSearchAlbums.setCode(200);
        outApiSearchAlbums.setAlbums(albumList);
    }

    @Override
    public void searchSongsByKeyword(InApiSearchByKeyword inApiSearchByKeyword, OutApiSearchSongs outApiSearchSongs) {
        // 执行分页查询，使用 IPage<User> 接收分页结果
        Page<Song> page = new Page<>(inApiSearchByKeyword.getCurrentPage(), inApiSearchByKeyword.getPageSize());
        String keyword = inApiSearchByKeyword.getKeyWord(); // 搜索关键字
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", keyword); // 使用like进行模糊匹配，指定name字段包含关键字

        IPage<Song> songPage = songMapper.selectPage(page, queryWrapper);
        List<Song> songList = songPage.getRecords();//// 获取查询结果列表
        long pageTotal = songPage.getTotal();
        //执行完毕
        outApiSearchSongs.setPageTotal(pageTotal);
        outApiSearchSongs.setCode(200);
        outApiSearchSongs.setSongs(songList);
    }
}
