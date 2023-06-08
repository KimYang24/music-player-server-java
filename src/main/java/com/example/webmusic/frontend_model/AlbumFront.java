package com.example.webmusic.frontend_model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumFront {
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