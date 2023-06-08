package com.example.webmusic.frontend_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * mv模型 仅作测试
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MvFront {
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
