package com.example.webmusic.service.album;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webmusic.controller.album.in.*;
import com.example.webmusic.controller.album.out.*;
import com.example.webmusic.mapper.album.AlbumMapper;
import com.example.webmusic.mapper.song.SongMapper;
import com.example.webmusic.models.album.Album;
import com.example.webmusic.models.song.Song;
import com.example.webmusic.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;
    private SongMapper songMapper;

    @Override
    //获取特定页专辑
    public void getPageAlbum(long currentPage ,long pageSize, OutApiGetPageAlbum outApiGetPageAlbum){
        Page<Album> page = new Page<>(currentPage,pageSize);
        // 执行分页查询，使用 IPage<User> 接收分页结果

        IPage<Album> albumPage = albumMapper.selectPage(page, null);
        List<Album> albumlist = albumPage.getRecords();
        long totals = albumPage.getTotal();
        outApiGetPageAlbum.setData(albumlist);
        outApiGetPageAlbum.setCode(200);
        outApiGetPageAlbum.setTotals(totals);
    }

    @Override
    //获取特定专辑
    public void getAlbum(String albumName, OutApiGetAlbum outApiGetAlbum){
        QueryWrapper<Album> qw = new QueryWrapper<>();
        qw.like("albumName",albumName);
        List<Album> albumList = albumMapper.selectList(qw);
        if (albumList.size() == 0){
            outApiGetAlbum.setCode(300);
            return;
        }
        else{
            outApiGetAlbum.setCode(200);
            outApiGetAlbum.setData(albumList);
        }
    }

    @Override
    //添加专辑
    public void addAlbum(Album album, OutApiAddAlbum outApiAddAlbum) {
        QueryWrapper<Album> qw = new QueryWrapper<>();
        //排除同一歌手的同一个专辑
        qw.eq("Name",album.getName()).and(albumQueryWrapper -> albumQueryWrapper.eq("artist_id", album.getAlbum_id()));
        Album temp = albumMapper.selectOne(qw);
        if(temp != null) {
            outApiAddAlbum.setCode(300);
            return;
        }
        int result = albumMapper.insert(album);
        if(result == 0){
            outApiAddAlbum.setCode(300);
            return;
        }
        int totals = albumMapper.selectCount(null);
        int totalPages = totals % 10 == 0 ? totals / 10 : totals / 10 + 1;
        IPage<Album> page = new Page<>(totalPages, 10);
        List<Album> albumList = albumMapper.selectPage(page, null).getRecords();
        outApiAddAlbum.setData(albumList);
        outApiAddAlbum.setCode(200);
        outApiAddAlbum.setCurrentPage(totalPages);
        outApiAddAlbum.setTotalPage(totals);
    }

    @Override
    //修改专辑信息
    public void modifyAlbumInfo(InApiModifyAlbumInfo inApiModifyAlbumInfo, OutApiModifyAlbum outApiModifyAlbumInfo){
        Album album = albumMapper.selectById(inApiModifyAlbumInfo.getData().getAlbum_id());
        String OldName = album.getName();
        int ok = albumMapper.updateById(inApiModifyAlbumInfo.getData());
        if (ok == 1)
            outApiModifyAlbumInfo.setCode(200);
        else{
            outApiModifyAlbumInfo.setCode(300);
            if (!inApiModifyAlbumInfo.getData().getName().equals(OldName)) {
                QueryWrapper<Song> qw = new QueryWrapper<>();
                qw.like("album_id", inApiModifyAlbumInfo.getData().getAlbum_id());
                List<Song> songlist = songMapper.selectList(qw);
                for (Song s : songlist) {
                    s.setName(inApiModifyAlbumInfo.getData().getName());
                    songMapper.updateById(s);
                }
            }
        }
    }

    @Override
    //删除专辑
    public void deleteAlbum(long albumId, OutApiDeleteAlbum outApiDeleteAlbum){
        int ok = albumMapper.deleteById(albumId);
        if (ok == 1)
            outApiDeleteAlbum.setCode(200);
        else {
            outApiDeleteAlbum.setCode(300);
            QueryWrapper<Song> qw = new QueryWrapper<>();
            qw.like("album_id", albumId);
            List<Song> songlist = songMapper.selectList(qw);
            if (songlist.size() == 0)
                return;
            else {
                for (Song song : songlist) {
                    QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("id", song.getSong_id());
                    songMapper.delete(queryWrapper);
                }
            }
        }
    }

    //管理端：获取歌手的所有专辑
    public void getAlbumByArtist(long artistId,OutApiGetAlbumByArtist outApiGetAlbumByArtist){
        QueryWrapper<Album> qw = new QueryWrapper<>();
        qw.like("artist_id",artistId);
        List<Album> albumList = albumMapper.selectList(qw);
        if (albumList.size() == 0)
            outApiGetAlbumByArtist.setCode(300);
        else
            outApiGetAlbumByArtist.setCode(200);
        outApiGetAlbumByArtist.setData(albumList);
    }

    @Override
    //专辑详情页
    public void albumDetail(long albumId, OutApiAlbumDetail outApiAlbumDetail){
        QueryWrapper<Album> qw=new QueryWrapper<>();
        QueryWrapper<Song> qww=new QueryWrapper<>();
        qw.like("album_id",albumId);
        qww.like("album_id",albumId);
        List<Album> albumList=albumMapper.selectList(qw);
        List<Song> songList=songMapper.selectList(qww);
        if(albumList.size()==0){
            outApiAlbumDetail.setCode(200);
        }
        else{
            outApiAlbumDetail.setCode(300);
            outApiAlbumDetail.setAlbums(albumList);
            outApiAlbumDetail.setSongs(songList);
        }
    }

    @Override
    //分页获取歌手专辑
    public void getPageAlbumByArtist(long artistId,long currentPage,long pageSize, OutApiGetPageAlbumByArtist outApiGetPageAlbumByArtist) {
        QueryWrapper<Album> qw = new QueryWrapper<>();
        qw.like("artist_id",artistId);
        List<Album> albumList = albumMapper.selectList(qw);
        int total = albumList.size();
        long totalPages = (total + pageSize - 1) / pageSize;
        if (total == 0) {
            outApiGetPageAlbumByArtist.setCode(200);
        } else {
            outApiGetPageAlbumByArtist.setCode(300);
            outApiGetPageAlbumByArtist.setPageTotal(String.valueOf(totalPages));//强制转换成string类型
            outApiGetPageAlbumByArtist.setData(albumList);
        }
    }
    @Override
    //专辑推荐，随机返回30张专辑
    public void getRandomAlbum(OutApiGetRecommendAlbum out) {
        List<Album> albums = albumMapper.selectList(new QueryWrapper<Album>().orderByAsc("rand()").last("limit 30"));
        if(albums == null){
            out.setCode(300);
        } else {
            out.setCode(200);
        }
        out.setAlbums(albums);
    }

}
