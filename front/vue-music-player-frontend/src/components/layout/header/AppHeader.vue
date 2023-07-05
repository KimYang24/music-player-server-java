<template>
  <div class="flex items-center justify-between h-14">
    <!-- 导航栏相关 -->
    <div class="flex items-center pl-3">
      <!-- 左导航栏 -->
      <IconPark
        :icon="Left"
        :size="iconSize"
        :stroke-width="4"
        class="icon-button"
        @click="router.back()"
      />
      <!-- 右导航栏 -->
      <IconPark
        :icon="Right"
        :size="iconSize"
        :stroke-width="4"
        class="icon-button"
        @click="router.forward()"
      />
      <!-- 搜索框 -->
      <div class="ml-4">
        <SearchPop />
      </div>
    </div>

    <!-- 用户信息显示 -->
    <div class="flex items-center mr-5">
      <UserInfo />
      <IconPark :icon="Mail" :size="iconSize" :stroke-width="2" class="icon-button" />
      <DarkMode />
      <IconPark :icon="HamburgerButton" :size="iconSize" :stroke-width="2" class="icon-button" />
    </div>

    <InfoEdit></InfoEdit>
  </div>
</template>

<script setup lang="ts">
import { HamburgerButton, Left, Mail, Right } from '@icon-park/vue-next'
import { useRouter } from 'vue-router'
import IconPark from '@/components/common/IconPark.vue'
import UserInfo from '@/components/layout/header/UserInfo.vue'
import SearchPop from '@/components/layout/header/SearchPop.vue'
import DarkMode from '@/components/layout/header/DarkMode.vue'
import InfoEdit from './InfoEdit.vue'

const iconSize = 28

const router = useRouter()
</script>
<style lang="scss">
//搜索框

.icon-button {
  @apply p-2 cursor-pointer;
  @apply hover:text-emerald-400;
}
</style>
