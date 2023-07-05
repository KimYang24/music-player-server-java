<template>
  <div class="mt-5 mb-3 flex items-center justify-between">
    <!-- 功能区 -->
    <div class="flex gap-x-3">
      <button class="w-28 button-outline" @click="playAll">
        <IconPark :icon="PlayOne" class="mr-1" size="20" />
        播放全部
      </button>
      <button class="w-28 button-outline">
        <IconPark :icon="DownloadFour" class="mr-1" size="18" />
        下载
      </button>
      <button class="w-28 button-outline">
        <IconPark :icon="ListSuccess" class="mr-1" size="17" />
        批量操作
      </button>
    </div>
  </div>

  <!-- 标签属性 -->
  <div class="mt-2">
    <div class="flex text-sm text-gray-400 py-2">
      <div class="flex-auto">歌曲</div>
      <div class="w-1/3">专辑</div>
      <div class="w-20">时长</div>
    </div>

    <!-- 歌曲组件 -->
    <div class="text-sm">
      <template v-for="(song, index) in songSearchResult" :key="index">
        <SongItem :prop-song="song" :order="index + 1" />
      </template>
    </div>

    <!-- 加载按钮 -->
    <div class="flex justify-center py-5" v-if="songSearchResult.length > 0 && !pageData[0].noMore">
      <el-button
        :loading="pageData[0].loading"
        link
        size="large"
        class="text-center"
        @click="pageGet('song')"
        >加载更多
      </el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { PlayOne, DownloadFour, ListSuccess } from '@icon-park/vue-next'
import SongItem from '@/components/common/SongItem.vue'
import IconPark from '@/components/common/IconPark.vue'
import { usePlayerStore } from '@/stores/player'
import { useSearchStore } from '@/stores/search'
import { storeToRefs } from 'pinia'

const { pushPlayList, play } = usePlayerStore()
const { pageGet } = useSearchStore()
const { pageData, songSearchResult } = storeToRefs(useSearchStore())

const playAll = () => {
  pushPlayList(true, ...songSearchResult.value)
  play(songSearchResult.value[0].songId)
}

onMounted(async () => {
  if (pageData.value[0].page == 0) {
    pageGet('song')
  }
})
</script>
<style lang="scss" scoped>
.el-button--large {
  @apply text-green-400;
}
</style>
