package com.example.webmusic.models.playlist;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("playlist_songs")
public class PlayListSong {
    /**
     * 表的主键
     */
    @TableId(type = IdType.AUTO)
    private long id;
    /**
     * 歌单的id
     */
    private long playlist_id;
    /**
     * 歌曲的id
     */
    private long song_id;
}
