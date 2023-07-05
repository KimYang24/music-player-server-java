<template>
  <div class="flex flex-col items-center pt-2">
    <!-- 拖动条 -->
    <div>
      <el-slider
        vertical
        height="100px"
        :show-tooltip="false"
        v-model="volume"
        :max="100"
        :min="0"
        size="small"
        :disabled="muted"
        @input="setVolume"
      />
    </div>
    <!-- 音量数字 -->
    <div class="text-sm mt-2 flex-shrink-0 w-6 ml-1.5">{{ volume }}</div>
  </div>
</template>

<script setup lang="ts">
import { usePlayerStore } from '@/stores/player'

const { volume, muted, setVolume } = toRefs(usePlayerStore())
</script>

<style lang="scss">
.el-popover.el-popper {
  @apply bg-gray-100;
  @apply dark:bg-stone-800;
}
</style>
