package com.example.webmusic.controller.song.in;

import com.example.webmusic.frontend_model.SongFront;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InApiModiSong {
    private SongFront data;
}
