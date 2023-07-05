<script setup lang="ts">
//初始化操作
import { userPlayerInit } from '@/stores/player'
userPlayerInit()
</script>

<template>
  <RouterView />
</template>

<style scoped></style>
