package com.example.webmusic.models.mv;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("mv")
public class Mv {
    @JsonProperty("movieId")
    @TableId(type = IdType.AUTO)
    private long movie_id;
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
    @JsonProperty("picUrl")
    private String pic_url;
    private String url;
}
