package com.example.webmusic.service.search;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webmusic.controller.search.In.InApiSearchByKeyword;
import com.example.webmusic.controller.search.out.*;
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
        queryWrapper.like("name",keyword); // 使用like进行模糊匹配，指定name字段包含关键字

        IPage<Artist> artistPge = artistMapper.selectPage(page, queryWrapper);
        List<Artist> artistList = artistPge.getRecords();//// 获取查询结果列表
        long pageTotal = artistPge.getPages();
        System.out.println("artistPge.getTotal() = " + artistPge.getTotal());
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

    @Override
    public void searchHot(OutApiSearchHot outApiSearchHot) {
        outApiSearchHot.setCode(200);
        List<OutApiSearchHot.SearchHot> hots = new ArrayList<>();
        hots.add(0, OutApiSearchHot.SearchHot.builder()
                        .searchWord("爱")
                        .type(1)
                        .build());
        hots.add(1, OutApiSearchHot.SearchHot.builder()
                .searchWord("all")
                .type(1)
                .build());
        hots.add(2, OutApiSearchHot.SearchHot.builder()
                .searchWord("忘记")
                .type(1)
                .build());
        hots.add(3, OutApiSearchHot.SearchHot.builder()
                .searchWord("渡")
                .type(2)
                .build());
        hots.add(4, OutApiSearchHot.SearchHot.builder()
                .searchWord("go")
                .type(2)
                .build());
        hots.add(5, OutApiSearchHot.SearchHot.builder()
                .searchWord("精选")
                .type(2)
                .build());
        hots.add(6, OutApiSearchHot.SearchHot.builder()
                .searchWord("周杰伦")
                .type(3)
                .build());
        hots.add(7, OutApiSearchHot.SearchHot.builder()
                .searchWord("taylor")
                .type(3)
                .build());
        hots.add(8, OutApiSearchHot.SearchHot.builder()
                .searchWord("avril")
                .type(3)
                .build());

        outApiSearchHot.setSearchHot(hots);
    }

    @Override
    public void searchHotByKeyWord(String keyword, OutApiHotByKeyword outApiHotByKeyword) {
        // 执行歌曲分页查询
        Page<Song> page1 = new Page<>(1, 4);
        QueryWrapper<Song> qwS = new QueryWrapper<>();
        qwS.like("name", keyword); // 使用like进行模糊匹配，指定name字段包含关键字
        IPage<Song> songIPage = songMapper.selectPage(page1, qwS);
        List<Song> songList = songIPage.getRecords();//// 获取查询结果列表
        outApiHotByKeyword.setSongs(songList);
        // 执行专辑分页查询
        Page<Album> page2 = new Page<>(1, 4);
        QueryWrapper<Album> qwAlbum = new QueryWrapper<>();
        qwAlbum.like("name", keyword); // 使用like进行模糊匹配，指定name字段包含关键字
        IPage<Album> albumIPage = albumMapper.selectPage(page2, qwAlbum);
        List<Album> albumList = albumIPage.getRecords();//// 获取查询结果列表
        outApiHotByKeyword.setAlbums(albumList);
        // 执行歌手分页查询
        Page<Artist> page3 = new Page<>(1, 4);
        QueryWrapper<Artist> qwArtist = new QueryWrapper<>();
        qwArtist.like("name", keyword); // 使用like进行模糊匹配，指定name字段包含关键字
        IPage<Artist> songPage = artistMapper.selectPage(page3, qwArtist);
        List<Artist> artistList = songPage.getRecords();//// 获取查询结果列表
        outApiHotByKeyword.setArtists(artistList);

        outApiHotByKeyword.setCode(200);

    }
}
