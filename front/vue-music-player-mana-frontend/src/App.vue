<script setup lang="ts">
import MyHeader from '@/components/layout/MyHeader.vue'
import MyFotter from '@/components/layout/MyFotter.vue'
import MyMenu from '@/components/layout/MyMenu.vue'
import { useRouter } from 'vue-router'
import { pathMap } from '@/router/pathMap'
import { useAuthStore } from './stores/auth'

//登录相关逻辑
const router = useRouter()
const authStore = useAuthStore()

//若未登录则跳转登录
router.beforeEach((to, from, next) => {
  if (to.path == '/login') {
    // 如果路径是 /login 则正常执行
    next()
  } else {
    //判断是否已登录
    if (authStore.getAuthStatus.isLogin == true) {
      console.log('已登录！')
      next()
    } else {
      //没有则跳至登录页面
      console.log('未登录！')
      next({ path: '/login' })
    }
  }
  //设置网页名称
  document.title = pathMap[to.name as keyof typeof pathMap]
})
</script>

<template>
  <div id="app">
    <div>
      <el-container v-if="authStore.getAuthStatus.isLogin" class="container">
        <MyMenu></MyMenu>
        <!--main container-->
        <el-container class="content">
          <el-header>
            <MyHeader></MyHeader>
          </el-header>
          <el-main class="main">
            <RouterView></RouterView>
          </el-main>
          <el-footer>
            <MyFotter></MyFotter>
          </el-footer>
        </el-container>
      </el-container>
      <el-container v-else>
        <RouterView></RouterView>
      </el-container>
    </div>
  </div>
</template>

<style scoped>
.content {
  display: flex;
  height: 100%;

  overflow-y: hidden;
}

.main {
  height: 100%;
  overflow-y: auto;
  padding: 30px;
}
</style>
