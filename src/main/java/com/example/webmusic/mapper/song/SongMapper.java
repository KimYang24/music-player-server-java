package com.example.webmusic.mapper.song;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webmusic.models.song.Song;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SongMapper extends BaseMapper<Song> {
}
