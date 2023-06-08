package com.example.webmusic.service.search;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webmusic.controller.search.In.InApiSearchByKeyword;
import com.example.webmusic.controller.search.out.OutApiSearchSongs;
import com.example.webmusic.mapper.song.SongMapper;
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

    @Override
    public void searchSongsByKeyword(InApiSearchByKeyword inApiSearchByKeyword, OutApiSearchSongs outApiSearchSongs) {
        // 执行分页查询，使用 IPage<User> 接收分页结果
        Page<Song> page = new Page<>(inApiSearchByKeyword.getCurrentPage(), inApiSearchByKeyword.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper<>();


        IPage<Song> songPage = songMapper.selectPage(page, queryWrapper);
        List<Song> songList = songPage.getRecords();
        long pageTotal = songPage.getTotal();
        //执行完毕
    }
}
