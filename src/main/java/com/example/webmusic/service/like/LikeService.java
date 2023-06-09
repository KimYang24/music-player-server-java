package com.example.webmusic.service.like;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webmusic.models.like.Like;


import java.util.List;

public interface LikeService extends IService<Like> {
    List<Like> getUserLike(int userID);
    int addUserLike(Like like);
    int deleteUserLike(Like like);
}
