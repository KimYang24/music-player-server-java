package com.example.webmusic.controller.artist.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InApiArtistDetail {//歌手详情页
    private int artistId;
}
