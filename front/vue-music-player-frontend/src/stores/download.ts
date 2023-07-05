import { defineStore } from 'pinia'
import type { Song } from '@/models/song'

export const useDwldStore = defineStore('download', {
  state: () => ({
    dwldSongs: [] as Song[]
  }),
  getters: {},
  actions: {
    addDwlnSongs(...songs: Song[]) {
      this.dwldSongs.push(...songs)
    },
    clearDwld() {
      this.dwldSongs = []
    }
  }
})
