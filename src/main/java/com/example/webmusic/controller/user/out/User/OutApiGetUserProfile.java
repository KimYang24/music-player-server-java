package com.example.webmusic.controller.user.out.User;

import com.example.webmusic.frontend_model.UserFront;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiGetUserProfile {
    long code;
    UserFront profile;
}
