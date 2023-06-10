package com.example.webmusic.boot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webmusic.MusicServerApplication;
import com.example.webmusic.mapper.album.AlbumMapper;
import com.example.webmusic.mapper.artist.ArtistMapper;
import com.example.webmusic.mapper.song.SongMapper;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.artist.Artist;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.service.album.AlbumService;
import com.example.webmusic.service.artist.ArtistService;
import com.example.webmusic.service.song.SongService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.util.List;

public class InitPictrue {

    public static void main(String[] args) {
        //启动springboot
        ApplicationContext context = SpringApplication.run(MusicServerApplication.class, args);
        ArtistService artistService = context.getBean(ArtistService.class);
        ArtistMapper artistMapper = context.getBean(ArtistMapper.class);
        AlbumMapper albumMapper = context.getBean(AlbumMapper.class);
        AlbumService albumService = context.getBean(AlbumService.class);
        SongService songService = context.getBean(SongService.class);
        SongMapper songMapper = context.getBean(SongMapper.class);

        //设置处理的问价夹目录
        String folderPath = "C:/Users/37359/Desktop/artist";
        File destFolder = new File(folderPath);
        String artist_path = "https://web-music-player.oss-cn-guangzhou.aliyuncs.com/artist/";

//        if (!destFolder.exists()){
//            destFolder.mkdir();
//        }
        //文件对象创建
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        //遍历歌曲
        for (File file : files) {
            //如果是歌曲文件
            if (file.isFile() && isSupportedPicFile(file.getName())) {
                //
                String fileName = file.getName();
//                int album_id =Integer.parseInt(fileName.substring(0,file.getName().lastIndexOf('.')));
//                System.out.println(id);


//                QueryWrapper<Album> qwa = new QueryWrapper<>();
//                qwa.eq("album_id",album_id);
//                Album album = albumService.getOne(qwa);
//                album.setPic_url(cover_path+file.getName());
//                albumService.updateById(album);

//                QueryWrapper<Song> qws = new QueryWrapper<>();
//                qws.eq("album_id",album_id);
//                List<Song> songs = songService.list(qws);
//                for(Song song:songs){
//                    song.setPic_url(cover_path+file.getName());
//                    songService.updateById(song);
//                }

                int artist_id =Integer.parseInt(fileName.substring(0,file.getName().lastIndexOf('.')));
                QueryWrapper<Artist> qwaa = new QueryWrapper<>();
                qwaa.eq("artist_id",artist_id);
                Artist artist = artistService.getOne(qwaa);
                artist.setPic_url(artist_path+file.getName());
                artistService.updateById(artist);



            }
        }

    }

    //检测是否是图片文件
    private static boolean isSupportedPicFile(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equalsIgnoreCase("png") ||
                extension.equalsIgnoreCase("jpg") ||
                extension.equalsIgnoreCase("jpeg");
    }
}
