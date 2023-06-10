package com.example.webmusic.controller.log.in;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class InApiSaveLog {
    private long songId;
    private long userId;
}
