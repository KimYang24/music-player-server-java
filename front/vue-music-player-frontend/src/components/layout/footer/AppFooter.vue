<template>
  <!-- 垂直布局，伸展 -->
  <div class="flex flex-col items-stretch h-20">
    <!-- 进度条 -->
    <PlayerSlider />
    <!-- 功能信息区 -->
    <div class="flex grow px-4 items-center mb-1">
      <!-- 歌曲信息显示 -->
      <div class="flex-1">
        <PlayerSong />
      </div>
      <!-- 播放按钮 -->
      <div class="flex-1">
        <PlayerController />
      </div>
      <!-- 播放信息显示 -->
      <div class="flex-1">
        <PlayerAction />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import PlayerSlider from '@/components/layout/footer/PlayerSlider.vue'
import PlayerSong from '@/components/layout/footer/PlayerSong.vue'
import PlayerAction from '@/components/layout/footer/PlayerAction.vue'
import PlayerController from '@/components/layout/footer/PlayerController.vue'
</script>
<style lang="scss"></style>
