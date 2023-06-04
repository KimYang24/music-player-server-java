package com.example.webmusic.controller.user.out.User;

import com.example.webmusic.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OutApi_getAllUserInfo {
    private int code;
    private List<User> data;
    private long totals;
}
