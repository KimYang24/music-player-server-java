<template>
  <div>
    <span class="text-sm ml-2">黑夜模式</span>
    <el-switch
      v-model="isDarkMode"
      class="ml-2"
      style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
    />
  </div>
</template>

<script setup lang="ts">
//夜间模式
const isDarkMode = ref(false)
watch(isDarkMode, (newValue, oldValue) => {
  if (newValue) {
    document.documentElement.classList.add('dark')
  } else {
    document.documentElement.classList.remove('dark')
  }
})
</script>
