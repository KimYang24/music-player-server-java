package com.example.webmusic.controller.album.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InApiAddAlbum {
    private String album;
    private String artist;
    private String picUrl;
    private String profile;
    private String publishTime;
    private long size;
    private String type;

}
