<template>
  <div class="mt-6 mx-5">
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
        <button class="w-40 button-outline" @click="clearDwld">
          <IconPark :icon="Delete" class="mr-1" size="17" />
          清除最近播放
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
        <template v-for="(song, index) in dwldSongs" :key="index">
          <SongItem :prop-song="song" :order="index + 1" />
        </template>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { PlayOne, DownloadFour, Delete } from '@icon-park/vue-next'
import SongItem from '@/components/common/SongItem.vue'
import IconPark from '@/components/common/IconPark.vue'
import { usePlayerStore } from '@/stores/player'
import { storeToRefs } from 'pinia'
import { useDwldStore } from '@/stores/download'

const { pushPlayList, play } = usePlayerStore()
const { dwldSongs } = storeToRefs(useDwldStore())
const { addDwlnSongs, clearDwld } = useDwldStore()

const playAll = () => {
  pushPlayList(true, ...dwldSongs.value)
  play(dwldSongs.value[0].songId)
}
</script>
<style lang="scss" scoped>
.el-button--large {
  @apply text-green-400;
}
</style>
