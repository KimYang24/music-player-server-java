import { defineStore } from 'pinia'
import { AlertError } from '@/utils/alert/AlertPop'
import type { Artist } from '@/models/artist'
import { apiArtistList } from '@/utils/api/artist'

export const useArtistStore = defineStore('artist', {
  state: () => ({
    pageData: {
      page: 0,
      pageSize: 10,
      pageTotal: 1,

      //是否显示加载动画
      loading: false,
      noMore: false,
      //查询信息
      firstLetter: '0',
      gender: 0,
      location: 0
    },

    artists: [] as Artist[]
  }),
  getters: {},
  actions: {
    //分页查询
    async pageGet() {
      this.pageData.noMore = false
      this.pageData.loading = true
      const res = await apiArtistList(
        this.pageData.pageSize,
        this.pageData.page,
        this.pageData.firstLetter,
        this.pageData.gender,
        this.pageData.location
      )
      if (res.code == 200) {
        //判断是否已经没有页数了
        if (this.pageData.page >= this.pageData.pageTotal) {
          //所有数据已经取完
          this.pageData.noMore = true
          return
        }
        if (this.pageData.page == 0) {
          //初始时设置数据
          console.log(11111111111)

          this.artists = res.artists
        } else {
          //否则push
          this.artists.push(...res.artists)
        }
        //当前位置页数加1
        this.pageData.page++
        console.log(2222222222222222)
        //更新pageSize
        this.pageData.pageTotal = res.pageTotal
      } else {
        AlertError('抱歉，没有符合筛选条件的歌手！')
      }
      //加载动画
      this.pageData.loading = false
    }
  }
})
