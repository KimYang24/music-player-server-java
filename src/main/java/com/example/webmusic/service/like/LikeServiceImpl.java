package com.example.webmusic.service.like;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.mapper.like.LikeMapper;
import com.example.webmusic.models.like.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {
    @Autowired
    private LikeMapper likeMapper;
    @Override
    public List<Like> getUserLike(long userID) {
        QueryWrapper<Like> qw = new QueryWrapper<>();
        qw.eq("user_id",userID);
        return likeMapper.selectList(qw);
    }

    @Override
    public int addUserLike(Like like) {
        int code = 200;
        int result = likeMapper.insert(like);
        if(result == 0) {
            code = 300;
        }
        return code;
    }
    @Override
    public int deleteUserLike(Like like) {
        int code = 200;
        QueryWrapper<Like> qw = new QueryWrapper<>();
        qw.eq("user_id", like.getUser_id())
                .eq("song_id",like.getSong_id())
                .eq("album_id",like.getAlbum_id())
                .eq("playlist_id",like.getPlaylist_id())
                .eq("type",like.getType())
                .eq("artist_id",like.getArtist_id());
        int result = likeMapper.delete(qw);
        if(result == 0) {
            code = 300;
        }
        return code;
    }
}

