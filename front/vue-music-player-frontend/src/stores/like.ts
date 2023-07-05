import { defineStore } from 'pinia'
import { AlertError, AlertSuccess } from '@/utils/alert/AlertPop'
import { apiAddLike, apiDelLike, apiUserLike } from '@/utils/api/like'
import type { Album } from '@/models/album'
import type { Artist } from '@/models/artist'
import type { Song } from '@/models/song'
import type { PlayList } from '@/models/playlist'
import type { LikeForm } from '@/models/like'

export const useLikeStore = defineStore('like', {
  state: () => ({
    albums: [] as Album[],
    artists: [] as Artist[],
    songs: [] as Song[],
    playlists: [] as PlayList[]
  }),
  getters: {},
  actions: {
    async updateLikes(userId: number) {
      //加载数据
      const res = await apiUserLike(userId)
      if (res.code == 200) {
        this.albums = res.albums
        this.artists = res.artists
        this.songs = res.songs
        this.playlists = res.playlists
      } else {
        AlertError('获取用户收藏')
      }
    },
    async addLike(likeForm: LikeForm, userId: number) {
      const res = await apiAddLike(likeForm)
      if (res.code == 200) {
        AlertSuccess('添加收藏成功')
        this.updateLikes(userId)
      } else {
        AlertError('添加收藏失败')
      }
    },
    async delLike(likeForm: LikeForm, userId: number) {
      const res = await apiDelLike(likeForm)
      if (res.code == 200) {
        AlertSuccess('删除收藏成功')
        this.updateLikes(userId)
      } else {
        AlertError('删除收藏失败')
      }
    }
  }
})
