<template>
  <div
    v-loading="!artistDesc"
    style="min-height: 200px"
    v-if="artistDesc"
    class="mt-4 text-dc px-2"
  >
    <div>
      <span class="title">简介</span>
      <div class="text-base my-4 leading-7 font-normal">
        {{ artistDesc?.profile }}
      </div>
    </div>

    <div>
      <span class="title">基本资料</span>
      <div class="describe">
        {{ artistDesc?.basicInfo }}
      </div>
    </div>

    <div>
      <span class="title">从艺历程</span>
      <div class="describe">
        {{ artistDesc?.timeLine }}
      </div>
    </div>

    <div>
      <span class="title">获奖记录</span>
      <div class="describe">
        {{ artistDesc?.award }}
      </div>
    </div>
  </div>
  <el-empty description="暂无数据" v-else></el-empty>
</template>

<script setup lang="ts">
import { apiArtistDesc } from '@/utils/api/artist'
import type { ArtistDesc } from '@/models/artist'
import { AlertError } from '@/utils/alert/AlertPop'

const props = defineProps<{
  id: number
}>()
const artistDesc = ref<ArtistDesc>()

onMounted(async () => {
  const res = await apiArtistDesc(props.id)
  if (res.code == 200) {
    artistDesc.value = res.describe
  } else {
    AlertError('获取歌手描述失败')
  }
})
</script>
<style lang="scss" scoped>
.title {
  @apply font-bold text-black dark:text-white text-base;
}
.describe {
  @apply text-base my-4 break-normal w-3/4 leading-7 font-normal;
}
</style>
