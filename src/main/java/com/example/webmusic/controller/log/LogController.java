package com.example.webmusic.controller.log;

import com.example.webmusic.controller.log.in.InApiSaveLog;
import com.example.webmusic.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LogController {
    @Autowired
    private LogService logService;
    //保存播放歌曲日志
    @PostMapping(value = "/log/play")
    public Map<String,Object> savePlayLog(@RequestBody InApiSaveLog inApiSaveLog){
        long code =  logService.savePlayLog(inApiSaveLog);
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        return map;
    }

    //保存下载歌曲日志
    @PostMapping(value = "/log/download")
    public Map<String,Object> saveDownloadLog(@RequestBody InApiSaveLog inApiSaveLog){
        long code =  logService.saveDownloadLog(inApiSaveLog);
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        return map;
    }
}
