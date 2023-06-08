package com.example.webmusic.controller.search.out;


import java.util.List;

@lombok.Data
public class OutApiSearchHot {
    private long code;
    private List<SearchHot> searchHot;
}