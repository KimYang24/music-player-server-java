<template>
  <!-- 头像 -->
  <div class="flex items-center cursor-pointer hover-text">
    <!-- popper -->
    <el-popover
      :width="300"
      :disabled="!isLogin"
      popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
    >
      <!-- 头像框 -->
      <template #reference>
        <ElAvatar size="default" round class="bg-gray-200" :src="profile?.picUrl">
          <!-- 加载失败时展示 -->
          <img src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
        </ElAvatar>
      </template>

      <!-- 弹出内容 -->
      <template #default>
        <div class="demo-rich-conent" style="display: flex; gap: 16px; flex-direction: column">
          <el-avatar
            :size="60"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            style="margin-bottom: 8px"
          />

          <!-- 文字信息 -->
          <div>
            <p class="demo-rich-content__name" style="margin: 0; font-weight: 500">
              {{ profile.nickname }}
            </p>
            <p
              class="demo-rich-content__mention"
              style="margin: 0; font-size: 14px; color: var(--el-color-info)"
            >
              {{ profile.email }}
            </p>
          </div>

          <p class="demo-rich-content__desc" style="margin: 0">
            欢迎您来到QQ音乐网页版(仿),本网站中山大学软件工程学院学生开发！
          </p>
          <div class="flex items-center flex-auto space-x-2">
            <button
              class="button-outline w-40"
              @click="routerPushByNameId(Pages.userProfile, userId)"
            >
              用户中心
            </button>
            <button class="button-outline w-40" @click="logout">退出登录</button>
          </div>
        </div>
      </template>
    </el-popover>

    <span class="text-sm mx-2" v-if="isLogin">{{ profile.nickname }}</span>
    <span class="text-sm mx-2" @click="showLogin = true" v-else>点击登录</span>
  </div>

  <!-- 登录框 -->
  <AppLogin></AppLogin>
  <!-- 注册框 -->
  <AppResgister></AppResgister>
</template>

<script setup lang="ts">
import { useAuthStore } from '@/stores/auth'
import { storeToRefs } from 'pinia'
import AppLogin from '../auth/AppLogin.vue'
import AppResgister from '../auth/AppResgister.vue'
import { routerPushByNameId } from '@/utils/navigator/router'
import { Pages } from '@/router/pages'

const { checkLogin, logout } = useAuthStore()
const { isLogin, profile, showLogin, userId } = storeToRefs(useAuthStore())

onMounted(() => {
  checkLogin()
})
</script>

<style lang="scss"></style>
