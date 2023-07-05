<template>
  <MyTitle title="推荐新歌曲" />
  <div
    class="mt-4 mb-10 grid grid-flow-row gap-x-5 cursor-pointer grid-cols-2 2xl:grid-cols-4 gap-y-6"
  >
    <!-- 循环 -->
    <div
      v-for="(item, index) in _.sampleSize(personalizedSongs, 16)"
      :key="index"
      class="hover-bg-view transition-all flex items-center"
      @click="play(item.songId)"
    >
      <!-- 第一列图片 -->
      <img
        :src="item.picUrl"
        alt="歌曲图片"
        class="w-40 h-40 object-cover rounded-2xl flex-shrink-0"
      />
      <!-- 第二列文字信息 -->
      <div class="px-3 flex-auto flex flex-col">
        <div class="text-bg truncate">
          {{ item.name }}
        </div>
        <div class="mt-1.5 text-dc truncate">
          {{ item.artist }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import MyTitle from '@/components/common/MyTitle.vue'
import { usePlayerStore } from '@/stores/player'
import { useMusicStore } from '@/stores/music'

import _ from 'lodash'

const { play } = usePlayerStore()
const { personalizedSongs } = toRefs(useMusicStore())
const { updatePersonalize: UpdatePersonalize } = useMusicStore()

onMounted(async () => {
  await UpdatePersonalize(1)
})
</script>

<style lang="scss" scoped></style>
