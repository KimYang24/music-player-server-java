package com.example.webmusic.controller.album.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InApiGetPageAlbumByArtist {
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
