<template>
  <el-card class="main-card">
    <el-table :data="state.tableData" height="550" style="width: 100%">
      <el-table-column prop="log_id" label="ID" width="100" />
      <el-table-column prop="username" label="用户名称" width="200" />
      <el-table-column prop="songname" label="播放歌曲" width="200" />
      <el-table-column prop="time" label="播放时间" width="200" />
    </el-table>

    <!--分页按钮-->
    <el-pagination
      background
      layout="prev, pager, next"
      :total="state.totals"
      :page-size="state.pageSize"
      :current-page="state.currentPage"
      @current-change="changePage"
    />
  </el-card>
</template>

<script setup lang="ts">
import type { Log } from '@/model/Log'
import { getLogInfo } from '@/utils/api/log'

const state = reactive({
  tableData: [] as Log[], // 数据列表
  currentPage: 1,
  pageSize: 10,
  totals: 0 // 一共有多少页
})

//加载数据
onMounted(() => {
  getLogInfo(state.currentPage, state.pageSize, 2).then((data) => {
    state.tableData = data.data
    state.totals = data.totals
    console.log(state)
  })
})

const changePage = (newPage: number) => {
  state.currentPage = newPage
  getLogInfo(state.currentPage, state.pageSize, 2).then((data) => {
    state.tableData = data.data
    state.totals = data.totals
    console.log(state.totals)
  })
}
</script>

<style scoped>
.main-card {
  display: flex;
  border-radius: 10px;
  margin-bottom: 50px;
  width: auto;
  height: 660px;
}
</style>
