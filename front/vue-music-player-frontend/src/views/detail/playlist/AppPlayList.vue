<template>
  <div class="playlist">
    <div class="p-5" v-if="playlist">
      <PlayListInfo :playlist="playlist" :play-all="playAll" />

      <!-- 标签页 -->
      <el-tabs class="mt-3" v-model="tab">
        <!-- 歌曲 -->
        <el-tab-pane lazy :label="`歌曲 ${playlist.size}`" name="songs">
          <PlatListSongs :songs="songs" />
        </el-tab-pane>
        <!-- 评论 -->
        <el-tab-pane lazy :label="`评论 ${playlist.mark}`" name="comments"> </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script setup lang="ts">
import { apiPlayListDetail } from '@/utils/api/playlist'
import PlayListInfo from './PlayListInfo.vue'
import PlatListSongs from '@/views/detail/playlist/PlatListSongs.vue'
import type { PlayList } from '@/models/playlist'
import type { Song } from '@/models/song'
import { usePlayerStore } from '@/stores/player'
import { AlertError } from '@/utils/alert/AlertPop'
import _ from 'lodash'
const tab = ref('songs')

const route = useRoute()
const playlist = ref<PlayList>()
const songs = ref<Song[]>([])

const { pushPlayList, play } = usePlayerStore()

const playAll = () => {
  if (songs.value.length) {
    pushPlayList(true, ...songs.value)
    play(_.first(songs.value)!.songId)
  } else {
    AlertError('歌单为空')
  }
}

onMounted(async () => {
  //获取动态路由信息
  const id: number = Number(route.query.id)
  //请求数据
  const res = await apiPlayListDetail(id)
  if (res.code == 200) {
    playlist.value = res.playlist
    songs.value = res.songs
  } else {
    AlertError('获取歌单失败')
  }
})
</script>

<style lang="scss">
.playlist {
  .el-tabs__nav-wrap::after {
    height: 0;
  }

  .el-tabs__header {
    @apply m-0;
  }
}
</style>
