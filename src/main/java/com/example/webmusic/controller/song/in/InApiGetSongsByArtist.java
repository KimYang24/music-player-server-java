package com.example.webmusic.controller.song.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InApiGetSongsByArtist {
    private Long artistId;
    /**
     * 请求第几页的数据
     */
    private Long currentPage;
    /**
     * publish_time 或者 pop
     */
    private String order;
    /**
     * 应该返回多少个数据
     */
    private Long pageSize;
}
