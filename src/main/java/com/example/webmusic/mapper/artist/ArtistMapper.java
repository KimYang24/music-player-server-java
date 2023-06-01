package com.example.webmusic.mapper.artist;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webmusic.models.artist.Artist;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArtistMapper extends BaseMapper<Artist> {
}
