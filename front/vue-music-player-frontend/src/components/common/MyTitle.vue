<template>
  <div class="text-2xl text-main py-3 mt-2 flex items-center">
    <div>{{ title }}</div>
    <IconPark :icon="CollectionRecords" size="24" :stroke-width="2.8" class="ml-1" />
  </div>
</template>

<script setup lang="ts">
import { CollectionRecords } from '@icon-park/vue-next'
import IconPark from '@/components/common/IconPark.vue'

defineProps<{
  title: string
}>()
</script>

<style lang="scss"></style>
