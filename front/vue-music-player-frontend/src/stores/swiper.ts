import { defineStore } from 'pinia'
import { apiSwiper } from '@/utils/api/swiper'
import type { Swiper } from '@/models/swiper'
import { AlertError } from '@/utils/alert/AlertPop'

export const useSwiperStore = defineStore('swiper', {
  state: () => ({
    swipers: [] as Swiper[]
  }),
  getters: {},
  actions: {
    async updateSwipers() {
      if (this.swipers.length) return
      //加载数据
      const res = await apiSwiper()
      if (res.code == 200) {
        this.swipers = res.swipers
      } else {
        AlertError('获取轮播图失败')
      }
    }
  }
})
