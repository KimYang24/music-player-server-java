<template>
  <!--右下角功能区-->
  <div class="flex justify-end items-center gap-x-3">
    <!-- 进度条显示 -->
    <span class="text-sm">
      {{ numberToDuration(currentTime) }} / {{ numberToDuration(duration) }}
    </span>
    <!-- 显示歌词 -->
    <IconPark :icon="TextMessage" size="22" :stroke-width="3" class="hover-text" title="歌词" />
    <!-- 播放列表显示 -->
    <div class="flex items-center hover-text" @click="showPlayList = true">
      <IconPark :icon="MusicList" size="22" :stroke-width="3" class="hover-text" title="播放列表" />
      <!-- 播放列表歌曲数量 -->
      <span class="text-xs">{{ playListCount }}</span>
    </div>
  </div>
</template>

<script setup lang="ts">
import { MusicList, TextMessage } from '@icon-park/vue-next'
import { numberToDuration } from '@/utils/number/number'
import { usePlayerStore } from '@/stores/player'
import IconPark from '@/components/common/IconPark.vue'
import { storeToRefs } from 'pinia'

const {
  currentTime,
  duration,
  getPlayListCount: playListCount,
  showPlayList
} = storeToRefs(usePlayerStore())
</script>

<style lang="scss"></style>
