<template>
  <div class="mt-3 mx-0">
    <MyTitle title="热门专辑"></MyTitle>
    <!-- 网格 -->
    <div class="mx-10 mt-2 grid grid-flow-row gap-24 grid-cols-3 2xl:grid-cols-4">
      <!-- 循环 -->
      <div
        v-for="(item, index) in listAlbums"
        :key="index"
        @click="routerPushByNameId(Pages.albumDetail, item.albumId)"
      >
        <!-- 封面 -->
        <MyCover
          :name="item.album"
          :pic-url="item.picUrl"
          :play-count="item.size"
          :showPlayCount="true"
        />
        <!-- 专辑名、发行时间 -->
        <div class="mt-1 ml-1 text-sm text-main truncate">{{ item.album }}</div>
        <div class="text-sm ml-1 text-dc truncate">{{ item.publishTime || '未知' }}</div>
      </div>
    </div>

    <MyTitle title="热门歌曲"></MyTitle>
    <!-- 歌曲 -->
    <!-- 功能区 -->
    <div class="flex gap-x-3 mt-1">
      <button class="w-28 button-outline" @click="playAll">
        <IconPark :icon="PlayOne" class="mr-1" size="20" />
        播放全部
      </button>
    </div>

    <div class="flex text-sm text-gray-400 pt-5">
      <div class="flex-auto">歌曲</div>
      <div class="w-1/3">专辑</div>
      <div class="w-20">时长</div>
    </div>

    <!-- 歌曲组件 -->
    <div class="text-sm">
      <template v-for="(song, index) in listSongs" :key="index">
        <SongItem :prop-song="song" :order="index + 1" />
      </template>
    </div>
  </div>
</template>

<script setup lang="ts">
import { apiArtistAlbums } from '@/utils/api/album'
import type { Album } from '@/models/album'
import MyCover from '@/components/common/MyCover.vue'
import { routerPushByNameId } from '@/utils/navigator/router'
import { Pages } from '@/router/pages'
import { PlayOne, DownloadFour, ListSuccess } from '@icon-park/vue-next'
import { apiArtistSongs } from '@/utils/api/artist'
import SongItem from '@/components/common/SongItem.vue'
import IconPark from '@/components/common/IconPark.vue'
import MyTitle from '@/components/common/MyTitle.vue'
import { AlertError } from '@/utils/alert/AlertPop'
import type { Song } from '@/models/song'
import { usePlayerStore } from '@/stores/player'

const { pushPlayList, play } = usePlayerStore()

const props = defineProps<{
  id: number
}>()
const listAlbums = ref<Album[]>([])
const listSongs = ref<Song[]>([])
const pageData = reactive({
  page1: 0,
  page2: 0,
  pageSize: 18,
  pageTotal: 1,

  //是否显示加载动画
  loading: false,
  noMore: false
})

const playAll = () => {
  pushPlayList(true, ...listSongs.value)
  play(listSongs.value[0].songId)
}

//分页查询
const pageGet = async () => {
  const res1 = await apiArtistAlbums(props.id, 4, 1)
  const res2 = await apiArtistSongs(props.id, 12, 1, 'hot')
  if (res1.code == 200) {
    //判断是否已经没有页数了
    if (pageData.page1 >= pageData.pageTotal) {
      //所有数据已经取完
      pageData.noMore = true
      return
    }
    if (pageData.page1 == 0) {
      //初始时设置数据
      listAlbums.value = res1.albums
    } else {
      //否则push
      listAlbums.value.push(...res1.albums)
    }
    //当前位置页数加1
    pageData.page1++
    //更新pageSize
    pageData.pageTotal = res1.pageTotal
  } else {
    AlertError('抱歉，该歌手暂时无专辑！')
  }

  if (res2.code == 200) {
    //判断是否已经没有页数了

    if (pageData.page2 >= pageData.pageTotal) {
      //所有数据已经取完
      pageData.noMore = true
      return
    }
    if (pageData.page2 == 0) {
      //初始时设置数据
      listSongs.value = res2.songs
    } else {
      //否则push
      listSongs.value.push(...res2.songs)
    }
    //当前位置页数加1
    pageData.page2++
    //更新pageSize
    pageData.pageTotal = res2.pageTotal
  } else {
    AlertError('抱歉，该歌手暂时无歌曲！')
  }
}

onMounted(async () => {
  await pageGet()
})
</script>
<style lang="scss" scoped>
.el-button--large {
  @apply text-green-400;
}
</style>
