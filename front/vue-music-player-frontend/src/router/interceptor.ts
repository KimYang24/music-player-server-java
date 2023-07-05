import { AlertRouterWarning } from '@/utils/alert/AlertPop'

import router from './index'
import { useAuthStore } from '@/stores/auth'
import { Pages, PagesAuth } from './pages'

// 全局前置守卫（全局钩子函数）
router.beforeEach((to, from, next) => {
  /* 
    在导航之前执行的逻辑或操作
    可以根据需要进行身份验证、权限检查等 
    */

  const isLogin = useAuthStore().$state.isLogin
  //如果访问未定义界面
  if (to.name == undefined || !(to.name in Pages)) {
    AlertRouterWarning('You are visiting a page that is not exsit!')
    next({ name: Pages.page404 })
  }
  //未登录
  else if (!isLogin) {
    if (to.name in PagesAuth) {
      AlertRouterWarning('You need login before visiting!')
      next({ name: Pages.discover })
    } else {
      next()
    }
  }
  //放行
  else next()
})

//全局后置钩子
router.afterEach((to, from) => {
  console.log('路由成功导航到' + to.fullPath)
})
