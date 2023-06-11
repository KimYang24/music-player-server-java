package com.example.webmusic.controller.artist.out;

import com.example.webmusic.models.artist.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApi_addArtist {
    int code;
    long currentPage;
    long totals;
    List<Artist> data;
}
