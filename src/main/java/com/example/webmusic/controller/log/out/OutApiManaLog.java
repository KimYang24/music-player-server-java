package com.example.webmusic.controller.log.out;

import com.example.webmusic.models.log.Log;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OutApiManaLog {
    long code;
    List<Log> data;
}
