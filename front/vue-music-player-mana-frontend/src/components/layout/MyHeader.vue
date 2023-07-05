<template>
  <div class="header">
    <!--页面名称-->
    <div class="left">
      <span style="font-size: 30px">{{ state.name }}</span>
    </div>
    <!--退出按钮-->
    <div class="right">
      <el-popover placement="bottom" :width="300" trigger="click" popper-class="popper-user-box">
        <template #reference>
          <div class="author">
            <el-icon><Avatar /></el-icon>
            <span class="tip">你好！{{ state.userInfo.loginUserName }}</span>
            <el-icon><ArrowDown /></el-icon>
          </div>
        </template>
        <div class="nickname">
          <p>登录名：{{ state.userInfo.loginUserName }}</p>
          <el-tag size="small" effect="dark" class="logout" @click="logout">退出登录</el-tag>
        </div>
      </el-popover>
    </div>
  </div>
</template>

<script setup lang="ts">
import { pathMap } from '@/router/pathMap'
import { useAuthStore } from '@/stores/auth'
import { onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { getUserInfo } from '@/utils/api/user'
import type { UserInfo } from '@/model/UserInfo'
import { storeToRefs } from 'pinia'

const authStore = useAuthStore()
const router = useRouter()
const { userId } = storeToRefs(useAuthStore())

// watch router change,显示页面名称
router.afterEach((to) => {
  console.log('to', to)
  state.name = pathMap[to.name as keyof typeof pathMap]
})

//显示登录用户的信息
const state = reactive({
  name: '',
  userInfo: {} as UserInfo
})

// onMounted加载,在组件被挂载后，根据URL的哈希值判断是否为登录页面
onMounted(() => {
  const pathname = window.location.hash.split('/')[1] || ''
  if (!['login'].includes(pathname)) {
    getUserInfo(userId.value).then((userInfo) => {
      state.userInfo = userInfo
      console.log('Received userInfo:', userInfo)
    })
  }
})

// 退出登录
const logout = () => {
  authStore.removeToken()
  // 回到登录页
  router.push({ path: '/login' })
}
</script>

<style scoped>
.header {
  height: 50px;
  border-bottom: 4px solid gray;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 10px;
}

.right > .author {
  font-size: 20px;
}
.author {
  margin-left: 10px;
  cursor: pointer;
}
</style>

<!--注意这些弹出式组件不能使用style scope,因为他们定义在app外面！-->
<style>
.tip {
  margin-right: 10px;
}

.popper-user-box {
  background: url('https://s.yezgea02.com/lingling-h5/static/account-banner-bg.png') 50% 50%
    no-repeat !important;
  background-size: cover !important;
  border-radius: 0 !important;
}
.popper-user-box .nickname {
  position: relative;
  color: #ffffff;
}
.popper-user-box .nickname .logout {
  position: absolute;
  right: 0;
  top: 0;
  cursor: pointer;
}
</style>
