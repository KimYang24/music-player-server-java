package com.example.webmusic.frontend_model;


@lombok.Data
public class Album {
    private String album;
    private Long albumId;
    private String artist;
    private Long artistId;
    /**
     * 专辑图片
     */
    private String picUrl;
    /**
     * 专辑描述
     */
    private String profile;
    /**
     * 专辑发行时间
     */
    private String publishTime;
    private Long size;
    /**
     * 专辑类型
     */
    private String type;
}