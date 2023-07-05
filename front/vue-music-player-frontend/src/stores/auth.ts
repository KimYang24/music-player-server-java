import { defineStore } from 'pinia'
import type { UserProfile, registerUser } from '@/models/user'
import { apiLogin, apiLoginStatus, apiRegister } from '@/utils/api/auth'
import { AlertError, AlertSuccess, AlertMsgWarning } from '@/utils/alert/AlertPop'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    //0则为未登录
    userId: 0,
    isLogin: false,
    token: '',
    //是否显示登录注册框
    showRegister: false,
    showModify: false,
    showLogin: false,
    //用户信息
    profile: {} as UserProfile,
    //登录表单
    ruleForm: {} as registerUser
  }),
  getters: {},
  actions: {
    async login(phone: string, password: string) {
      const res = await apiLogin(phone, password)
      //关闭窗口
      this.showLogin = false
      this.showRegister == false
      //逻辑判断
      if (res.code == 200) {
        AlertSuccess('你已成功登录')
        this.token = res.token
        this.userId = res.userId
        this.isLogin = true
        await this.checkLogin()
        this.isLogin = true
      } else if (res.code == 300) {
        AlertError('用户名不存在')
      } else if (res.code == 301) {
        AlertError('密码错误')
      } else {
        AlertError('服务器错误')
      }
    },
    async register(ruleForm: registerUser) {
      this.showRegister = false
      const res = await apiRegister(ruleForm)
      //关闭窗口
      this.showLogin = false
      this.showRegister == false
      //逻辑判断
      if (res.code == 200) {
        AlertSuccess('你已成功注册')
        this.token = res.token
        await this.checkLogin()
        this.isLogin = true
      } else if (res.code == 300) {
        AlertError('手机号已存在')
      } else {
        AlertError('服务器错误')
      }
    },

    //更新一下个人信息
    async checkLogin() {
      const res = await apiLoginStatus(this.userId)
      if (res.code == 200) {
        this.profile = res.profile
      } else {
        AlertError('获取用户信息失败')
      }
    },

    //退出登录
    async logout() {
      AlertMsgWarning('确定要退出登录吗？')
        .then(() => {
          AlertSuccess('操作成功')
          this.isLogin = false
          this.userId = 0
          this.token = ''
          this.profile = {} as UserProfile
        })
        .catch(() => {})
    }
  },
  //持久化
  persist: true
})
