package com.example.webmusic.controller.user.out.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.webmusic.models.user.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OutApi_addUserInfo {
    private int code;
    private long totals;
    private int currentPage;
    private List<User> data;
}
