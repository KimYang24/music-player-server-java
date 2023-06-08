package com.example.webmusic.frontend_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 轮播图模型
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SwiperFront {
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
