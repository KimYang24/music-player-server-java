import { defineStore } from 'pinia'
import { AlertError } from '@/utils/alert/AlertPop'
import type { Mv } from '@/models/mv'
import { apiMvHot } from '@/utils/api/mv'

export const useMvStore = defineStore('mv', {
  state: () => ({
    mvs: [] as Mv[],
    id: {} as number
  }),
  getters: {},
  actions: {
    async updateMv() {
      if (this.mvs.length) return
      const res = await apiMvHot()
      if (res.code == 200) {
        this.mvs = res.mvs
      } else {
        AlertError('请求热门mv失败')
      }
    }
  }
})
