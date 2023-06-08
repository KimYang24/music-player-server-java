package com.example.webmusic.frontend_model;

/**
 * mv模型 仅作测试
 */
@lombok.Data
public class Mv {
    /**
     * 表演者
     */
    private String artist;
    /**
     * 时长
     */
    private long duration;
    /**
     * 名字
     */
    private String movie;
    private long movieId;
    private String picUrl;
    private String url;
}
