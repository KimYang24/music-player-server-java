package com.example.webmusic.controller.search.In;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class InApiSearchByKeyword {
    private long pageSize;
    private long currentPage;
    private long keyWord;
}

