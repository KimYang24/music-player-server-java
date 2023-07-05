<template>
  <div class="cover-play-image" :class="{ 'aspect-square': !video, 'aspect-video': video }">
    <!-- 图片 -->
    <el-image :src="picUrl" :alt="name" class="w-full bg-gray-50 object-cover" />
    <div class="mask flex justify-center items-center">
      <IconPark
        :icon="PlayOne"
        size="70"
        theme="filled"
        class="text-white play-icon opacity-0 transition-opacity hover:text-zinc-300"
        @click="onPlay!"
      />
    </div>
    <div v-if="showPlayCount" class="play-count">
      <IconPark :icon="video ? Play : hot ? Headset : Record" class="mr-2" :size="18" />
      <text class="text-sm">
        {{ video ? numberToDuration(playCount || 0) : playCount }}
      </text>
    </div>
  </div>
</template>

<script setup lang="ts">
import { PlayOne, Play, Record, Headset } from '@icon-park/vue-next'
import { numberToDuration } from '@/utils/number/number'
import IconPark from '@/components/common/IconPark.vue'

defineProps<{
  picUrl?: string
  playCount?: number //mv时长或专辑歌曲数量
  name?: string
  showPlayCount?: boolean
  onPlay?: () => void
  video?: boolean //是否是mv
  hot?: boolean //是否显示听众
}>()
</script>

<style lang="scss" scoped>
.cover-play-image {
  @apply rounded-xl cursor-pointer transition-all relative overflow-hidden;
  //位置移动
  @apply hover:-translate-y-1.5;
  //设置延迟时间
  .mask {
    @apply absolute inset-0 bg-black bg-opacity-0 transition-all duration-1000;
  }
  //显示时长
  .play-count {
    @apply absolute bottom-2 right-2 text-gray-100 text-sm bg-zinc-950 bg-opacity-70 rounded-full px-2 pt-1 pb-0.5 flex items-center transition-all scale-90;
  }
  //悬浮时
  &:hover {
    .mask {
      @apply bg-opacity-50;
      .play-icon {
        @apply opacity-100;
      }
    }
    .play-count {
      @apply opacity-0;
    }
  }
}
</style>
