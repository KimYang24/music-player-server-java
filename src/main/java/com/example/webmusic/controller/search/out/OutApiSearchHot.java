package com.example.webmusic.controller.search.out;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class OutApiSearchHot {
    private long code;
    private List<SearchHot> searchHot;


    @Data
    @Builder
    @AllArgsConstructor
    public static class SearchHot{
        String searchWord;
        long type;//1歌曲2歌手3专辑
    }
}