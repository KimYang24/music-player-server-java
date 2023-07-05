<template>
  <div>
    <!-- 官方榜 -->
    <div class="text-xl pb-5 font-bold">{{ topListData[0].type }}</div>
    <!-- 网格 -->
    <div class="grid grid-flow-row grid-cols-2 2xl:grid-cols-4 gap-8">
      <div
        v-for="(item, index) in topListData[0].topList"
        :key="index"
        class="flex bg-dc rounded-lg items-center cursor-pointer"
        @click="toPlaylist(item)"
      >
        <!-- 封面 -->
        <MyCover
          :name="item.name"
          :pic-url="item.picUrl"
          :play-count="item.listeners"
          class="w-36 flex-shrink-0"
          show-play-count
          :hot="true"
        />

        <div class="px-3 flex-1 flex-shrink-0 flex flex-col">
          <!-- 排行榜名字 -->
          <div class="text-xl font-bold">{{ item.name }}</div>
          <!-- 3条显示 -->
          <div class="text-xs text-main mt-2">
            <div
              v-for="(song, index) in _.sampleSize(personalizedSongs, 3)"
              :key="index"
              class="mt-2"
            >
              <div class="flex">
                <span class="mr-1">{{ index + 1 }}</span>
                <div class="flex-auto w-20 truncate">
                  <span>{{ song.name }}</span>
                  <span>-</span>
                  <span>{{ song.artist }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 地区榜 -->
    <div class="text-xl py-8 font-bold">{{ topListData[1].type }}</div>
    <!-- 网格 -->
    <div class="grid grid-flow-row grid-cols-5 2xl:grid-cols-7 gap-8">
      <div v-for="(item, index) in topListData[1].topList" :key="index" @click="toPlaylist(item)">
        <MyCover :name="item.name" :pic-url="item.picUrl" :play-count="item.listeners" />
        <div class="text-sm mt-2">{{ item.name }}</div>
      </div>
    </div>

    <!-- 全球榜 -->
    <div class="text-xl py-8 font-bold">{{ topListData[2].type }}</div>
    <!-- 网格 -->
    <div class="mb-10 grid grid-flow-row grid-cols-5 2xl:grid-cols-7 gap-8">
      <div v-for="(item, index) in topListData[2].topList" :key="index" @click="toPlaylist(item)">
        <MyCover :name="item.name" :pic-url="item.picUrl" :play-count="item.listeners" />
        <div class="text-sm mt-2">{{ item.name }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import MyCover from '@/components/common/MyCover.vue'
import type { TopList } from '@/models/toplist'
import { Pages } from '@/router/pages'
// import { useTopListStore } from '@/stores/toplist'
import { routerPushByNameId } from '@/utils/navigator/router'
import { useMusicStore } from '@/stores/music'
import { storeToRefs } from 'pinia'
import _ from 'lodash'

// const { topListData } = storeToRefs(useTopListStore())
// const { updateTopListData } = useTopListStore()
const { personalizedSongs } = storeToRefs(useMusicStore())
const { updatePersonalize: UpdatePersonalize } = useMusicStore()

onMounted(async () => {
  // updateTopListData()
  UpdatePersonalize(1)
})

const toPlaylist = (top: TopList) => {
  routerPushByNameId(Pages.playlistDetail, top.topListId)
}

const topListData = ref([
  {
    type: '官方榜',
    topList: [
      {
        topListId: 1,
        listeners: 123.3,
        name: '飙升榜',
        picUrl: '/src/assets/images/toplist/a1.png'
      },
      {
        topListId: 2,
        listeners: 1143.7,
        name: '热歌榜',
        picUrl: '/src/assets/images/toplist/a2.png'
      },
      {
        topListId: 3,
        listeners: 43.3,
        name: '新歌榜',
        picUrl: '/src/assets/images/toplist/a3.png'
      },
      {
        topListId: 4,
        listeners: 342.1,
        name: '流行指数榜',
        picUrl: '/src/assets/images/toplist/a4.png'
      },
      {
        topListId: 5,
        listeners: 626.2,
        name: '腾讯音乐人原创榜',
        picUrl: '/src/assets/images/toplist/a5.png'
      },
      {
        topListId: 6,
        listeners: 446.1,
        name: '听歌识曲榜',
        picUrl: '/src/assets/images/toplist/a6.png'
      },
      {
        topListId: 7,
        listeners: 935.5,
        name: 'MV榜',
        picUrl: '/src/assets/images/toplist/a7.png'
      }
    ]
  },
  {
    type: '地区榜',
    topList: [
      {
        topListId: 1,
        listeners: 142.1,
        name: '内地榜',
        picUrl: '/src/assets/images/toplist/b1.png'
      },
      {
        topListId: 2,
        listeners: 56.2,
        name: '香港地区榜',
        picUrl: '/src/assets/images/toplist/b2.png'
      },
      {
        topListId: 3,
        listeners: 46.1,
        name: '台湾地区榜',
        picUrl: '/src/assets/images/toplist/b3.png'
      },
      {
        topListId: 4,
        listeners: 1443.2,
        name: '欧美榜',
        picUrl: '/src/assets/images/toplist/b4.png'
      },
      {
        topListId: 5,
        listeners: 123.8,
        name: '韩国榜',
        picUrl: '/src/assets/images/toplist/b5.png'
      },
      {
        topListId: 6,
        listeners: 64.3,
        name: '日本榜',
        picUrl: '/src/assets/images/toplist/b6.png'
      }
    ]
  },
  {
    type: '全球榜',
    topList: [
      {
        topListId: 1,
        listeners: 1233.3,
        name: 'billboard',
        picUrl: '/src/assets/images/toplist/c1.png'
      },
      {
        topListId: 2,
        listeners: 155.7,
        name: 'melon',
        picUrl: '/src/assets/images/toplist/c2.png'
      },
      {
        topListId: 3,
        listeners: 331.4,
        name: 'UK',
        picUrl: '/src/assets/images/toplist/c3.png'
      },
      {
        topListId: 4,
        listeners: 166.2,
        name: '日本公信榜',
        picUrl: '/src/assets/images/toplist/c4.png'
      },
      {
        topListId: 5,
        listeners: 34.3,
        name: '香港TVB金曲榜',
        picUrl: '/src/assets/images/toplist/c5.png'
      }
    ]
  }
])
</script>

<style lang="scss"></style>
