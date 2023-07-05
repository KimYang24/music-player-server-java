<template>
  <div class="px-7 pt-5 music">
    <h1 class="text-3xl font-bold pb-4">音乐馆</h1>
    <!-- affix页面元素固定在特定可视区域 -->
    <el-affix target=".music" :offset="56">
      <div>
        <!-- 标签页 -->
        <el-tabs v-model="tab" @tab-click="onTabClick">
          <el-tab-pane
            v-for="(menu, index) in menus"
            :key="index"
            :label="menu.label"
            :name="menu.name"
          />
        </el-tabs>
      </div>
    </el-affix>
    <div class="mt-5">
      <router-view v-slot="{ Component }">
        <transition class="animate__animated animate__zoomIn" mode="out-in">
          <component :is="Component" />
        </transition>
      </router-view>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Pages } from '@/router/pages'

interface Menu {
  label: string
  name: string
}

//之后换成page
const menus: Menu[] = [
  {
    label: '精选',
    name: Pages.select
  },
  {
    label: '有声电台',
    name: Pages.libRadio
  },
  {
    label: '排行',
    name: Pages.top
  },
  {
    label: '歌手',
    name: Pages.artist
  },
  {
    label: '分类歌单',
    name: Pages.playlistCata
  },
  {
    label: '数字专辑',
    name: Pages.digitalAlbum
  },
  {
    label: '手机专享',
    name: Pages.phoneOnly
  }
]

const router = useRouter()
const route = useRoute()
//监听当前路由名字
const tab = ref(route.name)
//组件挂载后，监听route，更新tab
watch(
  () => route.name,
  (newVal) => {
    tab.value = newVal
  }
)

const onTabClick = ({ props }: { props: Menu }) => {
  router.push({ name: props.name, replace: false })
}

onMounted(() => {
  router.push({ name: Pages.select })
})
</script>

<style lang="scss">
//标签页样式，无边距
.music {
  .el-tabs__nav-wrap::after {
    height: 0;
  }
  .el-tabs__header {
    @apply m-0;
  }
}
</style>
