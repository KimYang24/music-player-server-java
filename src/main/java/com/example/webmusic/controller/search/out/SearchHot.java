package com.example.webmusic.controller.search.out;

@lombok.Data
public class SearchHot {
    /**
     * 热搜词
     */
    private String searchWord;
    /**
     * 热搜种类，1歌曲 2歌手 3专辑
     */
    private long type;
}