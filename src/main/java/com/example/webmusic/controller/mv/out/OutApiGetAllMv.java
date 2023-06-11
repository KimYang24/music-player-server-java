package com.example.webmusic.controller.mv.out;

import com.example.webmusic.models.mv.Mv;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiGetAllMv {
    private long code;
    List<Mv> mvs;
}
