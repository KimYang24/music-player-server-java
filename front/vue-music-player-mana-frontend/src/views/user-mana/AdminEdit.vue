<template>
  <div class="space">
    <el-row :gutter="20">
      <el-col :span="2">
        <span class="input-mention">管理员名称</span>
      </el-col>
      <el-col :span="5">
        <el-input v-model="input.input" placeholder="请输入内容"></el-input>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" :icon="Search" @click="SearchClick">查询</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" :icon="Upload" @click="AddClick">添加</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" :icon="Check" @click="AllClick">全部</el-button>
      </el-col>
    </el-row>
  </div>

  <el-card class="main-card">
    <el-table :data="state.tableData" height="660" style="width: 100%">
      <el-table-column fixed prop="adminId" label="ID" width="100" />
      <el-table-column prop="adminName" label="名称" width="100" />
      <el-table-column prop="password" label="密码" width="300" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <el-button type="warning" :icon="Edit" @click="EditClick(scope.row.adminId)"
            >修改</el-button
          >
          <el-button type="danger" :icon="Delete" @click="DeleteClick(scope.row.adminId)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!--分页按钮-->
    <el-pagination
      background
      style="margin-top: 10px"
      layout="prev, pager, next"
      :total="state.totals"
      :page-size="state.pageSize"
      :current-page="state.currentPage"
      @current-change="changePage"
    />
  </el-card>

  <!--添加对话框-->
  <el-dialog v-model="addDialog.visable" title="添加" width="27%">
    <el-form :model="addDialog.data" label-width="100px">
      <el-form-item label="ID">
        <el-col :span="4">
          <el-input v-model="addDialog.data.adminId" :readonly="true" />
        </el-col>
      </el-form-item>
      <el-form-item label="管理员名称">
        <el-input v-model="addDialog.data.adminName"></el-input>
      </el-form-item>
      <el-form-item label="管理员密码">
        <el-input v-model="addDialog.data.password"></el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addDialog.visable = false">Cancel</el-button>
        <el-button type="primary" @click="confirmAdd"> Confirm </el-button>
      </span>
    </template>
  </el-dialog>

  <!--修改对话框-->
  <el-dialog v-model="modifyDialog.visable" title="信息修改" width="27%">
    <el-form :model="modifyDialog.data" label-width="100px">
      <el-form-item label="ID">
        <el-col :span="4">
          <el-input v-model="modifyDialog.data.adminId" :readonly="true" />
        </el-col>
      </el-form-item>
      <el-form-item label="管理员名称">
        <el-input v-model="modifyDialog.data.adminName"></el-input>
      </el-form-item>
      <el-form-item label="管理员密码">
        <el-input v-model="modifyDialog.data.password"></el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="modifyDialog.visable = false">Cancel</el-button>
        <el-button type="primary" @click="confirmModify"> Confirm </el-button>
      </span>
    </template>
  </el-dialog>

  <!--删除对话框-->
  <el-dialog v-model="deleteDialog.visable" title="系统提示" width="20%">
    <span>是否确认删除？</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="deleteDialog.visable = false">取消</el-button>
        <el-button type="primary" @click="deleteAdminInfo"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { Check, Delete, Edit, Search, Share, Upload } from '@element-plus/icons-vue'
import {
  getAdminInfo,
  getTheAdminInfo,
  deleteTheAdminInfo,
  modifyTheAdminInfo,
  addTheAdminInfo
} from '@/utils/api/admin'
import type { AdminInfo } from '@/model/UserInfo'
import type { UploadProps } from 'element-plus'
import { add, fill } from 'lodash'
import axios from 'axios'

const input = reactive({ input: '' })

const addDialog = reactive({
  visable: false,
  id: -1,
  data: {} as AdminInfo
})

const deleteDialog = reactive({
  visable: false,
  id: -1
})

const modifyDialog = reactive({
  visable: false,
  id: -1,
  data: {} as AdminInfo
})

const state = reactive({
  tableData: [] as AdminInfo[], // 数据列表
  currentPage: 1,
  pageSize: 11,
  totals: 0 // 一共有多少页
})

//加载数据
onMounted(() => {
  getAdminInfo(state.currentPage, state.pageSize).then((data) => {
    state.tableData = data.data
    state.totals = data.totals
    console.log(state.totals)
  })
})

const SearchClick = () => {
  console.log('click')
  getTheAdminInfo(input.input).then((data) => {
    if (data.code === 300) {
      ElMessage.warning('找不到该管理员.')
    } else if (data.code === 200) {
      console.log(data.data)
      state.tableData = data.data
    }
  })
}

const AddClick = () => {
  console.log('click')
  addDialog.visable = true
}

const EditClick = (id: number) => {
  console.log('click')
  modifyDialog.id = id
  const admin = state.tableData.find((item) => item.adminId === id)
  if (admin) {
    modifyDialog.data = { ...admin }
  }
  modifyDialog.visable = true
  console.log(modifyDialog.id)
}

const DeleteClick = (id: number) => {
  console.log('click')
  deleteDialog.id = id
  deleteDialog.visable = true
}

const AllClick = () => {
  getAdminInfo(state.currentPage, state.pageSize).then((data) => {
    state.tableData = data.data
    state.totals = data.totals
    console.log(state.totals)
  })
}

const deleteAdminInfo = () => {
  // 发送删除请求
  deleteTheAdminInfo(deleteDialog.id)
  // 修改前端数据
  const index = state.tableData.findIndex((item) => item.adminId === deleteDialog.id)
  console.log(index)
  if (index !== -1) {
    state.tableData.splice(index, 1)
  }
  console.log(state.tableData)
  deleteDialog.id = -1
  deleteDialog.visable = false
}

const confirmModify = () => {
  // 发送修改请求
  modifyTheAdminInfo(modifyDialog.data)
  // 修改前端数据
  // 遍历 tableData 数组
  for (let i = 0; i < state.tableData.length; i++) {
    const item = state.tableData[i]
    if (item.adminId === modifyDialog.id) {
      // 找到目标 adminId 对应的元素，进行修改
      state.tableData[i] = { ...item, ...modifyDialog.data }
      break // 找到后可以提前结束循环
    }
  }
  modifyDialog.visable = false
}

const confirmAdd = () => {
  // 发送添加请求
  addTheAdminInfo(addDialog.data).then((data) => {
    state.currentPage = data.currentPage
    state.totals = data.totals
    state.tableData = data.data
    console.log(state.tableData)
  })
  addDialog.data = {} as AdminInfo
  addDialog.visable = false
}

const changePage = (newPage: number) => {
  state.currentPage = newPage
  getAdminInfo(state.currentPage, state.pageSize).then((data) => {
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
  margin-bottom: 0px;
  width: auto;
  height: 730px;
}

.input-mention {
  font-size: 18px;
}

.space {
  margin-bottom: 20px;
}
</style>
