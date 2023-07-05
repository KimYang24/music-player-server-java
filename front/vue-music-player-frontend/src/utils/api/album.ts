import type { Album } from '@/models/album'
import myAxios from './myAxios'
import type { Song } from '@/models/song'

//获取专辑详情
export async function apiAlbumDetail(albumId: number) {
  const data = await myAxios.get<{
    code: number
    album: Album
    songs: Song[]
  }>('detail/album', { albumId: albumId })

  return data
}

//分页获取歌手专辑
export async function apiArtistAlbums(artistId: number, pageSize: number, currentPage: number) {
  const data = await myAxios.get<{
    code: number
    pageTotal: number
    albums: Album[]
  }>('detail/artist/album', { artistId: artistId, pageSize: pageSize, currentPage: currentPage })
  return data
}
