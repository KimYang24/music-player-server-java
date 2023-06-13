package com.example.webmusic.frontend_model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserFront {
    private String age;
    private String email;
    private String gender;
    private String nickname;
    private String phone;
    private String picUrl;
    private String username;
    private String password;
}
