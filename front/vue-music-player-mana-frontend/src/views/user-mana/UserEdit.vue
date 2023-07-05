<template>
  <div class="space">
    <el-row :gutter="20">
      <el-col :span="2">
        <span class="input-mention">用户名</span>
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
    <el-table :data="state.tableData" height="680" style="width: 100%">
      <el-table-column fixed prop="userId" label="ID" width="60" />
      <el-table-column fixed prop="picUrl" label="头像" width="120">
        <template #default="scope">
          <el-avatar shape="square" :size="90" :fit="'cover'" :src="scope.row.picUrl" />
        </template>
      </el-table-column>
      <el-table-column prop="username" label="姓名" width="85" />
      <el-table-column prop="nickname" label="昵称" width="105" />
      <el-table-column prop="gender" label="性别" width="70" />
      <el-table-column prop="age" label="年龄" width="70" />
      <el-table-column prop="email" label="邮箱" width="190" />
      <el-table-column prop="phone" label="电话" width="160" />
      <el-table-column prop="password" label="密码" width="190" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <el-button type="warning" :icon="Edit" @click="EditClick(scope.row.userId)"
            >修改</el-button
          >
          <el-button type="danger" :icon="Delete" @click="DeleteClick(scope.row.userId)"
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
          <el-input v-model="addDialog.data.userId" :readonly="true" />
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="3">
          <span>姓名</span>
        </el-col>
        <el-col :span="6">
          <el-input v-model="addDialog.data.username" />
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="3">
          <span>年龄</span>
        </el-col>
        <el-col :span="6">
          <el-input v-model="addDialog.data.age" />
        </el-col>
      </el-form-item>
      <el-form-item label="用户性别">
        <el-radio-group v-model="addDialog.data.gender">
          <el-radio label="男" />
          <el-radio label="女" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="用户昵称">
        <el-input v-model="addDialog.data.nickname"></el-input>
      </el-form-item>
      <el-form-item label="用户密码">
        <el-input v-model="addDialog.data.password"></el-input>
      </el-form-item>
      <el-form-item label="用户邮箱">
        <el-input v-model="addDialog.data.email"></el-input>
      </el-form-item>
      <el-form-item label="用户电话">
        <el-input v-model="addDialog.data.phone"></el-input>
      </el-form-item>
      <el-form-item label="用户头像">
        <el-upload
          class="avatar-uploader"
          action="#"
          :show-file-list="false"
          list-type="picture-card"
          :limit="1"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img
            style="width: 147px; height: 147px"
            v-if="addDialog.data.picUrl"
            :src="addDialog.data.picUrl"
            class="avatar"
          />
          <el-icon v-else><Plus /></el-icon>
        </el-upload>
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
          <el-input v-model="modifyDialog.data.userId" :readonly="true" />
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="3">
          <span>姓名</span>
        </el-col>
        <el-col :span="6">
          <el-input v-model="modifyDialog.data.username" />
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="3">
          <span>年龄</span>
        </el-col>
        <el-col :span="6">
          <el-input v-model="modifyDialog.data.age" />
        </el-col>
      </el-form-item>
      <el-form-item label="用户性别">
        <el-radio-group v-model="modifyDialog.data.gender">
          <el-radio label="男" />
          <el-radio label="女" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="用户昵称">
        <el-input v-model="modifyDialog.data.nickname"></el-input>
      </el-form-item>
      <el-form-item label="用户密码">
        <el-input v-model="modifyDialog.data.password"></el-input>
      </el-form-item>
      <el-form-item label="用户邮箱">
        <el-input v-model="modifyDialog.data.email"></el-input>
      </el-form-item>
      <el-form-item label="用户电话">
        <el-input v-model="modifyDialog.data.phone"></el-input>
      </el-form-item>
      <el-form-item label="用户头像">
        <el-upload
          class="avatar-uploader"
          action="#"
          :show-file-list="false"
          list-type="picture-card"
          :limit="1"
          :on-success="mhandleAvatarSuccess"
          :before-upload="mbeforeAvatarUpload"
        >
          <img
            style="width: 147px; height: 147px"
            v-if="modifyDialog.data.picUrl"
            :src="modifyDialog.data.picUrl"
            class="avatar"
          />
          <el-icon v-else><Plus /></el-icon>
        </el-upload>
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
        <el-button type="primary" @click="deleteUserInfo"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { Check, Delete, Edit, Search, Share, Upload } from '@element-plus/icons-vue'
import {
  getCustomerInfo,
  getTheCustomerInfo,
  deleteTheCustomerInfo,
  modifyTheCustomerInfo,
  addTheCustomerInfo
} from '@/utils/api/user'
import type { CustomerInfo } from '@/model/UserInfo'
import type { UploadProps } from 'element-plus'
import { add, fill } from 'lodash'
import axios from 'axios'
import { AlertError, AlertSuccess } from '@/utils/alert/AlertPop'

const input = reactive({ input: '' })

const addDialog = reactive({
  visable: false,
  id: -1,
  data: {} as CustomerInfo
})

const deleteDialog = reactive({
  visable: false,
  id: -1
})

const modifyDialog = reactive({
  visable: false,
  id: -1,
  data: {} as CustomerInfo
})

const state = reactive({
  tableData: [] as CustomerInfo[], // 数据列表
  currentPage: 1,
  pageSize: 5,
  totals: 0 // 一共有多少页
})

//加载数据
onMounted(() => {
  getCustomerInfo(state.currentPage, state.pageSize).then((data) => {
    state.tableData = data.data
    state.totals = data.totals
    console.log(state.totals)
  })
})

const SearchClick = () => {
  console.log('click')
  getTheCustomerInfo(input.input).then((data) => {
    if (data.code === 300) {
      ElMessage.warning('找不到该用户!')
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
  const user = state.tableData.find((item) => item.userId === id)
  if (user) {
    modifyDialog.data = { ...user }
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
  getCustomerInfo(state.currentPage, state.pageSize).then((data) => {
    state.tableData = data.data
    state.totals = data.totals
    console.log(state.totals)
  })
}

const deleteUserInfo = () => {
  // 发送删除请求
  deleteTheCustomerInfo(deleteDialog.id)
  // 修改前端数据
  const index = state.tableData.findIndex((item) => item.userId === deleteDialog.id)
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
  modifyTheCustomerInfo(modifyDialog.data)
  // 修改前端数据
  // 遍历 tableData 数组
  for (let i = 0; i < state.tableData.length; i++) {
    const item = state.tableData[i]
    if (item.userId === modifyDialog.id) {
      // 找到目标 userId 对应的元素，进行修改
      state.tableData[i] = { ...item, ...modifyDialog.data }
      break // 找到后可以提前结束循环
    }
  }
  modifyDialog.visable = false
}

const confirmAdd = () => {
  // 发送添加请求
  addTheCustomerInfo(addDialog.data).then((data) => {
    state.currentPage = data.currentPage
    state.totals = data.totals
    state.tableData = data.data
    console.log(state.tableData)
  })
  addDialog.data = {} as CustomerInfo
  addDialog.visable = false
}

const changePage = (newPage: number) => {
  state.currentPage = newPage
  getCustomerInfo(state.currentPage, state.pageSize).then((data) => {
    state.tableData = data.data
    state.totals = data.totals
    console.log(state.totals)
  })
}

const mhandleAvatarSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
  modifyDialog.data.picUrl = URL.createObjectURL(uploadFile.raw!)
  console.log(modifyDialog.data.picUrl)
}

const mbeforeAvatarUpload: UploadProps['beforeUpload'] = (file) => {
  if (file.type !== 'image/png') {
    ElMessage.error('头像必须是PNG文件！')
    return false
  } else if (file.size / 1024 / 1024 > 2) {
    ElMessage.error('头像不能超过 2MB!')
    return false
  }
  muploadAction(file)
  return false
}

const url = 'http://127.0.0.1:4000/User/uploadPic'
// 照片上传请求
const muploadAction = (file: File) => {
  let formData = new FormData()
  formData.append('file', file)
  //加上userID传进来
  formData.append('userId',modifyDialog.data.userId.toString())
  try {
    axios
      .post(url, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then((response) => {
        console.log(response)
        modifyDialog.data.picUrl = URL.createObjectURL(file)
        console.log(modifyDialog.data.picUrl)
        if(response.data.code==200){
          AlertSuccess('成功上传照片')
          modifyDialog.data.picUrl=response.data.picUrl
          console.log('response.data.picUrl'+response.data.picUrl);
          
        }else{
          AlertError('上传照片失败')
        }
        
      })
  } catch (error) {
    console.log(error)
  }
}

const handleAvatarSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
  addDialog.data.picUrl = URL.createObjectURL(uploadFile.raw!)
  console.log(addDialog.data.picUrl)
}

const beforeAvatarUpload: UploadProps['beforeUpload'] = (file) => {
  if (file.type !== 'image/png') {
    ElMessage.error('头像必须是PNG文件！')
    return false
  } else if (file.size / 1024 / 1024 > 2) {
    ElMessage.error('头像不能超过 2MB!')
    return false
  }
  uploadAction(file)
  return false
}

// 照片上传请求
const uploadAction = (file: File) => {
  let formData = new FormData()
  formData.append('file', file)
  try {
    axios
      .post(url, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then((response) => {
        console.log(response)
        addDialog.data.picUrl = URL.createObjectURL(file)
        console.log(addDialog.data.picUrl)
      })
  } catch (error) {
    console.log(error)
  }
}
</script>

<style scoped>
.main-card {
  display: flex;
  border-radius: 10px;
  margin-bottom: 0px;
  width: auto;
  height: 750px;
}

.input-mention {
  font-size: 18px;
}

.space {
  margin-bottom: 20px;
}
</style>
