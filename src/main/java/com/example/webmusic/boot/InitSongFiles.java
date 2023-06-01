package com.example.webmusic.boot;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webmusic.MusicServerApplication;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.artist.Artist;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.service.album.AlbumService;
import com.example.webmusic.service.artist.ArtistService;
import com.example.webmusic.service.song.SongService;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.FieldKey;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InitSongFiles {

    public InitSongFiles(AlbumService albumService, ArtistService artistService) {
        this.albumService = albumService;
        this.artistService = artistService;
    }

    private AlbumService albumService;
    private ArtistService artistService;

    //初始化数据库的时候才会运行该函数
    public static void main(String[] args) {
        //启动springboot
        ApplicationContext context = SpringApplication.run(MusicServerApplication.class, args);
        SongService songService = context.getBean(SongService.class);
        InitSongFiles initSongDatabase = new InitSongFiles(
                context.getBean(AlbumService.class),
                context.getBean(ArtistService.class));

        // 获取JAudioTagger库的Logger对象
        Logger jAudioTaggerLogger = Logger.getLogger("org.jaudiotagger");
        // 设置日志级别为OFF，禁用日志输出
        jAudioTaggerLogger.setLevel(Level.OFF);
        //设置处理的问价夹目录
        String folderPath = "C:/Users/37359/Desktop/新建文件夹";
        //输出命名后歌曲问价夹的目录
        String destFolderPath = "C:/Users/37359/Desktop/rename";
        File destFolder = new File(destFolderPath);
        if (!destFolder.exists()){
            destFolder.mkdir();
        }
        //文件对象创建
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        //遍历歌曲
        for (File file : files) {
            //如果是歌曲文件
            if (file.isFile() && isSupportedAudioFile(file.getName())) {
                try {
                    //读取歌曲标签
                    AudioFile audioFile = AudioFileIO.read(file);
                    Tag tag = audioFile.getTag();
                    AudioHeader audioHeader = audioFile.getAudioHeader();
                    //读取各种信息
                    String title = tag.getFirst(FieldKey.TITLE);
                    String artist = tag.getFirst(FieldKey.ARTIST);
                    String album = tag.getFirst(FieldKey.ALBUM);
                    String year = tag.getFirst(FieldKey.YEAR);
                    long trackLength = audioHeader.getTrackLength();
                    // 标签信息的打印
//                    System.out.println("---------------------------------");
//                    System.out.println("文件名: " + file.getName());
//                    System.out.println("标题: " + title);
//                    System.out.println("艺术家: " + artist);
//                    System.out.println("专辑: " + album);
//                    System.out.println("发行时间: " + year);
//                    System.out.println("时长 (秒): " + trackLength);
//                    System.out.println("---------------------------------");
                    //看一下有没有标签为空的
//                    if(title=="" || artist=="" || album==""){
//                        System.out.println("文件名: " + file.getName());
//                        System.out.println("标题: " + title);
//                        System.out.println("艺术家: " + artist);
//                        System.out.println("专辑: " + album);
//                    }
                    //插入歌曲数据到数据库
                    Song song = Song.builder()
                            .name(title)
                            .artist(artist)
                            .album(album)
                            .publish_time(year)
                            .duration(trackLength)
                            .url("")
                            .build();

                    songService.save(song);
                    //如下所示
                    System.out.println("file.getName() = " + file.getName());
                    String filename = file.getName();
                    String fileType = filename.substring(filename.indexOf("."));
                    String songName = filename.substring(0,filename.indexOf("."));
//                    System.out.println("fileType = " + fileType);
//                    System.out.println("songName = " + songName);
                    //根据ID补充url信息
                    Long id = song.getSong_id();
                    song.setUrl("https://web-music-player.oss-cn-guangzhou.aliyuncs.com/song/"+id+fileType);
                    songService.updateById(song);

                    //看看是否有歌词
                    for(File lrcFile : files){
                        String lrcFileName = lrcFile.getName();
                        String lrcFileNameSong = lrcFileName.substring(0,lrcFileName.indexOf("."));
                        String lrcFileType = lrcFileName.substring(lrcFileName.indexOf("."));
                        if (lrcFile.isFile()&& lrcFileType.equals(".lrc") && lrcFileNameSong.equals(songName)){
                            //是对应的歌词文件
                            //插入lrcurl字段
                            song.setLyric_url("https://web-music-player.oss-cn-guangzhou.aliyuncs.com/song/"+id+".lrc");
                            songService.updateById(song);
                            //重命名歌词文件
                            // 创建源文件路径对象
                            Path sourcePath1 = Path.of(lrcFile.getPath());
                            // 创建目标文件夹路径对象
                            Path destinationFolderPathObj1 = Path.of(destFolderPath);
                            // 创建目标文件路径对象
                            Path destinationPath = destinationFolderPathObj1.resolve(id+".lrc");
                            // 复制文件并重命名到目标文件夹
                            Files.copy(sourcePath1, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                        }else {
                        }
                    }

                    //复制并重命名歌曲文件
                    // 创建源文件路径对象
                    Path sourcePath = Path.of(file.getPath());
                    // 创建目标文件夹路径对象
                    Path destinationFolderPathObj = Path.of(destFolderPath);
                    // 创建目标文件路径对象
                    Path destinationPath = destinationFolderPathObj.resolve(id+fileType);
                    // 复制文件并重命名到目标文件夹
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                    /*插入歌手表*/
                    initSongDatabase.checkArtistAndCreate("name",song.getArtist(),song);
                    //更新歌曲表的歌手id
                    songService.updateById(song);

                    /*插入专辑表*/
                    initSongDatabase.checkAlbumAndCreate("name",song.getAlbum(),song);
                    //更新歌曲的专辑id
                    songService.updateById(song);

                } catch (Exception e) {
                    System.out.println("e.getMessage() = " + e.getMessage());
                }
            }

        }
    }

    //检测是否是音乐文件
    private static boolean isSupportedAudioFile(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equalsIgnoreCase("mp3") ||
                extension.equalsIgnoreCase("flac") ||
                extension.equalsIgnoreCase("wav") ||
                extension.equalsIgnoreCase("m4a") ||
                extension.equalsIgnoreCase("ogg");
        // 添加其他支持的音频文件扩展名
    }


    //如果不存在就create专辑
    public void checkAlbumAndCreate(String columnName, String columnValue,Song song) {
        // 创建查询条件
        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(columnName, columnValue);

        // 查询数据库是否存在符合条件的记录
        Album album = albumService.getOne(queryWrapper);

        if (album == null) {
            // 数据库中不存在符合条件的记录，创建新的记录
            Album newAlbum = Album.builder()
                    .name(columnValue)
                    .size(1)
                    .artist_id(song.getArtist_id())
                    .build();
            // 插入新的记录
            albumService.save(newAlbum);
            //返回album id
            song.setAlbum_id(newAlbum.getAlbum_id());
        } else {
            // 数据库中已存在符合条件的记录，不做任何操作
            album.setSize(album.getSize()+1);
            albumService.updateById(album);
            song.setAlbum_id(album.getAlbum_id());
        }
    }

    public void checkArtistAndCreate(String columnName, String columnValue,Song song) {
        // 创建查询条件
        QueryWrapper<Artist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(columnName, columnValue);

        // 查询数据库是否存在符合条件的记录
        Artist artist = artistService.getOne(queryWrapper);

        if (artist == null) {
            // 数据库中不存在符合条件的记录，创建新的记录
            Artist newArtist = Artist.builder()
                    .name(columnValue)
                    .build();
            // 插入新的记录
            artistService.save(newArtist);
            //返回artist id
            song.setArtist_id(newArtist.getArtist_id());
        } else {
            // 数据库中已存在符合条件的记录，不做任何操作
            song.setArtist_id(artist.getArtist_id());
        }
    }
}
