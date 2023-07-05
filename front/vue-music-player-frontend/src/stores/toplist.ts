import { defineStore } from 'pinia'
import { apiTopListDetail } from '@/utils/api/toplist'
import { AlertError } from '@/utils/alert/AlertPop'
import type { AllTopList } from '@/models/toplist'

export const useTopListStore = defineStore('toplist', {
  state: () => ({
    topListData: {} as AllTopList[]
  }),
  getters: {},
  actions: {
    async updateTopListData() {
      if (this.topListData.length) return
      const res = await apiTopListDetail()
      if (res.code == 200) {
        this.topListData = res.topLists
      } else {
        AlertError('获取排行榜失败')
      }
    }
  }
})
