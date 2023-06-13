package com.example.webmusic.controller.album.in;

import com.example.webmusic.frontend_model.AlbumFront;
import com.example.webmusic.models.album.Album;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InApiModifyAlbumInfo {
    AlbumFront data;
}
