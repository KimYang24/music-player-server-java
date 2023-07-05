<template>
  <div>
    <!-- 选项 -->
    <div class="pb-5">
      <!-- 每一行 -->
      <div v-for="option in options" :key="option.key" class="flex text-sm mb-5">
        <!-- 每一列 -->
        <div
          class="hover-text px-4 py-0.5"
          :class="{
            active:
              (item.key === pageData.gender && option.key === 'gender') ||
              (item.key === pageData.location && option.key === 'location') ||
              (item.key === pageData.firstLetter && option.key === 'firstLetter')
          }"
          v-for="(item, index) in option.list"
          :key="index"
          @click="optionChange(option.key, item.key)"
        >
          {{ item.name }}
        </div>
      </div>
    </div>
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
    <!-- 加载按钮 -->
    <div class="flex justify-center pb-14 pt-8" v-if="artists.length > 0 && !pageData.noMore">
      <el-button :loading="pageData.loading" link size="large" class="text-center" @click="pageGet"
        >加载更多
      </el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { routerPushByNameId } from '@/utils/navigator/router'
import { Pages } from '@/router/pages'
import { useArtistStore } from '@/stores/artist'
import { storeToRefs } from 'pinia'

const { pageData, artists } = storeToRefs(useArtistStore())
const { pageGet } = useArtistStore()

onMounted(async () => {
  if (pageData.value.page == 0) {
    pageGet()
  }
})

const optionChange = (keyName: string, keyValue: number | string) => {
  pageData.value.page = 0
  pageData.value.pageTotal = 1
  if (keyName === 'location') pageData.value.location = keyValue as number
  if (keyName === 'gender') pageData.value.gender = keyValue as number
  if (keyName === 'firstLetter') pageData.value.firstLetter = keyValue as string
  pageGet()
}

//筛选项
interface Option {
  key: string
  list: {
    key: string | number
    name: string
  }[]
}

const options: Option[] = [
  {
    key: 'location',
    list: [
      { key: 0, name: '全部' },
      { key: 1, name: '华语' },
      { key: 2, name: '欧美' },
      { key: 3, name: '日本' },
      { key: 4, name: '韩国' }
    ]
  },
  {
    key: 'gender',
    list: [
      { key: 0, name: '全部' },
      { key: 1, name: '男' },
      { key: 2, name: '女' },
      { key: 3, name: '组合' }
    ]
  },
  {
    key: 'firstLetter',
    list: [
      { key: '0', name: '全部' },
      { key: 'a', name: 'A' },
      { key: 'b', name: 'B' },
      { key: 'c', name: 'C' },
      { key: 'd', name: 'D' },
      { key: 'e', name: 'E' },
      { key: 'f', name: 'F' },
      { key: 'g', name: 'G' },
      { key: 'h', name: 'H' },
      { key: 'i', name: 'I' },
      { key: 'j', name: 'J' },
      { key: 'k', name: 'K' },
      { key: 'l', name: 'L' },
      { key: 'm', name: 'M' },
      { key: 'n', name: 'N' },
      { key: 'o', name: 'O' },
      { key: 'p', name: 'P' },
      { key: 'q', name: 'Q' },
      { key: 'r', name: 'R' },
      { key: 's', name: 'S' },
      { key: 't', name: 'T' },
      { key: 'u', name: 'U' },
      { key: 'v', name: 'V' },
      { key: 'w', name: 'W' },
      { key: 'x', name: 'X' },
      { key: 'y', name: 'Y' },
      { key: 'z', name: 'Z' },
      { key: '#', name: '#' }
    ]
  }
]
</script>

<style lang="scss" scoped>
.active {
  @apply bg-emerald-400 text-white rounded;
}
</style>
