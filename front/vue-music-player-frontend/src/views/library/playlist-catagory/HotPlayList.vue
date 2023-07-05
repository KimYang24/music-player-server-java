<template>
  <div class="grid grid-flow-row grid-cols-8 2xl:grid-cols-12 gap-2 gap-y-4">
    <button class="button-outline w-24">全部</button>
    <div v-for="(tag, index) in tags" :key="index">
      <button class="button-outline w-24">{{ tag.tagName }}</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { PlayListTag } from '@/models/playlist'

const tags = ref<PlayListTag[]>([])

onMounted(() => {
  tags.value = [
    {
      tagName: 'ACG'
    },
    {
      tagName: '游戏'
    },
    {
      tagName: '英语'
    },
    {
      tagName: '说唱'
    },
    {
      tagName: '国风'
    },
    {
      tagName: '私藏'
    },
    {
      tagName: '流行'
    },
    {
      tagName: '摇滚'
    }
  ]
})
</script>

<style lang="scss"></style>
