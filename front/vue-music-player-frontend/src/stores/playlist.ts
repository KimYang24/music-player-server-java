import { defineStore } from 'pinia'
import { AlertError } from '@/utils/alert/AlertPop'
import { apiHotPlayList } from '@/utils/api/playlist'
import type { PlayList } from '@/models/playlist'

export const usePlayListStore = defineStore('playlist', {
  state: () => ({
    pageData: {
      page: 0,
      pageSize: 10,
      pageTotal: 1,

      //是否显示加载动画
      loading: false,
      noMore: false
    },

    playLists: [] as PlayList[]
  }),
  getters: {},
  actions: {
    //分页查询
    async pageGet() {
      this.pageData.loading = true
      const res = await apiHotPlayList(this.pageData.pageSize, this.pageData.page)
      if (res.code == 200) {
        //判断是否已经没有页数了
        if (this.pageData.page >= this.pageData.pageTotal) {
          //所有数据已经取完
          this.pageData.noMore = true
          return
        }
        if (this.pageData.page == 0) {
          //初始时设置数据
          this.playLists = res.playlist
        } else {
          //否则push
          this.playLists.push(...res.playlist)
        }
        //当前位置页数加1
        this.pageData.page++
        //更新pageSize
        this.pageData.pageTotal = res.pageTotal
      } else {
        AlertError('抱歉，没有找到歌单！')
      }
      //加载动画
      this.pageData.loading = false
    }
  }
})
