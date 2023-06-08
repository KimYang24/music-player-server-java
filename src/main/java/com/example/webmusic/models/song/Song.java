package com.example.webmusic.models.song;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@TableName("song")
public class Song {
    //主键
    //将主键字段的值设置为 null 或默认值，数据库会自动生成自增的 ID，并将其赋值给实体对象的主键字段。
    //如果您手动设置了主键字段的值（非 null），则数据库会使用您提供的值插入到主键字段，并不会自动生成自增的 ID。
    @TableId(type = IdType.AUTO)
    private long song_id;
    /**
     * 歌曲专辑名
     */
    private String album;
    private long album_id;
    /**
     * 歌手名称
     */
    private String artist;
    private long artist_id;
    /**
     * 歌曲秒数时长
     */
    private long duration;
    /**
     * 歌词url
     */
    private String lyric_url;
    /**
     * 评论数
     */
    private long mark;
    /**
     * 歌曲名称
     */
    private String name;
    /**
     * 图片url，根据专辑id
     */
    private String pic_url;
    /**
     * 歌曲播放次数
     */
    private long pop;
    /**
     * 发行时间
     */
    private String publish_time;
    /**
     * 歌曲类别
     */
    private String type;
    /**
     * 歌曲
     */
    private String url;
}