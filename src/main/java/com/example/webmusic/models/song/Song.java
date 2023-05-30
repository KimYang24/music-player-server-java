package com.example.webmusic.models.song;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 对应数据库的song表格
 */
@Data
@TableName("song")
public class Song {
    //主键
    @TableId
    private long songId;
    /**
     * 歌曲专辑名
     */
    private String album;
    private Long albumId;
    /**
     * 歌手名称
     */
    private String artist;
    private Long artistId;
    /**
     * 歌曲秒数时长
     */
    private Long duration;
    /**
     * 歌词url
     */
    private String lyricUrl;
    /**
     * 评论数
     */
    private String mark;
    /**
     * 歌曲名称
     */
    private String name;
    /**
     * 图片url，根据专辑id
     */
    private String picUrl;
    /**
     * 歌曲播放次数
     */
    private String pop;
    /**
     * 发行时间
     */
    private String publishTime;
    /**
     * 歌曲类别
     */
    private String type;
    /**
     * 歌曲url
     */
    private String url;
}
