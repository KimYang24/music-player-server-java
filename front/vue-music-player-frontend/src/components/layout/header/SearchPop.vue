<template>
  <div class="flex items-center">
    <!-- 弹出显示区域 -->
    <el-popover
      popper-style="max-width:auto;padding:0;"
      v-model:visible="showSearchView"
      width="260px"
    >
      <!-- 输入框 -->
      <template #reference>
        <ElInput
          placeholder="搜索音乐、歌手、专辑"
          clearable
          @input="searchInput"
          v-model="searchKeyword"
          @focus="showSearchView = true"
          @focusout="showSearchView = false"
          class="custom-input h-9"
        >
        </ElInput>
      </template>

      <!-- 搜索结果显示 -->
      <div class="h-96">
        <el-scrollbar>
          <div class="pb-2.5">
            <!-- 显示热搜或搜索结果 -->
            <div v-if="showHot">
              <div class="pt-2 pb-1.5 px-2.5">热门搜索</div>
              <div>
                <!-- 热门搜索 -->
                <div
                  v-for="(item, index) in searchHotData"
                  :key="index"
                  class="py-2.5 px-2.5 hover-text cursor-pointer flex justify-between items-center text-xs"
                  @click="hotClick(item.searchWord)"
                >
                  <!-- 内容显示 -->
                  <div>
                    <span class="mr-1">{{ index + 1 }}.</span>
                    <span>{{ item.searchWord }}</span>
                  </div>
                  <div class="text-red-300 text-xs">{{ getType(item.type) }}</div>
                </div>
              </div>
            </div>
            <!-- 搜索结果 -->
            <SearchSuggest v-else />
          </div>
        </el-scrollbar>
      </div>
    </el-popover>

    <el-button class="button-outline button search-button" :icon="Search" @click="search" />
  </div>
</template>

<script setup lang="ts">
import { Search } from '@icon-park/vue-next'
import { storeToRefs } from 'pinia'
import { useSearchStore } from '@/stores/search'
import { debounce } from 'lodash'
import SearchSuggest from '@/components/layout/header/SearchSuggest.vue'
import { AlertError } from '@/utils/alert/AlertPop'
import { routerPushByNameKeyWord } from '@/utils/navigator/router'
import { Pages } from '@/router/pages'

onMounted(async () => {
  updateSearchHot()
})

// 搜索框输入更新搜索结果
const searchInput = debounce((value: string | number) => updateSuggest(), 500, { maxWait: 1000 })

const { showSearchView, searchKeyword, showHot, searchHotData } = storeToRefs(useSearchStore())
const { updateSuggest, updateSearchHot } = useSearchStore()

//热搜点击更新搜索结果
const hotClick = (text: string) => {
  searchKeyword.value = text
  updateSuggest()
  showSearchView.value = true
}

const getType = (type: string) => {
  if (type == '1') {
    return '歌曲'
  } else if (type == '2') {
    return '专辑'
  } else {
    return '歌手'
  }
}

//搜索
const search = () => {
  if (searchKeyword.value != '') {
    routerPushByNameKeyWord(Pages.searchResult, searchKeyword.value)
  } else {
    AlertError('搜索关键词不得为空！')
  }
}
</script>

<style lang="scss">
.custom-input {
  .el-input__wrapper {
    @apply rounded-2xl text-sm bg-slate-100 dark:bg-stone-900 dark:ring-gray-700 dark:ring-1;
  }
}

.search-button {
  @apply rounded-full h-9 ml-2 w-11 dark:bg-stone-900 dark:border-gray-700;
}
</style>
