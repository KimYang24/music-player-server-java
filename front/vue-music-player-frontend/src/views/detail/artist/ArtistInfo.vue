<template>
  <div class="flex">
    <!-- 背景图片 -->
    <img
      :src="artistDetail.picUrl"
      alt=""
      class="w-56 h-56 object-cover rounded-full flex-shrink-0"
    />

    <!-- 主体信息显示 -->
    <div class="pl-8 flex flex-col">
      <!-- 歌手信息 -->
      <div class="flex flex-col justify-end flex-1">
        <div class="text-5xl font-medium">{{ artistDetail.artist }}</div>
        <div class="text-xs text-gray-500 leading-normal mt-3 w-3/4">
          {{ artistDetail.profile.substring(0, 80) }}...
        </div>
        <div class="flex text-lg gap-x-6 mt-2">
          <div>
            单曲数：<span>{{ artistDetail.songSize }}</span>
          </div>
          <div>
            专辑数：<span>{{ artistDetail.albumSize }}</span>
          </div>
          <div>
            MV: <span>{{ 666 }}</span>
          </div>
        </div>
      </div>

      <!-- 操作栏 -->
      <div class="mt-3 gap-x-5 flex items-center">
        <button
          v-if="!isArtistLike"
          @click="addArtistLike"
          class="w-28 button rounded-full bg-gradient-to-r to-teal-400 from-emerald-300 text-slate-50"
        >
          <IconPark :icon="Plus" size="18" class="mr-1" />
          <span>关注</span>
        </button>
        <button v-else class="w-28 button rounded-full text-teal-600" @click="delArtistLike">
          <IconPark :icon="Dislike" size="18" class="mr-1" />
          <span>取消关注</span>
        </button>

        <button class="w-28 button-outline">
          <IconPark :icon="Fm" size="18" class="mr-1" />
          <span>歌手电台</span>
        </button>
        <!-- 无用按钮 -->
        <button class="button-outline w-8">
          <IconPark :icon="More" />
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Plus, Fm, More, Dislike } from '@icon-park/vue-next'
import IconPark from '@/components/common/IconPark.vue'

import type { Artist } from '@/models/artist'

import { useLikeStore } from '@/stores/like'
import { storeToRefs } from 'pinia'
import type { LikeForm } from '@/models/like'
import _ from 'lodash'
import { useAuthStore } from '@/stores/auth'
import { AlertError } from '@/utils/alert/AlertPop'

const { userId, isLogin } = storeToRefs(useAuthStore())
const { artists } = storeToRefs(useLikeStore())
const { addLike, delLike } = useLikeStore()
const isArtistLike = computed(() => {
  let index = _.findIndex(artists.value, (o) => {
    return o.artistId == props.artistDetail.artistId
  })
  return index == -1 ? false : true
})

const props = defineProps<{
  artistDetail: Artist
}>()

const addArtistLike = () => {
  if (isLogin.value) {
    const likeForm: LikeForm = {
      albumId: 0,
      userId: userId.value,
      songId: 0,
      artistId: props.artistDetail.artistId,
      playlistId: 0,
      type: 2 //歌手
    }
    addLike(likeForm, userId.value)
  } else {
    AlertError('请先登录！')
  }
}

const delArtistLike = () => {
  if (isLogin.value) {
    const likeForm: LikeForm = {
      albumId: 0,
      userId: userId.value,
      songId: 0,
      artistId: props.artistDetail.artistId,
      playlistId: 0,
      type: 2 //歌手
    }
    delLike(likeForm, userId.value)
  } else {
    AlertError('请先登录！')
  }
}
</script>
