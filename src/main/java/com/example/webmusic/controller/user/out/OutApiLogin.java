package com.example.webmusic.controller.user.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OutApiLogin {
    private String token;
    private long userId;
    private int code;
}
