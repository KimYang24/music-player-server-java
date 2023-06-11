package com.example.webmusic.service.log;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.log.in.InApiSaveLog;
import com.example.webmusic.controller.log.out.OutApiManaLog;
import com.example.webmusic.models.log.Log;
import com.example.webmusic.models.user.User;

public interface LogService extends IService<Log> {

    long savePlayLog(InApiSaveLog inApiSaveLog);

    long saveDownloadLog(InApiSaveLog inApiSaveLog);

    long saveRegisterLog(User user);

    void getLogByType(long type, OutApiManaLog outApiManaLog);


}
