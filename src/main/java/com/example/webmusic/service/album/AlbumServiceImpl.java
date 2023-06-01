package com.example.webmusic.service.album;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.mapper.album.AlbumMapper;
import com.example.webmusic.models.album.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;
}
