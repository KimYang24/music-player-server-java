<template>
  <div class="mt-8 mx-5">
    <!-- 网格 -->
    <div class="grid grid-flow-row gap-6 grid-cols-4 2xl:grid-cols-6">
      <!-- 循环 -->
      <div
        v-for="(item, index) in albumSearchResult"
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

    <!-- 加载按钮,还有页就显示 -->
    <div
      class="flex justify-center py-5"
      v-if="albumSearchResult.length > 0 && !pageData[1].noMore"
    >
      <el-button
        :loading="pageData[1].loading"
        link
        size="large"
        class="text-center"
        @click="pageGet('album')"
        >加载更多
      </el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import MyCover from '@/components/common/MyCover.vue'
import { routerPushByNameId } from '@/utils/navigator/router'
import { Pages } from '@/router/pages'
import { useSearchStore } from '@/stores/search'
import { storeToRefs } from 'pinia'

const { pageData, albumSearchResult } = storeToRefs(useSearchStore())
const { pageGet } = useSearchStore()

onMounted(async () => {
  if (pageData.value[1].page == 0) {
    pageGet('album')
  }
})
</script>
<style lang="scss" scoped>
.el-button--large {
  @apply text-green-400;
}
</style>
