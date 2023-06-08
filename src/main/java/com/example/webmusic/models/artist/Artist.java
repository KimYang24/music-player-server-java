package com.example.webmusic.models.artist;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * artist
 */
@Data
@Builder
@TableName("artist")
public class Artist {
    @TableId(type = IdType.AUTO)
    private long artist_id;
    /**
     * 歌手专辑数量
     */
    private String album_size;
    /**
     * 歌手名字首字母
     */
    private String first_letter;
    /**
     * 歌手性别，1男2女3组合
     */
    private long gender;
    /**
     * 歌手的地区
     */
    private String location;
    private String name;
    /**
     * 歌手照片
     */
    private String pic_url;
    /**
     * 歌手简介
     */
    private String profile;
    /**
     * 歌手歌曲总数
     */
    private String song_size;
}
