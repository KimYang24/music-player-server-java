import { defineStore } from 'pinia'
import {
  apiGetPersonalizeAlbums,
  apiGetPersonalizeArtists,
  apiGetPersonalizeSongs
} from '@/utils/api/personalized'
import type { PersonalizedSong, PersonalizedAlbum, PersonalizedArtist } from '@/models/personalized'
import { AlertError } from '@/utils/alert/AlertPop'

export const useMusicStore = defineStore('music', {
  state: () => ({
    personalizedSongs: {} as PersonalizedSong[],
    personalizedAlbums: {} as PersonalizedAlbum[],
    personalizedArtists: {} as PersonalizedArtist[]
  }),
  getters: {},
  actions: {
    async updatePersonalize(type: number) {
      switch (type) {
        case 1:
          {
            if (this.personalizedSongs.length) return
            const res = await apiGetPersonalizeSongs()
            if (res.code == 200) {
              this.personalizedSongs = res.songs
            } else {
              AlertError('请求推荐歌曲失败')
            }
          }
          break
        case 2:
          {
            if (this.personalizedAlbums.length) return
            const res = await apiGetPersonalizeAlbums()
            if (res.code == 200) {
              this.personalizedAlbums = res.albums
            } else {
              AlertError('请求推荐专辑失败')
            }
          }
          break
        case 3:
          {
            if (this.personalizedArtists.length) return
            const res = await apiGetPersonalizeArtists()
            if (res.code == 200) {
              this.personalizedArtists = res.artists
            } else {
              AlertError('请求推荐歌手失败')
            }
          }
          break
      }
    }
  }
})
