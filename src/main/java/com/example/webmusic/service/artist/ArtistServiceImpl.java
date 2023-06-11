package com.example.webmusic.service.artist;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.artist.out.*;
import com.example.webmusic.controller.artist.in.InApi_getSelectedArtist;
import com.example.webmusic.mapper.artist.ArtistMapper;
import com.example.webmusic.models.artist.Artist;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.List;
import java.util.Objects;

@Service
public class ArtistServiceImpl extends ServiceImpl<ArtistMapper, Artist> implements ArtistService {

    @Autowired
    private ArtistMapper artistMapper;

    @Override
    public void getArtistByCon(InApi_getSelectedArtist in, OutApi_getSelectedArtist out) {
        QueryWrapper<Artist> qw = new QueryWrapper<>();
        if(!Objects.equals(in.getFirstLetter(), "0"))
            qw.eq("first_letter",in.getFirstLetter());
        if(in.getLocation() != 0)
            qw.eq("gender",in.getGender());
        if(in.getGender() != 0)
            qw.eq("location",in.getLocation());
        Page<Artist> page = new Page<>(in.getCurrentPage(),in.getPageSize());
        IPage<Artist> artistpage = artistMapper.selectPage(page,qw);
        out.setArtists(artistpage.getRecords());
        out.setPageTotal(artistpage.getPages());
        out.setCode(200);
    }

    @Override
    public void getRandomArtist(OutApi_getRecommendArtist out) {
        List<Artist> artists = artistMapper.selectList(new QueryWrapper<Artist>().orderByAsc("rand()").last("limit 30"));
        if(artists == null){
            out.setCode(300);
        } else {
            out.setCode(200);
        }
        out.setArtists(artists);
    }

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
    public void getArtistDescribe(int artistId, OutApiGetArtistDescribe outApiGetArtistDescribe){
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

    @Override
    public void getPageArtist(int pagenum, int pagesize, OutApi_getPageArtist out) {
        Page<Artist> page = new Page<>(pagenum, pagesize);
        // 执行分页查询，使用 IPage<User> 接收分页结果
        IPage<Artist> artistPage = artistMapper.selectPage(page, null);
        List<Artist> artistlist = artistPage.getRecords();
        long totals = artistPage.getTotal();
        out.setData(artistlist);
        out.setCode(200);
        out.setTotals(totals);
    }

    @Override
    public void getArtist(String name,OutApi_getArtist out) {
        QueryWrapper<Artist> qw = new QueryWrapper<>();
        qw.like("name",name);
        List<Artist> artistlist = artistMapper.selectList(qw);
        if (artistlist.size() == 0)
            out.setCode(300);
        else
            out.setCode(200);
        out.setData(artistlist);
    }

    @Override
    public void addArtist(Artist artist, OutApi_addArtist out) {
        int result = artistMapper.insert(artist);
        if(result == 0){
            out.setCode(300);
            return;
        }
        int totals = artistMapper.selectCount(null);
        int totalPages = totals % 10 == 0 ? totals / 10 : totals / 10 + 1;
        IPage<Artist> page = new Page<>(totalPages, 10);
        List<Artist> artistlist = artistMapper.selectPage(page, null).getRecords();
        out.setData(artistlist);
        out.setCode(200);
        out.setCurrentPage(totalPages);
        out.setTotals(totals);
    }

    @Override
    public int deleteArtist(int artistID) {
        int code;
        int ok = artistMapper.deleteById(artistID);
        if (ok == 1)
            code = 200;
        else
            code = 300;
        return code;
    }

    @Override
    public int modifyArtist(Artist artist) {
        int code;
        int ok = artistMapper.updateById(artist);
        if (ok == 1)
            code = 200;
        else
            code = 300;
        return code;
    }
}
