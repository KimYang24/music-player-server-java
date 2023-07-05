<template>
  <div>
    <div class="mt-1 mb-3 flex items-center justify-between">
      <!-- 功能区 -->
      <div class="flex gap-x-3">
        <button class="w-28 button-outline" @click="playAll">
          <IconPark :icon="PlayOne" class="mr-1" size="20" />
          播放全部
        </button>
        <button class="w-28 button-outline">
          <IconPark :icon="DownloadFour" class="mr-1" size="18" />
          下载
        </button>
        <button class="w-28 button-outline">
          <IconPark :icon="ListSuccess" class="mr-1" size="17" />
          批量操作
        </button>
      </div>
    </div>

    <!-- 标签属性 -->
    <div class="mt-2">
      <div class="flex text-sm text-gray-400 py-2">
        <div class="flex-auto">歌曲</div>
        <div class="w-1/4">歌手</div>
        <div class="w-1/4">专辑</div>
      </div>

      <!-- 歌曲组件 -->
      <div class="text-sm">
        <template v-for="(song, index) in songs" :key="index">
          <SongItemWithEdit :prop-song="song" :order="index + 1" />
        </template>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { PlayOne, DownloadFour, ListSuccess } from '@icon-park/vue-next'
import IconPark from '@/components/common/IconPark.vue'
import { usePlayerStore } from '@/stores/player'
import { storeToRefs } from 'pinia'
import { useAuthStore } from '@/stores/auth'
import { useLikeStore } from '@/stores/like'
import SongItemWithEdit from '@/components/common/SongItemWithEdit.vue'

const { updateLikes } = useLikeStore()
const { songs } = storeToRefs(useLikeStore())
const { userId } = storeToRefs(useAuthStore())
const { pushPlayList, play } = usePlayerStore()

const playAll = () => {
  pushPlayList(true, ...songs.value)
  play(songs.value[0].songId)
}

onMounted(async () => {
  updateLikes(userId.value)
})
</script>
<style lang="scss" scoped>
.el-button--large {
  @apply text-green-400;
}
</style>
