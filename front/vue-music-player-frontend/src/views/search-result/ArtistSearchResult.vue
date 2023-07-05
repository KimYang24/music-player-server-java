<template>
  <div class="mt-7 mx-2">
    <!-- 歌手展示 -->
    <div class="grid grid-flow-row grid-cols-4 xl:grid-cols-5 2xl:grid-cols-6 gap-10">
      <div
        v-for="(artist, index) in artistSearchResult"
        :key="index"
        class="flex items-center flex-col"
        @click="routerPushByNameId(Pages.artistDetail, artist.artistId)"
      >
        <img
          :src="artist.picUrl"
          alt="歌手头像"
          class="rounded-full cursor-pointer w-full aspect-square object-cover bg-dc"
        />
        <div class="mt-2 text-sm">{{ artist.artist }}</div>
      </div>
    </div>
    <!-- 加载按钮 -->
    <div
      class="flex justify-center py-5"
      v-if="artistSearchResult.length > 0 && !pageData[2].noMore"
    >
      <el-button
        :loading="pageData[2].loading"
        link
        size="large"
        class="text-center"
        @click="pageGet('artist')"
        >加载更多
      </el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { routerPushByNameId } from '@/utils/navigator/router'
import { Pages } from '@/router/pages'
import { useSearchStore } from '@/stores/search'
import { storeToRefs } from 'pinia'

const { pageData, artistSearchResult } = storeToRefs(useSearchStore())
const { pageGet } = useSearchStore()

onMounted(async () => {
  if (pageData.value[2].page == 0) {
    pageGet('artist')
  }
})
</script>

<style lang="scss" scoped>
.active {
  @apply bg-emerald-400 text-white rounded;
}
</style>
