import myAxios from './myAxios'
import type { Album } from '@/models/album'
import type { Artist } from '@/models/artist'
import type { Song } from '@/models/song'
import type { LikeForm } from '@/models/like'
import type { PlayList } from '@/models/playlist'
import { AlertSuccess } from '../alert/AlertPop'

//获取用户收藏
export async function apiUserLike(userId: number) {
  const data = await myAxios.get<{
    code: number
    albums: Album[]
    artists: Artist[]
    songs: Song[]
    playlists: PlayList[]
  }>('user/like', {
    userId: userId
  })
  return data
}

//添加收藏
export async function apiAddLike(likeForm: LikeForm) {
  const data = await myAxios.post<{
    code: number
  }>('user/like', {
    userId: likeForm.userId,
    songId: likeForm.songId,
    albumId: likeForm.albumId,
    artistId: likeForm.artistId,
    playListId: likeForm.playListId,
    type: likeForm.type
  })
  return data
}

//删除收藏
export async function apiDelLike(likeForm: LikeForm) {
  const data = await myAxios.post<{
    code: number
  }>('user/like/delete', {
    userId: likeForm.userId,
    songId: likeForm.songId,
    albumId: likeForm.albumId,
    artistId: likeForm.artistId,
    playListId: likeForm.playListId,
    type: likeForm.type
  })
  return data
}
