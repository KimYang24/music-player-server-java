package com.example.webmusic.models.artist;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * artist
 */
@Data
@Builder
@TableName("artist")
public class Artist {
    @TableId(type = IdType.AUTO)
    private long artist_id;
    /**
     * 歌手的地区
     */
    private String lacation;
    /**
    * 歌手名
    */
    private String name;
    /**
     * 歌手简介
     */
    private String profile;
}
