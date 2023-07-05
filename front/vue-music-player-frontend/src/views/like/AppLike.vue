<template>
  <!-- 主体界面 -->
  <div class="search-detail px-7">
    <div>
      <h1 class="text-3xl font-bold mt-4">我喜欢</h1>
    </div>
    <!-- 分页tab栏 -->
    <el-tabs v-model="tab" class="mt-2">
      <!-- 歌曲搜索结果 -->
      <el-tab-pane lazy label="歌曲" name="song">
        <SongLike />
      </el-tab-pane>
      <!-- 专辑搜索结果 -->
      <el-tab-pane lazy label="专辑" name="album">
        <AlbumLike />
      </el-tab-pane>
      <!-- 歌手搜索结果 -->
      <el-tab-pane lazy label="歌手" name="artist">
        <ArtistLike />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup lang="ts">
import SongLike from './SongLike.vue'
import AlbumLike from './AlbumLike.vue'
import ArtistLike from './ArtistLike.vue'

const tab = ref('song')
</script>
