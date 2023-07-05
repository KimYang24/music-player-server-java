<template>
  <div class="mt-5 mx-5">
    <!-- 网格 -->
    <div class="grid grid-flow-row gap-6 grid-cols-4 2xl:grid-cols-6">
      <!-- 循环 -->
      <div
        v-for="(item, index) in albums"
        :key="index"
        @click="routerPushByNameId(Pages.albumDetail, item.albumId)"
      >
        <!-- 封面 -->
        <MyCover
          :name="item.album"
          :pic-url="item.picUrl"
          :play-count="item.size"
          :showPlayCount="true"
        />
        <!-- 专辑名、发行时间 -->
        <div class="mt-1 ml-1 text-sm text-main truncate">{{ item.album }}</div>
        <div class="text-sm ml-1 text-dc truncate">{{ item.publishTime || '未知' }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import MyCover from '@/components/common/MyCover.vue'
import { routerPushByNameId } from '@/utils/navigator/router'
import { Pages } from '@/router/pages'
import { storeToRefs } from 'pinia'
import { useAuthStore } from '@/stores/auth'
import { useLikeStore } from '@/stores/like'

const { updateLikes } = useLikeStore()
const { albums } = storeToRefs(useLikeStore())
const { userId } = storeToRefs(useAuthStore())

onMounted(async () => {
  updateLikes(userId.value)
})
</script>
<style lang="scss" scoped>
.el-button--large {
  @apply text-green-400;
}
</style>
