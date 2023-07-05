<template>
  <!-- 主体界面 -->
  <div class="search-detail px-7">
    <!-- 分页tab栏 -->
    <el-tabs v-model="tab" class="mt-4">
      <!-- 歌曲搜索结果 -->
      <el-tab-pane lazy label="歌曲" name="song">
        <SongSearchResult />
      </el-tab-pane>
      <!-- 专辑搜索结果 -->
      <el-tab-pane lazy label="专辑" name="album">
        <AlbumSearchResult />
      </el-tab-pane>
      <!-- 歌手搜索结果 -->
      <el-tab-pane lazy label="歌手" name="artist">
        <ArtistSearchResult />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup lang="ts">
import AlbumSearchResult from './AlbumSearchResult.vue'
import SongSearchResult from './SongSearchResult.vue'
import ArtistSearchResult from './ArtistSearchResult.vue'
import { useSearchStore } from '@/stores/search'
import { storeToRefs } from 'pinia'

const { pageData } = storeToRefs(useSearchStore())
const { clearPageData } = useSearchStore()

//路由信息
const route = useRoute()
const keyWord: string = String(route.query.keyWord)
const tab = ref('song')

pageData.value[0].keyWord = keyWord
pageData.value[1].keyWord = keyWord
pageData.value[2].keyWord = keyWord

onUnmounted(() => {
  clearPageData()
})
</script>
<style lang="scss">
.search-detail {
  .el-tabs__nav-wrap::after {
    height: 0;
  }

  .el-tabs__header {
    @apply m-0;

    .el-tabs__item {
      @apply text-base;
    }
  }
}
</style>
