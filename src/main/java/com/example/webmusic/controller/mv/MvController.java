package com.example.webmusic.controller.mv;

import com.example.webmusic.controller.mv.out.OutApiGetAllMv;
import com.example.webmusic.service.mv.MvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MvController {

    @Autowired
    private MvService mvService;

    //获取所有mv
    @GetMapping("/mv")
    public OutApiGetAllMv getAllMv(){
        OutApiGetAllMv outApiGetAllMv = new OutApiGetAllMv();
        mvService.getAllMv(outApiGetAllMv);
        return outApiGetAllMv;
    }
}
