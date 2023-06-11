package com.example.webmusic.controller.artist.out;

import com.example.webmusic.models.artist.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApi_getArtistByAlbum {
    int code;
    Artist data;
}
