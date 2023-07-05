import { defineStore } from 'pinia'
import type { Song } from '@/models/song'
import _ from 'lodash'

export const useRecentPlayStore = defineStore('recentPlay', {
  state: () => ({
    rSongs: [] as Song[]
  }),
  getters: {},
  actions: {
    addRecentSongs(...songs: Song[]) {
      for (let index = 0; index < songs.length; index++) {
        const i = _.findIndex(this.rSongs, (o: Song) => {
          return o.songId == songs[index].songId
        })
        //移除重复元素
        if (i != -1) {
          _.pullAt(this.rSongs, i)
        }
        this.rSongs.push(songs[index])
      }
    },
    clearRecent() {
      this.rSongs = []
    }
  },
  persist: true
})
