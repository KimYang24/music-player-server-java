package com.example.webmusic.controller.user.out.User;

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
