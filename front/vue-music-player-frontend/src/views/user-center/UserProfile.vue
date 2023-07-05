<template>
  <div aria-label="user-center" class="p-9 pt-5">
    <el-page-header @back="onBack" title="退出登录" :icon="Logout">
      <!-- 用户头像 -->
      <template #content>
        <div class="flex items-center">
          <el-avatar
            class="mr-3"
            :size="75"
            :src="
              profile.picUrl ||
              'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
            "
          />
          <!-- 用户名 -->
          <span class="text-3xl font-600 mx-3"> {{ profile.nickname }} </span>
          <span class="text-base mr-2" style="color: var(--el-text-color-regular)">
            {{ profile.email }}
          </span>
        </div>
      </template>

      <!-- btn -->
      <template #extra>
        <div class="flex items-center">
          <div class="flex items-center">
            <button class="mr-3 button-outline w-28 h-10" @click="showModify = true">
              编辑信息
            </button>
          </div>
          <div class="flex items-center">
            <button class="mr-3 button-outline w-28 h-10" @click="router.push({ name: 'vip' })">
              开通VIP
            </button>
          </div>
          <div class="flex items-center">
            <button class="button-outline w-28 h-10" @click="router.push({ name: Pages.contact })">
              联系我们
            </button>
          </div>
        </div>
        <EditProfile></EditProfile>
      </template>

      <!-- 描述 -->
      <el-descriptions :column="2" size="large" class="mt-6 w-full">
        <RouterView> </RouterView>
        <el-descriptions-item label="用户名">
          <span class="text-2xl"> {{ profile.username }} </span>
        </el-descriptions-item>
        <el-descriptions-item label="昵称">
          {{ profile.nickname }}
        </el-descriptions-item>
        <el-descriptions-item label="性别">
          {{ profile.gender }}
        </el-descriptions-item>
        <el-descriptions-item label="年龄">
          {{ profile.age }}
        </el-descriptions-item>
        <el-descriptions-item label="电话号码">
          {{ profile.phone }}
        </el-descriptions-item>
        <el-descriptions-item label="邮箱">
          {{ profile.email }}
        </el-descriptions-item>
        <el-descriptions-item label="Remarks">
          <el-tag>普通用户</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="Address"
          >No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province
        </el-descriptions-item>
      </el-descriptions>
      <div class="mt-6 text-xl">欢迎您来到QQ音乐网页版(仿),本网站中山大学软件工程学院学生开发!</div>
    </el-page-header>
  </div>
</template>

<script setup lang="ts">
import { Logout } from '@icon-park/vue-next'
import { useAuthStore } from '@/stores/auth'
import { storeToRefs } from 'pinia'
import { Pages } from '@/router/pages'

const { profile, showModify } = storeToRefs(useAuthStore())
const { logout, checkLogin } = useAuthStore()
const router = useRouter()

const onBack = () => {
  logout()
}
onMounted(() => {
  checkLogin()
})
</script>
