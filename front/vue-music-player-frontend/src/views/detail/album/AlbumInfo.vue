<template>
  <!-- 水平flex -->
  <div class="flex items-stretch">
    <!-- 图片 -->
    <img :src="album.picUrl" alt="" class="w-44 h-44 object-cover rounded-xl flex-shrink-0" />
    <!-- 描述信息 -->
    <!-- col flex flex-1-->
    <div class="pl-7 flex flex-col justify-between py-2 flex-1">
      <div class="flex flex-col justify-between flex-1">
        <!-- 专辑名 -->
        <div class="text-4xl font-bold">{{ album.album }}</div>
        <!-- 歌手 -->
        <div class="flex items-center text-sm text-gray-400">
          <span class="">{{ album.artist }}</span>
        </div>
        <div class="text-xs text-gray-500">
          <span>发行时间:</span>
          {{ album.publishTime }}
          <span class="ml-3">类别:</span>
          {{ album.type }}
        </div>
      </div>

      <!-- 按钮 -->
      <div class="justify-self-stretch mt-5 gap-x-4 flex items-center">
        <button
          class="w-28 button rounded-full bg-gradient-to-r to-teal-400 from-emerald-300 text-slate-50"
          @click="playAll"
        >
          <IconPark :icon="PlayOne" size="24" />
          <span>播放全部</span>
        </button>

        <button class="w-28 button-outline" v-if="!isAlbumLike" @click="addAlbumLike">
          <IconPark :icon="Like" size="18" class="mr-1" theme="outline" />
          <span>收藏</span>
        </button>
        <button class="w-28 button-outline" v-else @click="delAlbumLike">
          <IconPark :icon="Like" size="18" class="mr-1" theme="filled" fill="#d0021b" />
          <span>取消收藏</span>
        </button>

        <button class="button-outline w-8">
          <IconPark :icon="More" />
        </button>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Like, More, PlayOne } from '@icon-park/vue-next'
import IconPark from '@/components/common/IconPark.vue'
import type { Album } from '@/models/album'
import type { Song } from '@/models/song'
import { usePlayerStore } from '@/stores/player'
import { useLikeStore } from '@/stores/like'
import { storeToRefs } from 'pinia'
import type { LikeForm } from '@/models/like'
import _ from 'lodash'
import { useAuthStore } from '@/stores/auth'
import { AlertError } from '@/utils/alert/AlertPop'

const { userId, isLogin } = storeToRefs(useAuthStore())
const { albums } = storeToRefs(useLikeStore())
const { addLike, delLike } = useLikeStore()
const isAlbumLike = computed(() => {
  let index = _.findIndex(albums.value, (o) => {
    return o.albumId == props.album.albumId
  })
  return index == -1 ? false : true
})

const addAlbumLike = () => {
  if (isLogin.value) {
    const likeForm: LikeForm = {
      albumId: props.album.albumId,
      userId: userId.value,
      songId: 0,
      artistId: 0,
      playlistId: 0,
      type: 3 //专辑
    }
    addLike(likeForm, userId.value)
  } else {
    AlertError('请先登录！')
  }
}

const delAlbumLike = () => {
  if (isLogin.value) {
    const likeForm: LikeForm = {
      albumId: props.album.albumId,
      userId: userId.value,
      songId: 0,
      artistId: 0,
      playlistId: 0,
      type: 3 //专辑
    }
    delLike(likeForm, userId.value)
  } else {
    AlertError('请先登录！')
  }
}

const props = defineProps<{
  album: Album
  songs: Song[]
}>()

const { pushPlayList, play } = usePlayerStore()

const playAll = () => {
  pushPlayList(true, ...props.songs)
  play(props.songs[0].songId)
}
</script>
