package com.example.webmusic.controller.user.out.User;

import com.example.webmusic.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApi_getAUser {
    int code;
    User data;
    long totals;
}
