<template>
  <div class="px-10">
    <h1 class="text-3xl font-bold pt-4 pb-4">推荐</h1>
    <MySwiper />
    <PersonalizedSong />
    <PersonalizedAlbum />
    <PersonalizedArtist />
    <!-- 回到开头按钮 -->
  </div>
</template>

<script setup lang="ts">
import PersonalizedSong from '@/views/discover/PersonalizedSong.vue'
import PersonalizedAlbum from '@/views/discover/PersonalizedAlbum.vue'
import PersonalizedArtist from '@/views/discover/PersonalizedArtist.vue'
import MySwiper from '@/components/common/MySwiper.vue'
</script>

<style lang="scss"></style>
