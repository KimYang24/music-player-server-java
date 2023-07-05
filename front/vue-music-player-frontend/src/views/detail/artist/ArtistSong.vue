<template>
  <div class="mt-3 flex items-center justify-between">
    <!-- 功能区 -->
    <div class="flex gap-x-3">
      <button class="w-28 button-outline" @click="playAll">
        <IconPark :icon="PlayOne" class="mr-1" size="20" />
        播放全部
      </button>
      <button class="w-28 button-outline">
        <IconPark :icon="DownloadFour" class="mr-1" size="18" />
        下载
      </button>
      <button class="w-28 button-outline">
        <IconPark :icon="ListSuccess" class="mr-1" size="17" />
        批量操作
      </button>
    </div>

    <!-- 排序显示 -->
    <div class="gap-x-7 flex mr-7 text-sm">
      <div
        class="hover-text"
        :class="{ 'text-emerald-300': pageData.order === 'hot' }"
        @click="setOrder('hot')"
      >
        最热
      </div>
      <div
        class="hover-text"
        :class="{ 'text-emerald-300': pageData.order === 'time' }"
        @click="setOrder('time')"
      >
        最新
      </div>
    </div>
  </div>

  <!-- 标签属性 -->
  <div class="mt-2">
    <div class="flex text-sm text-gray-400 py-2">
      <div class="flex-auto">歌曲</div>
      <div class="w-1/3">专辑</div>
      <div class="w-20">时长</div>
    </div>

    <!-- 歌曲组件 -->
    <div class="text-sm">
      <template v-for="(song, index) in list" :key="index">
        <SongItem :prop-song="song" :order="index + 1" />
      </template>
    </div>

    <!-- 加载按钮 -->
    <div class="flex justify-center py-5" v-if="list.length > 0 && !pageData.noMore">
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
import { PlayOne, DownloadFour, ListSuccess } from '@icon-park/vue-next'
import type { Song } from '@/models/song'
import { apiArtistSongs } from '@/utils/api/artist'
import SongItem from '@/components/common/SongItem.vue'
import IconPark from '@/components/common/IconPark.vue'
import { usePlayerStore } from '@/stores/player'
import { AlertError } from '@/utils/alert/AlertPop'

const props = defineProps<{ id: number }>()

const list = ref<Song[]>([])

const { pushPlayList, play } = usePlayerStore()

const playAll = () => {
  pushPlayList(true, ...list.value)
  play(list.value[0].songId)
}

const pageData = reactive({
  page: 0,
  pageSize: 10,
  pageTotal: 1,

  //是否显示加载动画
  loading: false,
  noMore: false,
  //排序方式：热门或最新
  order: 'hot'
})

//分页查询
const pageGet = async () => {
  pageData.loading = true
  const res = await apiArtistSongs(props.id, pageData.pageSize, pageData.page + 1, pageData.order)
  if (res.code == 200) {
    //判断是否已经没有页数了
    if (pageData.page >= pageData.pageTotal) {
      //所有数据已经取完
      pageData.noMore = true
      return
    }
    if (pageData.page == 0) {
      //初始时设置数据
      list.value = res.songs
    } else {
      //否则push
      list.value.push(...res.songs)
    }
    //当前位置页数加1
    pageData.page++
    //更新pageSize
    pageData.pageTotal = res.pageTotal
  } else {
    AlertError('抱歉，该歌手暂时无歌曲！')
  }
  //加载动画
  pageData.loading = false
}

//设置排序方式
const setOrder = (order: 'time' | 'hot') => {
  pageData.page = 0
  pageData.pageTotal = 0
  pageData.order = order
  pageGet()
}

onMounted(() => {
  pageGet()
})
</script>
<style lang="scss" scoped>
.el-button--large {
  @apply text-green-400;
}
</style>
