package com.example.webmusic.controller.search.out;


import com.example.webmusic.frontend_model.SongFront;
import lombok.Data;

import java.util.List;

@Data
public class OutApiSearchSongs {
    private long code;
    private long pageTotal;
    private List<SongFront> songFronts;
}
