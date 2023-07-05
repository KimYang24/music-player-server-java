import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    //大盘数据
    {
      path: '/',
      name: 'welcome',
      component: () => import('@/views/data-analyse/DataAnalyse.vue')
    },
    //登录
    {
      path: '/login',
      component: () => import('@/views/login/MyLogin.vue'),
      name: 'login'
    },

    //歌曲相关
    //歌曲编辑
    {
      path: '/songEdit',
      component: () => import('@/views/song-mana/SongEdit.vue'),
      name: 'songEdit'
    },
    //专辑编辑
    {
      path: '/albumEdit',
      component: () => import('@/views/song-mana/AlbumEdit.vue'),
      name: 'albumEdit'
    },
    //歌手编辑
    {
      path: '/singerEdit',
      component: () => import('@/views/song-mana/SingerEdit.vue'),
      name: 'singerEdit'
    },

    //用户相关

    //用户管理
    {
      path: '/userEdit',
      component: () => import('@/views/user-mana/UserEdit.vue'),
      name: 'userEdit'
    },
    //管理员管理
    {
      path: '/adminEdit',
      component: () => import('@/views/user-mana/AdminEdit.vue'),
      name: 'adminEdit'
    },
    //用户收藏管理
    {
      path: '/userLikeEdit',
      component: () => import('@/views/user-mana/UserLikeEdit.vue'),
      name: 'userLikeEdit'
    },

    //日志相关
    //登录日志
    {
      path: '/loginHistory',
      component: () => import('@/views/log-mana/DownloadHistory.vue'),
      name: 'loginHistory'
    },
    //注册日志
    {
      path: '/registerHistory',
      component: () => import('@/views/log-mana/LogInRegisterHistory.vue'),
      name: 'registerHistory'
    },
    //播放日志
    {
      path: '/playHistory',
      component: () => import('@/views/log-mana/PlayHistory.vue'),
      name: 'playHistory'
    }
  ]
})

export default router
