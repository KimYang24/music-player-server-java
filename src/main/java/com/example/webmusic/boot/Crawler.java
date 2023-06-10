package com.example.webmusic.boot;

import com.example.webmusic.MusicServerApplication;
import com.example.webmusic.mapper.album.AlbumMapper;
import com.example.webmusic.mapper.artist.ArtistMapper;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.artist.Artist;
import com.example.webmusic.service.album.AlbumService;
import com.example.webmusic.service.artist.ArtistService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;


public class Crawler {
    public static void main(String[] args) {

        //启动springboot
        ApplicationContext context = SpringApplication.run(MusicServerApplication.class, args);
        ArtistService artistService = context.getBean(ArtistService.class);
        ArtistMapper artistMapper = context.getBean(ArtistMapper.class);
        AlbumMapper albumMapper = context.getBean(AlbumMapper.class);
        AlbumService albumService = context.getBean(AlbumService.class);
        // 遍历表中的所有数据
//        List<Artist> artistList = artistMapper.selectList(null);
//        for(Artist artist:artistList){
//            if (artist.getProfile()==null || artist.getProfile().length()==0){
//                try {
//                    // 使用 Jsoup 连接到目标网页
//                    String url = "https://baike.baidu.com/item/" + artist.getName(); // 替换为实际的专辑页面链接
//                    Document document = Jsoup.connect(url).get();
//
//                    // 根据网页结构，使用选择器获取专辑信息
//                    String artistProfile = document.select("div.MARK_MODULE").text();
//                    // 输出获取到的专辑信息
//                    System.out.println("artistProfile: " + artistProfile);
//                    String truncatedString = getTruncatedString(artistProfile, 250);
//                    artist.setProfile(truncatedString);
//                    artistService.updateById(artist);
//                    //
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        // 遍历表中的所有数据
//        List<Album> albumList = albumMapper.selectList(null);
//        for(Album album:albumList){
//            if (album.getProfile()==null || album.getProfile().length()==0){
//                try {
//                    // 使用 Jsoup 连接到目标网页
//                    String url = "https://baike.baidu.com/item/" + album.getName(); // 替换为实际的专辑页面链接
//                    Document document = Jsoup.connect(url).get();
//
//                    // 根据网页结构，使用选择器获取专辑信息
//                    String artistProfile = document.select("div.MARK_MODULE").text();
//                    // 输出获取到的专辑信息
//                    System.out.println("artistProfile: " + artistProfile);
//                    String truncatedString = getTruncatedString(artistProfile, 250);
//                    album.setProfile(truncatedString);
//                    albumService.updateById(album);
//                    //
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }



    }

    public static String getTruncatedString(String originalString, int byteLimit) {
        if (originalString.getBytes().length <= byteLimit) {
            return originalString;
        }
        String truncatedString = originalString.substring(0, byteLimit);
        while (getByteLength(truncatedString) > byteLimit) {
            truncatedString = truncatedString.substring(0, truncatedString.length() - 1);
        }
        return truncatedString;
    }

    public static int getByteLength(String str) {
        return str.getBytes().length;
    }


}
