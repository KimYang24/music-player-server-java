package com.example.webmusic.controller.album.in;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InApiGetAlbumByArtist {
    private Long artistId;
    /**
     * 请求第几页的数据
     */
    private Long currentPage;
    /**
     * 应该返回多少个数据
     */
    private Long pageSize;
}
