package com.example.webmusic.service.artist;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.artist.out.OutApiArtistDetail;
import com.example.webmusic.controller.artist.out.OutApiGetArtistDescribe;
import com.example.webmusic.mapper.artist.ArtistMapper;
import com.example.webmusic.models.artist.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl extends ServiceImpl<ArtistMapper, Artist> implements ArtistService {

    @Autowired
    private ArtistMapper artistMapper;

    @Override
    public void artistDetail(long artistId, OutApiArtistDetail outApiArtistDetail){
        Artist artist=artistMapper.selectById(artistId);//选一个,选列表用selectList
        if(artist==null){
            outApiArtistDetail.setCode(200);
        }
        else{
            outApiArtistDetail.setCode(300);
            outApiArtistDetail.setArtistDetail(artist);
        }
    }
    public void getArtistDescribe(long artistId, OutApiGetArtistDescribe outApiGetArtistDescribe){
        QueryWrapper<Artist> qw=new QueryWrapper<>();
        qw.like("artist_id",artistId);
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
