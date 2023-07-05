<template>
  <div class="p-5" v-if="albumData">
    <!-- 专辑详情页 -->
    <Info :album="albumData" :songs="songList" />
    <!-- 标签栏 -->
    <el-tabs class="mt-3" v-model="tab">
      <!-- 歌曲 -->
      <el-tab-pane lazy :label="`歌曲 ${songList.length}`" name="songs">
        <AlbumSongs :song-list="songList"></AlbumSongs>
      </el-tab-pane>
      <!-- 专辑详情 -->
      <el-tab-pane lazy label="专辑详情" name="desc">
        <div
          class="text-base text-slate-500 leading-7 flex flex-col gap-y-4 w-2/3 mx-1"
          style="white-space: pre-wrap"
        >
          <div>
            <span>专辑：</span>
            <span>{{ albumData.album }}</span>
          </div>
          <div>
            <span>歌手：</span>
            <span>{{ albumData.artist }}</span>
          </div>
          <div>
            <span>类型：</span>
            <span>{{ albumData.type }}</span>
          </div>
          <div>
            <span>语言：</span>
            <span>未知</span>
          </div>
          <div class="flex">
            <span class="flex-shrink-0">简介：</span>
            <span>{{ albumData.profile }}</span>
          </div>
        </div>
      </el-tab-pane>
      <!-- 评论 -->
      <el-tab-pane lazy label="评论 64" name="comments">
        <AlbumComments :album-id="id"></AlbumComments>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup lang="ts">
import type { Album } from '@/models/album'
import type { Song } from '@/models/song'
import { apiAlbumDetail } from '@/utils/api/album'
import Info from './AlbumInfo.vue'
import AlbumSongs from './AlbumSongs.vue'
import AlbumComments from './AlbumComments.vue'
import { AlertError, AlertSuccess } from '@/utils/alert/AlertPop'

const albumData = ref<Album>()
const songList = ref<Song[]>([])
const tab = ref('songs')
const route = useRoute()

//album id
const id = Number(route.query.id)

//获取专辑歌曲
onMounted(async () => {
  const res = await apiAlbumDetail(id)
  if (res.code == 200) {
    albumData.value = res.album
    songList.value = res.songs
  } else {
    AlertError('获取专辑歌曲失败')
  }
})
</script>
<style lang="scss"></style>
