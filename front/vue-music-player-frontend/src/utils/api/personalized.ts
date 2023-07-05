import type { PersonalizedAlbum, PersonalizedArtist, PersonalizedSong } from '@/models/personalized'
import myAxios from './myAxios'

//同样的api
export async function apiGetPersonalizeSongs() {
  const result = await myAxios.get<{
    code: number
    songs: PersonalizedSong[]
  }>('discover/song')
  return result
}

export async function apiGetPersonalizeAlbums() {
  const result = await myAxios.get<{
    code: number
    albums: PersonalizedAlbum[]
  }>('discover/album')

  return result
}

export async function apiGetPersonalizeArtists() {
  const result = await myAxios.get<{
    code: number
    artists: PersonalizedArtist[]
  }>('discover/artist')
  return result
}
