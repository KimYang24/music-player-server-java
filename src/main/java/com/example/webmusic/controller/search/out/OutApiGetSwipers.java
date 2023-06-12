package com.example.webmusic.controller.search.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiGetSwipers {
    private int code;
    private long targetId;//album_id,song_id和artist_id都是long
    private int targetType;
    private String targetTitle;

}