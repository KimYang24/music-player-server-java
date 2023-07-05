<template>
  <div class="mt-2">
    <div class="flex text-xs text-gray-400 py-2">
      <div class="flex-auto">歌曲</div>
      <div class="w-1/4">歌手</div>
      <div class="w-1/4">专辑</div>
    </div>
    <div class="text-sm">
      <template v-for="(song, index) in songs" :key="index">
        <SongItemWithEdit :prop-song="song" :order="index + 1" />
      </template>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Song } from '@/models/song'
import SongItemWithEdit from '@/components/common/SongItemWithEdit.vue'

const props = defineProps<{
  songs: Song[]
}>()
</script>
<style lang="scss"></style>
