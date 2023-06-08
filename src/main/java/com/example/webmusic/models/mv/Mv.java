package com.example.webmusic.models.mv;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("mv")
public class Mv {
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

    private String pic_url;
    private String url;
}
