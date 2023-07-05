<template>
  <div>
    <HotPlayList />
    <div class="py-5 text-xl">歌单</div>

    <div class="gap-x-5 gap-y-7 grid grid-flow-row grid-cols-3 lg:grid-cols-5 2xl:grid-cols-7">
      <div
        v-for="(item, index) in playLists"
        :key="index"
        @click="routerPushByNameId(Pages.playlistDetail, item.playListId)"
      >
        <!-- mycover调整 -->
        <div class="cover-play-image">
          <!-- 图片 -->
          <el-image
            :src="item.picUrl"
            alt="playlist"
            class="w-full h-full bg-gray-50 object-cover"
            style="object-fit: cover; aspect-ratio: 1/1"
          />
        </div>

        <div class="mt-1 text-sm text-main leading-5 flex justify-center">{{ item.playList }}</div>
      </div>
    </div>
    <!-- 加载按钮 -->
    <div class="flex justify-center py-5" v-if="playLists.length > 0 && !pageData.noMore">
      <el-button
        :loading="pageData.loading"
        link
        size="large"
        class="text-center"
        @click="pageGet()"
        >加载更多
      </el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import HotPlayList from '@/views/library/playlist-catagory/HotPlayList.vue'
import { Pages } from '@/router/pages'
import { routerPushByNameId } from '@/utils/navigator/router'
import { usePlayListStore } from '@/stores/playlist'
import { storeToRefs } from 'pinia'

const { pageData, playLists } = storeToRefs(usePlayListStore())
const { pageGet } = usePlayListStore()
onMounted(async () => {
  if (pageData.value.page == 0) {
    pageGet()
  }
})
</script>
<style lang="scss">
.cover-play-image {
  @apply rounded-xl cursor-pointer transition-all relative overflow-hidden;
  //位置移动
  @apply hover:-translate-y-1.5;
  //悬浮时
  &:hover {
    .play-count {
      @apply opacity-0;
    }
  }
}
</style>
