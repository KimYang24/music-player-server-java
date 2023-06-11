package com.example.webmusic.service.playlist;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.playlist.OutApiGetHotPlaylist;
import com.example.webmusic.controller.playlist.in.InApiGetHotPlaylist;
import com.example.webmusic.controller.playlist.out.OutApi_getOnePlayList;
import com.example.webmusic.mapper.playlist.PlayListMapper;
import com.example.webmusic.models.playlist.PlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Random;

@Mapper
interface PlaylistMapper {//在PlaylistMapper中定义getPlaylistsByPage方法：

    @Select("SELECT * FROM playlist LIMIT #{pageSize} OFFSET #{offset}")
    static List<PlayList> getPlaylistsByPage(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize) {
        return null;
    }
}
@Service
public class PlayListServiceImpl extends ServiceImpl<PlayListMapper, PlayList> implements PlayListService {
    @Autowired
    private PlayListMapper playListMapper;
    @Override
    //获取歌单元数据
    public void getOnePlayListById(int playListID, OutApi_getOnePlayList out) {
        PlayList playList = playListMapper.selectById(playListID);
        if(playList == null){
            out.setCode(300);
        } else {
            out.setCode(200);
        }
        out.setPlaylist(playList);
    }

    public void getHotPlaylist(int currentPage,int pageSize, OutApiGetHotPlaylist outApiGetHotPlaylist){
        //获取指定页码的歌单列表
        List<PlayList> playlists = PlaylistMapper.getPlaylistsByPage(currentPage,pageSize);
        // 随机获取一个歌单
        int randomIndex = new Random().nextInt(playlists.size());
        outApiGetHotPlaylist.setData(playlists.get(randomIndex));
        outApiGetHotPlaylist.setCode(200);
    }
}
