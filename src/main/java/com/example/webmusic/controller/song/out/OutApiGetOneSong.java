package com.example.webmusic.controller.song.out;

import com.example.webmusic.models.song.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiGetOneSong {
    int code;
    Song song;
}
