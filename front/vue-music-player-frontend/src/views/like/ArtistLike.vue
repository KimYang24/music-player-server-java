<template>
  <div class="mt-5 mx-2">
    <!-- 歌手展示 -->
    <div class="grid grid-flow-row grid-cols-4 xl:grid-cols-5 2xl:grid-cols-6 gap-10">
      <div
        v-for="(artist, index) in artists"
        :key="index"
        class="flex items-center flex-col"
        @click="routerPushByNameId(Pages.artistDetail, artist.artistId)"
      >
        <img
          :src="artist.picUrl"
          alt="歌手头像"
          class="rounded-full cursor-pointer w-full aspect-square object-cover bg-dc"
        />
        <div class="mt-2 text-sm">{{ artist.artist }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { routerPushByNameId } from '@/utils/navigator/router'
import { Pages } from '@/router/pages'
import { storeToRefs } from 'pinia'
import { useAuthStore } from '@/stores/auth'
import { useLikeStore } from '@/stores/like'

const { updateLikes } = useLikeStore()
const { artists } = storeToRefs(useLikeStore())
const { userId } = storeToRefs(useAuthStore())

onMounted(async () => {
  updateLikes(userId.value)
})
</script>

<style lang="scss" scoped>
.active {
  @apply bg-emerald-400 text-white rounded;
}
</style>
