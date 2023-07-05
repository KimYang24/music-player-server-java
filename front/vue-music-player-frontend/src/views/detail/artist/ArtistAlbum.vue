<template>
  <div class="mt-5 mx-6">
    <!-- 网格 -->
    <div class="grid grid-flow-row gap-6 grid-cols-4 2xl:grid-cols-6">
      <!-- 循环 -->
      <div
        v-for="(item, index) in list"
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

    <!-- 加载按钮,还有页就显示 -->
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
import { apiArtistAlbums } from '@/utils/api/album'
import type { Album } from '@/models/album'
import MyCover from '@/components/common/MyCover.vue'
import { routerPushByNameId } from '@/utils/navigator/router'
import { Pages } from '@/router/pages'
import { AlertError } from '@/utils/alert/AlertPop'

const props = defineProps<{
  id: number
}>()
const list = ref<Album[]>([])

const pageData = reactive({
  page: 0,
  pageSize: 18,
  pageTotal: 1,

  //是否显示加载动画
  loading: false,
  noMore: false
})

//分页查询
const pageGet = async () => {
  pageData.loading = true
  const res = await apiArtistAlbums(props.id, pageData.pageSize, pageData.page + 1)
  if (res.code == 200) {
    //判断是否已经没有页数了
    if (pageData.page >= pageData.pageTotal) {
      //所有数据已经取完
      pageData.noMore = true
      return
    }
    if (pageData.page == 0) {
      //初始时设置数据
      list.value = res.albums
    } else {
      //否则push
      list.value.push(...res.albums)
    }
    //当前位置页数加1
    pageData.page++
    //更新pageSize
    pageData.pageTotal = res.pageTotal
  } else {
    AlertError('抱歉，该歌手暂时无专辑！')
  }
  //加载动画
  pageData.loading = false
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
