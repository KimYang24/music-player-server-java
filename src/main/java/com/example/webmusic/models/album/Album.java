package com.example.webmusic.models.album;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * album
 */
@Data
@Builder
@TableName("album")
public class Album {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private long album_id;
    /**
     * 歌手名
     */
    private String artist;
    /**
     * 歌手主键
     */
    @JsonProperty("artistId")
    private long artist_id;
    /**
     * 专辑名
     */
    private String name;
    /**
     * 专辑描述
     */
    private String profile;
    /**
     * 歌曲数量
     */
    private long size;
    /**
     * 专辑类型
     */
    private String type;
    /**
     * 专辑发行时间
     */
    @JsonProperty("publishTime")
    private String publish_time;
    /**
     * 专辑照片封面
     */
    @JsonProperty("picUrl")
    private String pic_url;

}