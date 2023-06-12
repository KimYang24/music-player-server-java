package com.example.webmusic.controller.playlist.out;

import com.example.webmusic.models.playlist.PlayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiGetHotPlaylist {//获取热门歌单
    private List<PlayList> data;
    private int code;
    private int pageTotal;

}