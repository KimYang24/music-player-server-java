package com.example.webmusic.controller.artist.in;

import com.example.webmusic.models.artist.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InApi_modifyArtistAndAddArtist {
    Artist data;
}
