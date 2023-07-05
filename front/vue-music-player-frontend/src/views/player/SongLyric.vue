<template>
  <div class="container">
    <n-carousel
      :ref="carousel"
      :slides-per-view="10"
      :space-between="20"
      :loop="false"
      :draggable="true"
      :autoplay="false"
      direction="vertical"
      effect="slide"
      :mousewheel="true"
      :show-dots="false"
    >
      <div v-for="(item, index) in lyric" :key="index" class="hover:cursor-pointer">
        <div>{{ item.content }}</div>
        <div>
          {{
            _.findIndex(tlyric, (o) => {
              return item.time == o.time
            }) != -1
              ? tlyric[
                  _.findIndex(tlyric, (o) => {
                    return item.time == o.time
                  })
                ].content
              : ''
          }}
        </div>
      </div>
    </n-carousel>
  </div>
</template>

<script setup lang="ts">
import { usePlayerStore } from '@/stores/player'
import { storeToRefs } from 'pinia'
import _ from 'lodash'
import { apiGetLyric } from '@/utils/api/song'

const carousel = ref()
const { audio, lyric, tlyric, song, showPlayWindow } = storeToRefs(usePlayerStore())
const { updateLrc } = usePlayerStore()

onMounted(async () => {
  getLrc()
})

//监听播放结束
watch(showPlayWindow, (show) => {
  if (show) {
    getLrc()
  }
})

const getLrc = async () => {
  if (song.value.lyricUrl) {
    const data: string = (await apiGetLyric(song.value.lyricUrl)) as string
    const regex = /\[by:.*?\]/
    const result = data.split(regex)
    console.log(result)
    if (result.length == 1) {
      updateLrc(result[0], '')
    } else {
      updateLrc(result[0], result[1])
    }
  } else {
    updateLrc('', '')
  }
}
</script>

<style lang="scss">
.container {
  height: 60vh;
}
</style>
