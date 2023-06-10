package com.example.webmusic.controller.artist.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.NStringTypeHandler;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiArtistDetail {//歌手详情页

    private ArtistBrief artistBrief;
    private int code;
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArtistBrief{//设置成公有类
        /**
         * 歌手专辑数量
         */
        private long albumSize;
        /**
         * 歌手名
         */
        private String artist;
        private long artistId;
        /**
         * 歌手地区
         */
        private String location;
        /**
         * 歌手照片
         */
        private String picUrl;
        /**
         * 歌手简介
         */
        private String profile;
        /**
         * 歌手歌曲总数
         */
        private long songSize;
    }

}

