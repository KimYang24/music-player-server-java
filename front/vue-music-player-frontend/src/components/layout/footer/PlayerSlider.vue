<template>
  <div class="player-slider">
    <el-slider
      :show-tooltip="false"
      :min="0"
      v-model="currentTime"
      :max="duration"
      @change="onSliderChange"
    />
  </div>
</template>

<script setup lang="ts">
import { usePlayerStore } from '@/stores/player'
import { storeToRefs } from 'pinia'

const { duration, currentTime } = storeToRefs(usePlayerStore())
const { onSliderChange } = usePlayerStore()
</script>

<style lang="scss" scoped>
//拖动条样式
.player-slider {
  .el-slider {
    height: 10px;
  }
}
</style>
