package com.example.webmusic.models.log;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("log")
public class Log {
    @TableId(type = IdType.AUTO)
    private long log_id;
    private Long song_id;
    /**
     * 歌曲名
     */
    private String songname;
    /**
     * 日志时间
     */
    private String time;
    /**
     * 1为注册2为播放3为下载
     */
    private long type;
    private Long user_id;
    /**
     * 用户名
     */
    private String username;
}
