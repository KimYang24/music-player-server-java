package com.example.webmusic.service.mv;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.controller.mv.out.OutApiGetAllMv;
import com.example.webmusic.models.mv.Mv;

public interface MvService extends IService<Mv> {
    void getAllMv(OutApiGetAllMv outApiGetAllMv);
}
