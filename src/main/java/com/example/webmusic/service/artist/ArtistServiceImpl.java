package com.example.webmusic.service.artist;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.artist.in.InApiArtistDetail;
import com.example.webmusic.controller.artist.in.InApiGetArtistDescribe;
import com.example.webmusic.controller.artist.out.OutApiArtistDetail;
import com.example.webmusic.controller.artist.out.OutApiGetArtistDescribe;
import com.example.webmusic.mapper.artist.ArtistMapper;
import com.example.webmusic.models.artist.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl extends ServiceImpl<ArtistMapper, Artist> implements ArtistService {

    @Autowired
    private ArtistMapper artistMapper;

    @Override
    public void artistDetail(InApiArtistDetail inApiArtistDetail, OutApiArtistDetail outApiArtistDetail){
        QueryWrapper<Artist> qw=new QueryWrapper<>();
        qw.like("artist_id",inApiArtistDetail.getArtistId());
        Artist artists=artistMapper.selectOne(qw);//选一个,选列表用selectList
        if(artists==null){
            outApiArtistDetail.setCode(200);
        }
        else{
            outApiArtistDetail.setCode(300);
            OutApiArtistDetail.ArtistBrief singerBrief = new OutApiArtistDetail.ArtistBrief();
            singerBrief.setArtistId(artists.getArtist_id());
            singerBrief.setProfile(artists.getProfile());
            singerBrief.setLocation(artists.getLocation());
            singerBrief.setPicUrl(artists.getPic_url());
            singerBrief.setAlbumSize(artists.getAlbum_size());
            singerBrief.setSongSize(artists.getSong_size());
        }
    }
    public void getArtistDescribe(InApiGetArtistDescribe inApiGetArtistDescribe, OutApiGetArtistDescribe outApiGetArtistDescribe){
        QueryWrapper<Artist> qw=new QueryWrapper<>();
        qw.like("artist_id",inApiGetArtistDescribe.getArtist_id());
        Artist artists=artistMapper.selectOne(qw);//选一个,选列表用selectList
        if(artists==null){
            outApiGetArtistDescribe.setCode(200);
        }
        else {
            outApiGetArtistDescribe.setCode(300);
            outApiGetArtistDescribe.setProfile(artists.getProfile());
        }
    }
}
