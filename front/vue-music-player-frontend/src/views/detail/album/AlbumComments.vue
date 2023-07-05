<template>
  <div class="mx-6 mb-3">
    <!-- 输入框 -->
    <el-input
      v-model="comment"
      maxlength="80"
      placeholder="期待你的神评论 !"
      type="textarea"
      :show-word-limit="true"
      clearable:true
      rows="3"
      resize="none"
      input-style="height:100px"
      class="myInput"
    />
  </div>
  <!-- 发布按钮 -->
  <div class="flex justify-end mr-7">
    <button class="button-outline w-16 h-8">发布</button>
  </div>
  <!-- 评论标题 -->
  <div class="text-xl text-main mt-0.5 mt-2 flex items-center">
    <div>精彩评论</div>
    <IconPark :icon="MessageEmoji" size="22" :stroke-width="3" class="ml-1" />
  </div>
</template>

<script setup lang="ts">
import { MessageEmoji } from '@icon-park/vue-next'
import IconPark from '@/components/common/IconPark.vue'

//评论
const comment = ref('')

defineProps<{
  albumId: number
}>()
</script>

<style lang="scss">
.myInput {
  @apply mt-0.5;
  .el-textarea__inner {
    @apply rounded-xl text-base bg-slate-50 dark:bg-zinc-900 dark:ring-gray-700 dark:ring-2;
  }
  .el-input__count {
    @apply dark:bg-zinc-900;
  }
}
</style>
