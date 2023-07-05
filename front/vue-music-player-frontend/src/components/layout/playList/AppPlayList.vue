<template>
  <!-- 抽屉组件 -->
  <el-drawer v-model="showPlayList" :with-header="false" size="340px" class="play-list">
    <div class="h-screen flex flex-col">
      <div class="p-2.5 flex-shrink-0">
        <div class="text-2xl">播放列表</div>
        <div class="text-sm mt-2 flex justify-between items-center">
          <div>共 {{ playListCount }} 首歌曲</div>
          <div class="text-dc flex items-center hover-text" @click="clearPlayList">
            <IconPark :icon="Delete" />
            <span class="ml-1">清空</span>
          </div>
        </div>
      </div>
      <!-- 隐藏未加载 -->
      <div class="flex-1 overflow-hidden">
        <el-scrollbar>
          <PlayListItem
            v-for="item in playList"
            :key="item.songId"
            :song="item"
            :active="item.songId === song.songId"
            @dblclick="play(item.songId)"
          />
        </el-scrollbar>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { Delete } from '@icon-park/vue-next'
import { usePlayerStore } from '@/stores/player'
import IconPark from '@/components/common/IconPark.vue'
import PlayListItem from '@/components/layout/playList/PlayListItem.vue'

const { showPlayList, getPlayListCount: playListCount, playList } = storeToRefs(usePlayerStore())
const { play, clearPlayList, song } = usePlayerStore()
</script>
<style lang="scss">
.play-list {
  .el-drawer__body {
    padding: 0px;
  }
}
</style>
