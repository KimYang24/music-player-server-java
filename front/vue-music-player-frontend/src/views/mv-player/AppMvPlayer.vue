<template>
  <div class="p-5" v-if="mv">
    <div class="flex gap-x-5">
      <div></div>
      <div class="flex-1">
        <div class="flex flex-col">
          <video class="aspect-video w-full" :src="mv.url" autoplay controls />
          <div class="flex mt-8">
            <div class="text-2xl">{{ mv.movie }}------{{ mv.artist }}</div>
          </div>
          <div class="mt-8">
            <MvComment></MvComment>
          </div>
        </div>
      </div>
      <div class="hidden w-80 flex-shrink-0 xl:block">
        <span class="text-2xl">大家都在看:</span>
        <div class="flex flex-col space-y-8 mx-8">
          <div v-for="(item, index) in mvs" :key="index" @click="toMv(item.movieId)">
            <!-- 封面组件 -->
            <MyCover
              :name="item.movie"
              :pic-url="item.picUrl"
              :showPlayCount="true"
              :movie="true"
              :play-count="item.duration"
            />
            <!-- MV名 -->
            <div class="mx-2 mt-2 text-lg text-main truncate">
              {{ item.movie }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useMvStore } from '@/stores/mv'
import { usePlayerStore } from '@/stores/player'
import { storeToRefs } from 'pinia'
import _ from 'lodash'
import MyCover from '@/components/common/MyCover.vue'
import MvComment from './MvComment.vue'

const { mvs } = storeToRefs(useMvStore())
const { id } = storeToRefs(useMvStore())
const { togglePlay } = usePlayerStore()
const { isPlaying } = storeToRefs(usePlayerStore())
const mv = ref()

const toMv = (movieId: number) => {
  id.value = movieId
  let index = _.findIndex(mvs.value, (o) => {
    return o.movieId == id.value
  })
  mv.value = mvs.value[index]
  //暂停音乐播放
  if (isPlaying.value) {
    togglePlay()
  }
}

onMounted(async () => {
  let index = _.findIndex(mvs.value, (o) => {
    return o.movieId == id.value
  })
  mv.value = mvs.value[index]
  //暂停音乐播放
  if (isPlaying.value) {
    togglePlay()
  }
})

onUnmounted(() => {
  //恢复音乐播放
  setTimeout(() => {
    togglePlay()
  }, 1000)
})
</script>
<style lang="scss"></style>
