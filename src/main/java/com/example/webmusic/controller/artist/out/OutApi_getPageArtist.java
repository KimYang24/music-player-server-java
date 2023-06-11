package com.example.webmusic.controller.artist.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.webmusic.models.artist.Artist;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApi_getPageArtist {
    int code;
    List<Artist> data;
    long totals;
}
