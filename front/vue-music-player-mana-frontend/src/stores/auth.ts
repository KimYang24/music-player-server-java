import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    isLogin: false,
    token: '',
    userId: 0
  }),
  getters: {
    getAuthStatus(state) {
      return state
    }
  },
  actions: {
    SetToken(value: string) {
      this.isLogin = true
      this.token = value
      console.log('token:'+value);
      
    },
    removeToken() {
      this.isLogin = false
      this.token = ''
    }
  },
  persist: true
})
