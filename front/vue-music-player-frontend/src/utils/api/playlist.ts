import type { PlayList } from '@/models/playlist'
import myAxios from './myAxios'
import type { Song } from '@/models/song'

//获取歌单信息
export async function apiPlayListDetail(playListId: number) {
  const data = await myAxios.get<{
    code: number
    playlist: PlayList
    songs: Song[]
  }>('detail/playlist', { playListId: playListId })
  return data
}

//获取热门歌单
export async function apiHotPlayList(pageSize: number, currentPage: number) {
  const data = await myAxios.get<{
    code: number
    pageTotal: number
    playlist: PlayList[]
  }>('library/playlist/hot', { pageSize: pageSize, currentPage: currentPage })
  return data
}
