package com.example.webmusic.controller.song.out;


import com.example.webmusic.models.song.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiAddSong {
    private long code;
    private long totals;
    private long currentPage;//最后一页，方便看歌曲是否添加了
    private List<Song> data;
}
