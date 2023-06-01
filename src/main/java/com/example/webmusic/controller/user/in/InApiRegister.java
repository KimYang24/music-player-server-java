package com.example.webmusic.controller.user.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InApiRegister {
    private String username;
    private String nickname;
    private String phone;
    private String email;
    private String password;
}
