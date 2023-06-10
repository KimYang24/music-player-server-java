package com.example.webmusic.service.log;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.log.in.InApiSaveLog;
import com.example.webmusic.models.log.Log;
import com.example.webmusic.models.user.User;

public interface LogService extends IService<Log> {

    public long savePlayLog(InApiSaveLog inApiSaveLog);

    public long saveDownloadLog(InApiSaveLog inApiSaveLog);

    public long saveRegisterLog(User user);


}
