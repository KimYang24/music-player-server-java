package com.example.webmusic.controller.song.out;

import com.example.webmusic.models.song.Song;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiGetSong {
    private List<Song> data;
    private long code;
}
