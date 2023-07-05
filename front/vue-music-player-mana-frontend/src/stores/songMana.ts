import { defineStore } from 'pinia'

export const useSongManaStore = defineStore('songMana', {
  state: () => ({
    //编辑歌曲相关
    appName: '',
    isVisible: false
  }),
  actions: {
    SetSongDialog(appname: string, isVisible: boolean) {
      this.appName = appname
      this.isVisible = isVisible
    }
  }
})
