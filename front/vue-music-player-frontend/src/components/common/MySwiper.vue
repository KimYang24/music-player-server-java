<template>
  <!-- 歌曲循环推荐 -->
  <Swiper
    v-if="isShowSwiper"
    class="inSwiper"
    slidesPerGroup:1
    slides-per-view="auto"
    :navigation="true"
    :pagination="true"
    :grab-cursor="true"
    :loop="true"
    :modules="modules"
    :autoplay="{
      delay: 2500,
      disableOnInteraction: false
    }"
    speed:1500
  >
    <!-- slider -->
    <SwiperSlide v-for="(item, index) in swipers" :key="index">
      <img
        :src="item.picUrl"
        class="swiper-image"
        style="object-fit: cover; aspect-ratio: 1/1"
        @click="onClick(item)"
      />
      <div class="text-center">
        <span>{{ item.typeTitle }}</span>
      </div>
    </SwiperSlide>
  </Swiper>
</template>

<script setup lang="ts">
// Import Swiper styles
import 'swiper/css'
import 'swiper/css/navigation'
import 'swiper/css/pagination'

import { Swiper, SwiperSlide } from 'swiper/vue'
import { Navigation, Pagination, Autoplay } from 'swiper'
import { usePlayerStore } from '@/stores/player'
import { useSwiperStore } from '@/stores/swiper'
import type { Swiper as MySwiper } from '@/models/swiper'
import { routerPushByNameId } from '@/utils/navigator/router'
import { Pages } from '@/router/pages'

const { swipers } = toRefs(useSwiperStore())
const { updateSwipers } = useSwiperStore()
const modules = [Navigation, Pagination, Autoplay]
const isShowSwiper = ref(false)

onMounted(async () => {
  await updateSwipers()
  isShowSwiper.value = true
})
const { play } = usePlayerStore()
const router = useRouter()

const onClick = (swiper: MySwiper) => {
  //为歌曲则播放
  if (swiper.targetType == 1) {
    play(swiper.targetId)
  }
  //为专辑则跳转
  else if (swiper.targetType == 2) {
    routerPushByNameId(Pages.albumDetail, swiper.targetId)
  }
  //为歌手则跳转
  else if (swiper.targetType == 3) {
    routerPushByNameId(Pages.artistDetail, swiper.targetId)
  }
}

onUnmounted(() => {})
</script>

<style lang="scss">
.inSwiper {
  @apply -mx-8 pt-5;

  .swiper-button-next {
    @apply pb-36 text-emerald-500;
  }
  .swiper-button-prev {
    @apply pb-36 text-emerald-500;
  }
  .swiper-pagination-bullet-active {
    @apply bg-emerald-500;
  }
  .swiper-wrapper {
    transition-timing-function: ease-in-out;

    .swiper-slide {
      @apply w-full lg:w-1/2 xl:w-1/3 2xl:w-1/4 px-11 pb-12;

      .swiper-image {
        @apply rounded-2xl cursor-pointer transition-all w-full h-full;
        @apply hover:shadow hover:opacity-50;
      }
    }
  }
}
</style>
