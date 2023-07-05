<template>
  <div class="space">
    <el-row :gutter="20">
      <el-col :span="2">
        <span class="input-mention">歌曲名</span>
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
      <el-table-column fixed prop="songId" label="ID" width="60" />
      <el-table-column fixed prop="picUrl" label="专辑封面" width="120">
        <template #default="scope">
          <el-avatar shape="square" :size="90" :fit="'cover'" :src="scope.row.picUrl" />
        </template>
      </el-table-column>
      <el-table-column prop="name" label="歌名" width="150" />
      <el-table-column prop="artist" label="歌手名" width="150" />
      <el-table-column prop="album" label="专辑名" width="150" />
      <el-table-column prop="publishTime" label="发行时间" width="150" />
      <el-table-column prop="type" label="歌曲类别" width="80" />
      <el-table-column prop="duration" label="歌曲时间(s)" width="80" />
      <el-table-column prop="mark" label="评论数" width="80" />
      <el-table-column prop="pop" label="点赞数" width="80" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <el-button type="warning" :icon="Edit" @click="EditClick(scope.row.songId)"
            >修改</el-button
          >
          <el-button type="danger" :icon="Delete" @click="DeleteClick(scope.row.songId)"
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
        <el-col :span="3">
          <el-input v-model="addDialog.data.songId" :readonly="true" />
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="6">
          <span>歌曲类型</span>
        </el-col>
        <el-col :span="6">
          <el-input v-model="addDialog.data.type" />
        </el-col>
      </el-form-item>
      <el-form-item label="歌曲名">
        <el-input v-model="addDialog.data.name"></el-input>
      </el-form-item>
      <el-form-item label="歌手名">
        <el-input v-model="addDialog.data.artist"></el-input>
      </el-form-item>
      <el-form-item label="专辑名">
        <el-input v-model="addDialog.data.album"></el-input>
      </el-form-item>
      <el-form-item label="音乐时长(s)">
        <el-col :span="4">
          <el-input v-model="addDialog.data.duration"></el-input>
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="4">
          <span>点赞数</span>
        </el-col>
        <el-col :span="4">
          <el-input v-model="addDialog.data.pop" />
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="4">
          <span>评论数</span>
        </el-col>
        <el-col :span="4">
          <el-input v-model="addDialog.data.mark" />
        </el-col>
      </el-form-item>

      <el-form-item label="专辑封面">
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
  <el-dialog v-model="modifyDialog.visable" title="修改" width="27%">
    <el-form :model="modifyDialog.data" label-width="100px">
      <el-form-item label="ID">
        <el-col :span="3">
          <el-input v-model="modifyDialog.data.songId" :readonly="true" />
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="6">
          <span>歌曲类型</span>
        </el-col>
        <el-col :span="6">
          <el-input v-model="modifyDialog.data.type" />
        </el-col>
      </el-form-item>
      <el-form-item label="歌曲名">
        <el-input v-model="modifyDialog.data.name"></el-input>
      </el-form-item>
      <el-form-item label="歌手名">
        <el-input v-model="modifyDialog.data.artist"></el-input>
      </el-form-item>
      <el-form-item label="专辑名">
        <el-input v-model="modifyDialog.data.album"></el-input>
      </el-form-item>
      <el-form-item label="音乐时长(s)">
        <el-col :span="4">
          <el-input v-model="modifyDialog.data.duration"></el-input>
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="4">
          <span>点赞数</span>
        </el-col>
        <el-col :span="4">
          <el-input v-model="modifyDialog.data.pop" />
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="4">
          <span>评论数</span>
        </el-col>
        <el-col :span="4">
          <el-input v-model="modifyDialog.data.mark" />
        </el-col>
      </el-form-item>

      <el-form-item label="专辑封面">
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
        <el-button type="primary" @click="deleteSongInfo"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { Check, Delete, Edit, Search, Share, Upload } from '@element-plus/icons-vue'
import {
  getSongInfo,
  getTheSongInfo,
  deleteTheSongInfo,
  addSongInfo,
  modifySongInfo
} from '@/utils/api/song'
import type { SongInfo } from '@/model/SongInfo'
import type { UploadProps } from 'element-plus'
import { add, fill } from 'lodash'
import axios from 'axios'

const input = reactive({ input: '' })

const addDialog = reactive({
  visable: false,
  id: -1,
  data: {} as SongInfo
})

const deleteDialog = reactive({
  visable: false,
  id: -1
})

const modifyDialog = reactive({
  visable: false,
  id: -1,
  data: {} as SongInfo
})

const state = reactive({
  tableData: [] as SongInfo[], // 数据列表
  currentPage: 1,
  pageSize: 5,
  totals: 0 // 一共有多少页
})

//加载数据
onMounted(() => {
  getSongInfo(state.currentPage, state.pageSize).then((data) => {
    state.tableData = data.data
    state.totals = data.totals
    console.log(state.totals)
  })
})

const SearchClick = () => {
  console.log('click')
  getTheSongInfo(input.input).then((data) => {
    if (data.code === 300) {
      ElMessage.warning('找不到该音乐!')
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
  const song = state.tableData.find((item) => item.songId === id)
  if (song) {
    modifyDialog.data = { ...song }
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
  getSongInfo(state.currentPage, state.pageSize).then((data) => {
    state.tableData = data.data
    state.totals = data.totals
    console.log(state.totals)
  })
}

const deleteSongInfo = () => {
  // 发送删除请求
  deleteTheSongInfo(deleteDialog.id)
  // 修改前端数据
  const index = state.tableData.findIndex((item) => item.songId === deleteDialog.id)
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
  modifySongInfo(modifyDialog.data).then((data) => {
    if (data.code == 200) {
      // 修改前端数据
      // 遍历 tableData 数组
      for (let i = 0; i < state.tableData.length; i++) {
        const item = state.tableData[i]
        if (item.songId === modifyDialog.id) {
          // 找到目标 userId 对应的元素，进行修改
          state.tableData[i] = { ...item, ...modifyDialog.data }
          break // 找到后可以提前结束循环
        }
      }
      modifyDialog.visable = false
      ElMessage({ message: '修改成功！', type: 'success' })
    } else {
      ElMessage({ message: '修改失败！', type: 'error' })
    }
  })
}

const confirmAdd = () => {
  // 发送添加请求
  addSongInfo(addDialog.data).then((data) => {
    console.log(data.code)
    if (data.code == 200) {
      state.currentPage = data.currentPage
      state.totals = data.totals
      state.tableData = data.data
      console.log(state.tableData)
      console.log('state.currentPage' + state.currentPage)

      ElMessage({ message: '添加成功！', type: 'success' })
    } else {
      ElMessage({ message: '添加失败！', type: 'error' })
    }
  })
  addDialog.data = {} as SongInfo
  addDialog.visable = false
}

const changePage = (newPage: number) => {
  state.currentPage = newPage
  getSongInfo(state.currentPage, state.pageSize).then((data) => {
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

// 照片上传请求
const muploadAction = (file: File) => {
  let formData = new FormData()
  formData.append('file', file)
  const url = 'https://mock.apifox.cn/m1/2794549-0-default/User/uploadPic'
  try {
    axios
      .post('https://mock.apifox.cn/m1/2794549-0-default/User/uploadPic', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then((response) => {
        console.log(response)
        modifyDialog.data.picUrl = URL.createObjectURL(file)
        console.log(modifyDialog.data.picUrl)
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
  const url = 'https://mock.apifox.cn/m1/2794549-0-default/User/uploadPic'
  try {
    axios
      .post('https://mock.apifox.cn/m1/2794549-0-default/User/uploadPic', formData, {
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
