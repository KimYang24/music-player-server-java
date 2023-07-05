<template>
  <div class="flex-col flex h-full pb-28 justify-center">
    <div class="flex items-center">
      <!-- 左边专辑封面 -->
      <div class="w-1/2">
        <div class="flex justify-center">
          <div class="w-1/2 h-1/2 rounded-full">
            <img id="myImageAAAA" :src="song.picUrl || albumLogo" alt="" />
          </div>
        </div>
      </div>

      <!-- 右边歌词歌曲信息 -->
      <div class="w-1/2">
        <div class="flex justify-center h-full">
          <div class="flex flex-col items-center mr-20">
            <!-- 歌曲标题 -->
            <div class="h-5 mt-9 mb-5">
              <span class="font-bold text-3xl">歌曲名</span>
            </div>
            <div class="h-5">
              <span>{{ songPos }}</span>
            </div>
            <div class="h-5 mb-12">
              <span>{{ artistPos }}</span>
            </div>
            <!-- 歌词显示 -->
            <div class="flex-1">
              <SongLyric></SongLyric>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import albumLogo from '@/assets/images/albumLogo.png'
import { usePlayerStore } from '@/stores/player'
import { storeToRefs } from 'pinia'
import { apiGetLyric } from '@/utils/api/song'
import SongLyric from './SongLyric.vue'

const lyric = ref()
const get = () => {
  lyric.value = apiGetLyric(song.value.lyricUrl!)
}
const { song } = storeToRefs(usePlayerStore())
const songPos = computed(() => {
  return song.value.artist ? '歌手：' + song.value.artist : ''
})
const artistPos = computed(() => {
  return song.value.album ? '专辑：' + song.value.album : ''
})

// //动画
// import gsap, { Power0 } from 'gsap'
// // 创建一个变量来保存 TweenLite 对象，方便以后操作
// let tween;

// // 页面加载后初始化动画效果
// function initAnimation() {
//   // 选取图片元素并旋转它
//   const image = document.getElementById('myImageAAAA');
//   tween = gsap.to(image, {
//     rotate: 360,
//     duration: 5,
//     ease: Power0.easeNone,
//     repeat: -1,
//     paused: true
//   });
// }

// // 暂停动画
// function pauseAnimation() {
//   tween.pause();
// }

// // 恢复动画
// function resumeAnimation() {
//   tween.resume();
// }

// onMounted(()=>{
// // 初始化动画效果
// initAnimation();
// })
</script>
