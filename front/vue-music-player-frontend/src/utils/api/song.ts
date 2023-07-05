import myAxios from './myAxios'
import type { Song } from '@/models/song'

export async function apiGetSong(songId: number) {
  const data = await myAxios.get<{
    code: number
    song: Song
  }>('detail/song', { songId: songId })
  return data
}

//获取歌词
export async function apiGetLyric(url: string) {
  const data = await myAxios.get(url)
  return data
}
