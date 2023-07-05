<template>
  <!-- 标签 -->
  <div class="flex text-base text-gray-400 py-2">
    <div class="flex-auto">歌曲</div>
    <div class="w-1/3">专辑</div>
    <div class="w-20">时长</div>
  </div>
  <!-- 歌曲条目 -->
  <div v-for="(item, index) in songList" :key="index">
    <SongItem :prop-song="item" :order="index + 1" />
  </div>
</template>

<script setup lang="ts">
import type { Song } from '@/models/song'

defineProps<{
  songList: Song[]
}>()
</script>
<style lang="scss"></style>
