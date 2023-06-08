package com.example.webmusic.frontend_model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFront {
    private String age;
    private String email;
    private String gender;
    private String nickname;
    private String phone;
    private String picUrl;
    private Long userId;
    private String username;
}
