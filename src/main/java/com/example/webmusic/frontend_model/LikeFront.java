package com.example.webmusic.frontend_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 收藏模型
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeFront {
    /**
     * 喜欢的专辑的id
     */
    private long albumId;
    /**
     * 喜欢的歌手的id
     */
    private long artistId;
    /**
     * 喜欢的歌单的id
     */
    private long playlistId;
    /**
     * 喜欢的歌曲的id
     */
    private long songId;
    /**
     * 喜欢类型(1：歌曲 2：歌手 3：专辑)
     */
    private long type;
    /**
     * 喜欢的歌曲的所有者，可以通过这个所有者来遍历找到这个人所有喜欢的歌
     */
    private long userId;
}