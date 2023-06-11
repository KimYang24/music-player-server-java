package com.example.webmusic.service.mv;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.mv.out.OutApiGetAllMv;
import com.example.webmusic.mapper.mv.MvMapper;
import com.example.webmusic.models.mv.Mv;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MvServiceImpl extends ServiceImpl<MvMapper, Mv> implements MvService {
    @Override
    public void getAllMv(OutApiGetAllMv outApiGetAllMv) {
       List<Mv> mvs = list();
       if (mvs.size()==0){
           outApiGetAllMv.setCode(300);
       }else{
           outApiGetAllMv.setCode(200);
       }
        outApiGetAllMv.setMvs(mvs);
    }
}
