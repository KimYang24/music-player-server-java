package com.example.webmusic.mapper.album;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webmusic.models.album.Album;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlbumMapper extends BaseMapper<Album> {
}