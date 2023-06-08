package com.example.webmusic.frontend_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 歌单模型
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayListFront {
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 评论数
     */
    private long mark;
    /**
     * 封面
     */
    private String picUrl;
    /**
     * 歌单名称
     */
    private String playList;
    /**
     * 播放列表ID
     */
    private long playListId;
    /**
     * 歌单描述
     */
    private String profile;
    /**
     * 歌曲数量
     */
    private long size;
    /**
     * 歌单类别 #流行#国语
     */
    private String tag;
    /**
     * 创建人名称
     */
    private String user;
    /**
     * 创建人id
     */
    private long userId;
}
