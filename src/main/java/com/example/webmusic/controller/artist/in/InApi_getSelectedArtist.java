package com.example.webmusic.controller.artist.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InApi_getSelectedArtist {
    int pageSize;
    int currentPage;
    String firstLetter;
    int gender;
    int location;
}
