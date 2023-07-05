<template>
  <div class="pl-7 pr-9">
    <h1 class="text-3xl font-bold pt-8 pb-4">推荐</h1>
    <MyCardSwiper />
    <PersonalizedRadio />
  </div>
</template>

<script setup lang="ts">
import PersonalizedRadio from './PersonalizedRadio.vue'
import MyCardSwiper from '@/components/common/MyCardSwiper.vue'
</script>

<style lang="scss"></style>
