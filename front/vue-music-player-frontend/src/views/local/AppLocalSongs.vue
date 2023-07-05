<template>
  <el-empty :image-size="250" class="mt-14">
    <el-text class="mx-1 mt-5" size="large">本页面正在开发中</el-text><br />
    <el-button type="primary" class="mt-3" @click="router.push('/discover')">回到首页</el-button>
  </el-empty>
</template>

<script setup lang="ts">
const router = useRouter()
</script>
