package com.example.webmusic.boot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webmusic.MusicServerApplication;
import com.example.webmusic.mapper.album.AlbumMapper;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.service.album.AlbumService;
import com.example.webmusic.service.artist.ArtistService;
import com.example.webmusic.service.song.SongService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Random;



public class InitAlbum {

    static String[] array = {"流行", "轻音乐", "电音", "摇滚"};
    public static void main(String[] args) {

        //启动springboot
        ApplicationContext context = SpringApplication.run(MusicServerApplication.class, args);
        AlbumService albumService = context.getBean(AlbumService.class);
        AlbumMapper albumMapper = context.getBean(AlbumMapper.class);
        SongService songService = context.getBean(SongService.class);
        // 遍历表中的所有数据
        List<Album> albumList = albumMapper.selectList(null);
        // 设置日期字段的值
        Random random = new Random();
        for (Album album : albumList) {
            //随机设置发布日期
//            int index= random.nextInt(4);
//            System.out.println("index = " + index);
//            album.setPublish_time(generateRandomDate());
//            album.setType(array[index]);
//            long id = album.getAlbum_id();
//            String data = album.getPublish_time();
//            QueryWrapper<Song> qws = new QueryWrapper<>();
//            qws.eq("album_id",id);
//            List<Song> songs = songService.list(qws);
//            for (Song s : songs){
//                s.setPublish_time(data);
//                songService.updateById(s);
//            }



        }

        // 批量更新日期字段的值
//        albumService.updateBatchById(albumList);


    }

    private static String generateRandomDate() {
        // 在这里编写生成随机日期的逻辑
        // 返回生成的随机日期
        Date startDate = Date.valueOf("2006-5-03");  // 起始日期
        Date endDate = new Date(System.currentTimeMillis());  // 当前日期

        long startMillis = startDate.getTime();
        long endMillis = endDate.getTime();
        long dayMillis = 24 * 60 * 60 * 1000;  // 一天的毫秒数
        long daysDiff = (endMillis - startMillis) / dayMillis;  // 日期范围的天数差

        Random random = new Random();
        long randomOffset = random.nextInt((int) daysDiff + 1);

        long randomMillis = startMillis + (randomOffset * dayMillis);
        Date randomDate = new Date(randomMillis);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(randomDate);

//        System.out.println("随机日期: " + formattedDate);
        return formattedDate;
    }


}
