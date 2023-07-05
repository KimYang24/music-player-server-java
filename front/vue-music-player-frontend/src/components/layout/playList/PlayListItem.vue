<template>
  <div
    class="flex p-2.5 border-b border-b-stone-50 dark:border-b-stone-800 hover-bg-view"
    :class="{ active: active }"
  >
    <!-- 歌曲封面 -->
    <el-image lazy :src="song.picUrl" class="aspect-square w-10 flex-shrink-0" />
    <!-- 歌曲信息 -->
    <div class="ml-2 text-xs h-10 flex flex-1 w-1">
      <div class="flex flex-1 flex-col justify-between truncate">
        <div class="truncate">{{ song.name }}</div>
        <div class="truncate">{{ song.artist }}</div>
      </div>
      <div class="flex-shrink-0 ml-5 flex items-center justify-end">
        <div class="truncate">
          <small>{{ numberToDuration(song.duration || 0) }}</small>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Song } from '@/models/song'
import { numberToDuration } from '@/utils/number/number'

defineProps<{
  song: Song
  active: boolean
}>()
</script>
<style lang="scss" scoped>
.active {
  @apply border-l-2 border-l-emerald-400 text-emerald-400;
}
</style>
