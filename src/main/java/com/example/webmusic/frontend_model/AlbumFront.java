package com.example.webmusic.frontend_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlbumFront {

    private long albumId;
    private long artistId;
    private String artist;
    private String album;
    private String profile;
    private long size;
    private String type;
    private String publishTime;
    private String picUrl;

}
