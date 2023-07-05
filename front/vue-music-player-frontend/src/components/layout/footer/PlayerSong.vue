<template>
  <div class="flex player-song">
    <!-- 歌曲封面 -->
    <img
      alt=""
      class="w-11 h-11 rounded song_cover"
      :src="song.picUrl || albumLogo"
      @click="changePlayerShow"
    />
    <div class="ml-3 text-sm flex flex-col justify-between">
      <!-- 歌曲信息 -->
      <div class="w-52 2xl:w-96 cursor-pointer truncate">
        <div class="flex">
          <span>{{ song.name || '云音乐' }}</span>
          <span class="ml-2 text-dc">- {{ song.artist || `unknow artist` }}</span>
        </div>
      </div>
      <!-- 功能区 -->
      <div class="flex gap-x-3">
        <IconPark
          v-if="!isSongLike"
          :icon="Like"
          size="18"
          :stroke-width="3"
          class="text-gray-400 ml-3 mr-2 cursor-pointer hover:text-red-400"
          @click="addSongLike"
        />
        <IconPark
          v-else
          :icon="Like"
          size="18"
          :stroke-width="3"
          theme="filled"
          fill="#d0021b"
          @click="delSongListLike"
          class="text-gray-400 ml-3 mr-2 cursor-pointer"
        />
        <IconPark
          :icon="DownTwo"
          size="18"
          :stroke-width="3"
          class="text-slate-400 hover-text"
          @click="dwld"
        />
        <IconPark :icon="MoreTwo" size="18" :stroke-width="3" class="text-slate-400 hover-text" />
        <el-badge :value="1000" :max="999" class="badge">
          <IconPark :icon="Comment" size="18" :stroke-width="3" class="text-slate-400 hover-text" />
        </el-badge>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Like, DownTwo, MoreTwo, Comment } from '@icon-park/vue-next'
import { usePlayerStore } from '@/stores/player'
import albumLogo from '@/assets/images/albumLogo.png'
import IconPark from '@/components/common/IconPark.vue'
import { downloadSong } from '@/utils/download/dowmload'
import _ from 'lodash'
import { useAuthStore } from '@/stores/auth'
import { AlertError } from '@/utils/alert/AlertPop'
import { storeToRefs } from 'pinia'
import { useLikeStore } from '@/stores/like'
import type { LikeForm } from '@/models/like'
const { userId, isLogin } = storeToRefs(useAuthStore())
const { songs } = storeToRefs(useLikeStore())
const { addLike, delLike } = useLikeStore()

const { changePlayerShow } = usePlayerStore()

const { song } = toRefs(usePlayerStore())

const dwld = () => {
  if (song.value.url) {
    downloadSong(song.value)
  } else {
    AlertError('抱歉，这首歌暂时没有开放下载')
  }
}

const isSongLike = computed(() => {
  let index = _.findIndex(songs.value, (o) => {
    return o.songId == song.value.songId
  })
  return index == -1 ? false : true
})

const addSongLike = () => {
  if (isLogin.value) {
    const likeForm: LikeForm = {
      albumId: 0,
      userId: userId.value,
      songId: song.value.songId,
      artistId: 0,
      playListId: 0,
      type: 1 //歌曲
    }
    if (likeForm.songId != null) {
      addLike(likeForm, userId.value)
    }
  } else {
    AlertError('请先登录！')
  }
}

const delSongListLike = () => {
  if (isLogin.value) {
    const likeForm: LikeForm = {
      albumId: 0,
      userId: userId.value,
      songId: song.value.songId,
      artistId: 0,
      playListId: 0,
      type: 1 //歌曲
    }
    delLike(likeForm, userId.value)
  } else {
    AlertError('请先登录！')
  }
}
</script>

<style lang="scss">
.song_cover {
  @apply hover:opacity-70 cursor-pointer;
}

.player-song {
  .badge {
    //评论数量样式
    .el-badge__content {
      @apply scale-75 left-1 bg-stone-100 text-slate-500 bg-opacity-80 right-auto;
      @apply dark:bg-stone-900;
    }
  }
}
</style>
