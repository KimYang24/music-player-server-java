import type {
  SearchAlbums,
  SearchArtists,
  SearchHotDetail,
  SearchSongs,
  SearchUsers
} from '@/models/search'
import myAxios from './myAxios'
import type { Song } from '@/models/song'
import type { Artist } from '@/models/artist'
import type { Album } from '@/models/album'

//动态获取搜索结果
export async function apiSearchSuggest(keywords: string) {
  const data = await myAxios.get<{
    code: number
    albums: SearchAlbums[]
    artists: SearchArtists[]
    songs: SearchSongs[]
    users: SearchUsers[]
  }>('search/suggest', {
    keywords: keywords
  })
  return data
}

//获取热搜结果
export async function apiSearchHotDetail() {
  const data = await myAxios.get<{
    code: number
    searchHot: SearchHotDetail[]
  }>('search/hot')
  return data
}

//分页搜索关键词--3个
export async function apiSearchAlbum(pageSize: number, currentPage: number, keyWord: string) {
  const data = await myAxios.get<{
    code: number
    pageTotal: number
    albums: Album[]
  }>('search/album', { pageSize: pageSize, currentPage: currentPage, keyWord: keyWord })
  return data
}
export async function apiSearchArtist(pageSize: number, currentPage: number, keyWord: string) {
  const data = await myAxios.get<{
    code: number
    pageTotal: number
    artists: Artist[]
  }>('search/artist', { pageSize: pageSize, currentPage: currentPage, keyWord: keyWord })
  return data
}
export async function apiSearchSong(pageSize: number, currentPage: number, keyWord: string) {
  const data = await myAxios.get<{
    code: number
    pageTotal: number
    songs: Song[]
  }>('search/song', { pageSize: pageSize, currentPage: currentPage, keyWord: keyWord })
  return data
}
