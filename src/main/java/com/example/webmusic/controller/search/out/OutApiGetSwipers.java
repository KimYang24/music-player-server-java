package com.example.webmusic.controller.search.out;

import com.example.webmusic.frontend_model.SwiperFront;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiGetSwipers {
    private int code;
    private List<SwiperFront> swipers;
}