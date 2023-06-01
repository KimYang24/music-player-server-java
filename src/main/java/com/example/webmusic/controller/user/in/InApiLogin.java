package com.example.webmusic.controller.user.in;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InApiLogin {
    private String phone;
    private String password;
}
