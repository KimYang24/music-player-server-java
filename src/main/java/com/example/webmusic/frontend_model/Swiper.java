package com.example.webmusic.frontend_model;

/**
 * 轮播图模型
 */
@lombok.Data
public class Swiper {
    private String picUrl;
    /**
     * 歌曲id或专辑id或歌手id
     */
    private long targetId;
    private String targetType;
    /**
     * 歌曲名或专辑名或歌手名
     */
    private String typeTitle;
}
