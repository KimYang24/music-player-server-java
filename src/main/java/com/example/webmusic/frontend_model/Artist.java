package com.example.webmusic.frontend_model;

/**
 * 歌手模型
 */
@lombok.Data
public class Artist {
    /**
     * 歌手专辑数量
     */
    private long albumSize;
    /**
     * 歌手名
     */
    private String artist;
    private long artistId;
    /**
     * 歌手地区
     */
    private long location;
    /**
     * 歌手照片
     */
    private String picUrl;
    /**
     * 歌手简介
     */
    private String profile;
    /**
     * 歌手歌曲总数
     */
    private long songSize;
}