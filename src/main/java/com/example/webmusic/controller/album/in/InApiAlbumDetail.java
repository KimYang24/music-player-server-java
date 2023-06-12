package com.example.webmusic.controller.album.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InApiAlbumDetail {//专辑详情页
    private int albumId;
}
