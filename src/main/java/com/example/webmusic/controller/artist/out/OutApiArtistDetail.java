package com.example.webmusic.controller.artist.out;

import com.example.webmusic.models.artist.Artist;
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

    private int code;
    private Artist artistDetail;

}

