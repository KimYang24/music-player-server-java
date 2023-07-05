<template>
  <div
    class="flex song-item items-center w-full hover-bg-main"
    :class="{ playing: id == propSong.songId }"
    @dblclick="play(propSong.songId)"
  >
    <!-- 左部 -->
    <div class="flex-shrink-0 flex-1 flex items-center justify-between pr-5">
      <!-- 歌曲编号 -->
      <div class="text-sm ml-0.5">
        <span>{{ _.padStart(order.toString(), 2, '0') }}</span>
      </div>
      <!-- 喜欢按钮 -->
      <div class="items-center flex flex-1 flex-shrink-0">
        <IconPark
          v-if="!isSongLike"
          :icon="Like"
          size="20"
          :stroke-width="3"
          class="text-gray-400 ml-3 mr-2 cursor-pointer hover:text-red-400"
          @click="addSongLike"
        />
        <IconPark
          v-else
          :icon="Like"
          size="20"
          :stroke-width="3"
          theme="filled"
          fill="#d0021b"
          @click="delSongListLike"
          class="text-gray-400 ml-3 mr-2 cursor-pointer"
        />

        <!-- 歌曲名 -->
        <div class="truncate text-lg" style="max-width: 40%">
          <small>{{ propSong.name }}</small>
        </div>
        <!-- 随机返回一个1-10整数 -->
        <IconPark
          v-if="true"
          class="ml-3 text-gray-400 cursor-pointer hover-text"
          size="16"
          :icon="PlayTwo"
          @click="routerPushByNameId(Pages.mvDetail, _.random(1, 10))"
        />
      </div>

      <!-- 中间操作栏 -->
      <div class="hidden icon-action flex-shrink-0">
        <div class="flex items-center gap-x-2 text-gray-400 ml-2">
          <IconPark
            title="播放"
            :icon="PlayOne"
            size="24"
            class="hover-text"
            @click="play(propSong.songId)"
          />
          <IconPark :icon="Add" size="20" class="hover-text" />
          <IconPark :icon="DownTwo" size="20" class="hover-text" @click="dwld" />
          <IconPark :icon="MoreTwo" size="20" class="hover-text" />
        </div>
      </div>
    </div>

    <!-- 专辑名 -->
    <div class="text-lg flex-shrink-0 w-1/3 items-center">
      <small
        class="truncate hover-text"
        @click="routerPushByNameId(Pages.albumDetail, propSong.albumId)"
      >
        {{ propSong.album }}
      </small>
    </div>

    <!-- 歌曲时长 -->
    <div class="w-20 flex-shrink-0 truncate flex items-center text-lg">
      <small>{{ numberToDuration(propSong.duration) }}</small>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Add, DownTwo, Like, MoreTwo, PlayOne, PlayTwo } from '@icon-park/vue-next'
import { numberToDuration } from '@/utils/number/number'
import { usePlayerStore } from '@/stores/player'
import IconPark from '@/components/common/IconPark.vue'
import type { Song } from '@/models/song'
import { Pages } from '@/router/pages'
import { routerPushByNameId } from '@/utils/navigator/router'
import type { LikeForm } from '@/models/like'
import _ from 'lodash'
import { useAuthStore } from '@/stores/auth'
import { AlertError } from '@/utils/alert/AlertPop'
import { storeToRefs } from 'pinia'
import { useLikeStore } from '@/stores/like'
import { downloadSong } from '@/utils/download/dowmload'
const { userId, isLogin } = storeToRefs(useAuthStore())
const { songs } = storeToRefs(useLikeStore())
const { addLike, delLike } = useLikeStore()

const dwld = () => {
  if (props.propSong.url) {
    downloadSong(props.propSong)
  } else {
    AlertError('抱歉，这首歌暂时没有开放下载')
  }
}

const isSongLike = computed(() => {
  let index = _.findIndex(songs.value, (o) => {
    return o.songId == props.propSong.songId
  })
  return index == -1 ? false : true
})

const props = defineProps<{
  propSong: Song
  order: number
}>()
const { play } = usePlayerStore()
const { song } = storeToRefs(usePlayerStore())
const id = song.value.songId

const addSongLike = () => {
  if (isLogin.value) {
    const likeForm: LikeForm = {
      albumId: 0,
      userId: userId.value,
      songId: props.propSong.songId,
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
      songId: props.propSong.songId,
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

<style lang="scss" scoped>
//内联操作图标
.song-item {
  @apply py-2.5 pl-1;
  &:hover {
    .icon-action {
      @apply inline-block;
    }
  }
}
//在播放时
.playing {
  @apply bg-emerald-50 dark:bg-stone-800;
}
</style>
