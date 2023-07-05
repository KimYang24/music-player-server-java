<template>
  <div class="flex items-center justify-center gap-x-4">
    <!-- 播放模式按钮 -->
    <IconPark
      :icon="loopType === 0 ? PlayOnce : loopType === 1 ? LoopOnce : ShuffleOne"
      size="22"
      :stroke-width="3"
      class="hover-text"
      @click="toggleLoop"
    />
    <!-- 上一曲按钮 -->
    <IconPark :icon="GoStart" size="30" theme="filled" class="hover-text" @click="prev" />
    <!-- 播放暂停按钮 -->
    <IconPark
      :icon="isPlaying ? PauseOne : Play"
      size="45"
      theme="filled"
      class="hover-text text-emerald-500"
      @click="togglePlay"
    />
    <!-- 下一曲按钮 -->
    <IconPark :icon="GoEnd" size="30" class="hover-text" @click="next" />
    <!-- 声音调整按钮 -->
    <el-popover placement="top" width="45px">
      <template #reference>
        <!-- 声音按钮 -->
        <IconPark
          :icon="muted ? VolumeMute : VolumeSmall"
          size="22"
          :stroke-width="3"
          @click="toggleMuted"
          class="hover-text"
        />
      </template>
      <!-- 拖动栏 -->
      <PlayerVolumeSlider />
    </el-popover>
  </div>
</template>

<script setup lang="ts">
import {
  Play,
  PauseOne,
  LoopOnce,
  ShuffleOne,
  PlayOnce,
  GoEnd,
  GoStart,
  VolumeSmall,
  VolumeMute
} from '@icon-park/vue-next'
import IconPark from '@/components/common/IconPark.vue'
import PlayerVolumeSlider from '@/components/layout/footer/PlayerVolumeSlider.vue'
import { usePlayerStore } from '@/stores/player'
import { storeToRefs } from 'pinia'

const { loopType, muted, isPlaying } = storeToRefs(usePlayerStore())
const { toggleLoop, nextPlay: next, prev, togglePlay, toggleMuted } = usePlayerStore()
</script>

<style lang="scss">
.el-popover.el-popper {
  min-width: auto;
}
</style>
