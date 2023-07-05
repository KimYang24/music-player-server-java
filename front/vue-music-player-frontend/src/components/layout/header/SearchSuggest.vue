<template>
  <div v-if="suggestData">
    <!-- 主体 -->
    <div v-for="(types, index) in { 0: 'songs', 1: 'artists', 2: 'albums' }" :key="index">
      <!-- 推荐搜索条目是什么类型 -->
      <div class="pt-2 pb-1.5 px-2.5">{{ getTitle(types) }}</div>

      <!-- 歌曲条目 -->
      <div v-if="types == 'songs'">
        <div
          v-for="item in suggestData.songs"
          :key="item.songId"
          class="suggestItem hover-bg-main"
          @click="play(item.songId)"
        >
          <!-- 歌曲名 -->
          <span class="text-emerald-500">{{ item.name }}</span>
          <span class="pl-1"> - {{ item.artist }}</span>
        </div>
      </div>

      <!-- 专辑条目 -->
      <div v-if="types == 'albums'">
        <div
          v-for="item in suggestData.albums"
          :key="item.albumId"
          class="suggestItem hover-bg-main"
          @click="jump(Pages.albumDetail, item.albumId)"
        >
          <span class="text-emerald-500">{{ item.album }}</span>
          <span class="pl-1"> - {{ item.artist }}</span>
        </div>
      </div>

      <!-- 歌手条目 -->
      <div v-if="types == 'artists'">
        <div
          v-for="item in suggestData.artists"
          :key="item.artistId"
          class="suggestItem hover-bg-main"
          @click="jump(Pages.artistDetail, item.artistId)"
        >
          <span class="text-emerald-500">{{ item.artist }}</span>
          <span class="text-emerald-500">-歌曲数：{{ item.songSize }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { useSearchStore } from '@/stores/search'
import { usePlayerStore } from '@/stores/player'
import { routerPushByNameId } from '@/utils/navigator/router'
import { Pages } from '@/router/pages'
const { suggestData, showSearchView } = storeToRefs(useSearchStore())
const { play } = usePlayerStore()

//显示类型
const getTitle = (name: string) => {
  switch (name) {
    case 'songs':
      return '单曲'
    case 'albums':
      return '专辑'
    case 'artists':
      return '歌手'
    default:
      return name
  }
}

//跳转
const jump = (name: string, id: number) => {
  routerPushByNameId(name, id)
  showSearchView.value = false
}
</script>

<style lang="scss" scoped>
.suggestItem {
  @apply py-1.5 px-2.5 text-xs cursor-pointer;
}
</style>
