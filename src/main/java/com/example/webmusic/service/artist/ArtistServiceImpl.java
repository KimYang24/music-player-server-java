package com.example.webmusic.service.artist;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.mapper.artist.ArtistMapper;
import com.example.webmusic.models.artist.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl extends ServiceImpl<ArtistMapper, Artist> implements ArtistService {

    @Autowired
    private ArtistMapper artistMapper;
}
