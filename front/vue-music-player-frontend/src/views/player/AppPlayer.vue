<template>
  <div>
    <el-drawer
      v-model="showPlayWindow"
      :direction="direction"
      :append-to-body="true"
      :show-close="false"
      size="100%"
      :with-header="false"
      class="player"
    >
      <div class="w-screen h-screen flex flex-col overflow-hidden">
        <!-- 关闭按钮 -->
        <div class="hover-text h-8" @click="changePlayerShow">
          <IconPark :icon="Down" :size="35"></IconPark>
        </div>
        <!-- 主体显示部分 -->
        <div class="flex-1">
          <PlayerInfo />
        </div>
        <!-- 底部播放控件 -->
        <div class="h-24 flex-col flex justify-center">
          <PlayerController />
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script setup lang="ts">
import IconPark from '@/components/common/IconPark.vue'
import PlayerController from './PlayerControl.vue'
import PlayerInfo from './PlayerInfo.vue'
import { usePlayerStore } from '@/stores/player'
import { Down } from '@icon-park/vue-next'
import { storeToRefs } from 'pinia'

const { showPlayWindow } = storeToRefs(usePlayerStore())
const { changePlayerShow } = usePlayerStore()
const direction = ref('btt')
</script>

<style lang="scss">
.player {
  .el-drawer__body {
    padding: 0px;
  }
  //渐变色
  background: #8360c3; /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #8360c3, #2ebf91); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to right,
    #8360c3,
    #2ebf91
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
</style>
