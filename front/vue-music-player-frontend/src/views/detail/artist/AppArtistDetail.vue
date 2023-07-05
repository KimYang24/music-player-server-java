<template>
  <!-- 主体界面 -->
  <div class="artist-detail px-7 pt-3" v-if="artistDetail">
    <!-- 歌手信息栏 -->
    <ArtistInfo :artist-detail="artistDetail" />

    <!-- 分页tab栏 -->
    <el-tabs v-model="tab" class="mt-3">
      <!-- 精选歌曲 -->
      <el-tab-pane lazy label="精选" name="select">
        <ArtistSelect :id="artistId" />
      </el-tab-pane>
      <!-- 歌曲栏 -->
      <el-tab-pane lazy :label="`歌曲 ${artistDetail.songSize}`" name="song">
        <ArtistSong :id="artistId" />
      </el-tab-pane>
      <!-- 专辑栏 -->
      <el-tab-pane lazy :label="`专辑 ${artistDetail.albumSize}`" name="album">
        <ArtistAlbum :id="artistId" />
      </el-tab-pane>
      <!-- 视频栏 -->
      <el-tab-pane lazy :label="`视频 ${66}`" name="mv">
        <ArtistMv :id="artistId" />
      </el-tab-pane>
      <!-- 详情栏 -->
      <el-tab-pane lazy label="详情" name="describe">
        <ArtistDesc :id="artistId" />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup lang="ts">
import { apiArtistDetail } from '@/utils/api/artist'
import type { Artist } from '@/models/artist'
import ArtistSelect from './ArtistSelect.vue'
import ArtistInfo from './ArtistInfo.vue'
import ArtistDesc from './ArtistDesc.vue'
import ArtistSong from './ArtistSong.vue'
import ArtistAlbum from './ArtistAlbum.vue'
import ArtistMv from './ArtistMV.vue'
import { AlertError } from '@/utils/alert/AlertPop'

//路由信息
const route = useRoute()
const artistId = Number(route.query.id)
const artistDetail = ref<Artist>()
const tab = ref('select')

//获取歌手详情
onMounted(async () => {
  const res = await apiArtistDetail(artistId)
  if (res.code == 200) {
    artistDetail.value = res.artistDetail
  } else {
    AlertError('获取歌手详情失败')
  }
})
</script>
<style lang="scss">
.artist-detail {
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
