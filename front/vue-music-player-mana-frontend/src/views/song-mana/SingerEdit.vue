<template>
  <div class="space">
    <el-row :gutter="20">
      <el-col :span="2">
        <span class="input-mention">歌手名</span>
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
    <el-table :data="state.tableData" height="680" style="width: 100%" @cell-dblclick="DBClick">
      <el-table-column fixed prop="artistId" label="ID" width="60" />
      <el-table-column fixed prop="picUrl" label="歌手封面" width="120">
        <template #default="scope">
          <el-avatar shape="square" :size="90" :fit="'cover'" :src="scope.row.picUrl" />
        </template>
      </el-table-column>
      <el-table-column prop="artist" label="歌手名" width="200" />
      <el-table-column prop="songSize" label="歌曲数量" width="200" />
      <el-table-column prop="albumSize" label="专辑数量" width="200" />
      <el-table-column prop="location" label="地域" width="200" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <el-button type="warning" :icon="Edit" @click="EditClick(scope.row.artistId)"
            >修改</el-button
          >
          <el-button type="danger" :icon="Delete" @click="DeleteClick(scope.row.artistId)"
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
          <el-input v-model="addDialog.data.artistId" :readonly="true" />
        </el-col>
      </el-form-item>
      <el-form-item label="歌手名">
        <el-input v-model="addDialog.data.artist"></el-input>
      </el-form-item>
      <el-form-item label="歌曲数量">
        <el-col :span="5">
          <el-input v-model="addDialog.data.songSize"></el-input>
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="5">
          <span>专辑数量</span>
        </el-col>
        <el-col :span="5">
          <el-input v-model="addDialog.data.albumSize"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="歌手简介">
        <el-input v-model="addDialog.data.profile" :rows="5" type="textarea" placeholder="请输入" />
      </el-form-item>

      <el-form-item label="歌手封面">
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
          <el-input v-model="modifyDialog.data.artistId" :readonly="true" />
        </el-col>
      </el-form-item>
      <el-form-item label="歌手名">
        <el-input v-model="modifyDialog.data.artist"></el-input>
      </el-form-item>
      <el-form-item label="歌曲数量">
        <el-col :span="5">
          <el-input v-model="modifyDialog.data.songSize"></el-input>
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="5">
          <span>专辑数量</span>
        </el-col>
        <el-col :span="5">
          <el-input v-model="modifyDialog.data.albumSize"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="歌手简介">
        <el-input
          v-model="modifyDialog.data.profile"
          :rows="5"
          type="textarea"
          placeholder="请输入"
        />
      </el-form-item>

      <el-form-item label="歌手封面">
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
        <el-button type="primary" @click="deleteArtistInfo"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>

  <!--简介对话框-->
  <el-dialog v-model="profileDialog.visable" title="简介" width="20%">
    <span>歌手简介：{{ profileDialog.profile }}</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="profileDialog.visable = false">取消</el-button>
        <el-button type="primary" @click="profileDialog.visable = false"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>

  <!--歌曲对话框-->
  <el-dialog v-model="songDialog.visable" title="歌曲" width="60%" @close="AllClick">
    <el-table :data="songDialog.tableData" height="600" style="width: 100%">
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
          <el-button type="warning" :icon="Edit" @click="songEditClick(scope.row.songId)"
            >修改</el-button
          >
          <el-button type="danger" :icon="Delete" @click="songDeleteClick(scope.row.songId)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
  <!--歌曲删除对话框-->
  <el-dialog v-model="songDeleteDialog.visable" title="系统提示" width="20%">
    <span>是否确认删除？</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="deleteDialog.visable = false">取消</el-button>
        <el-button type="primary" @click="deleteSongInfo"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>
  <!--修改歌曲对话框-->
  <el-dialog v-model="songModifyDialog.visable" title="修改" width="27%">
    <el-form :model="songModifyDialog.data" label-width="100px">
      <el-form-item label="ID">
        <el-col :span="3">
          <el-input v-model="songModifyDialog.data.songId" :readonly="true" />
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="6">
          <span>歌曲类型</span>
        </el-col>
        <el-col :span="6">
          <el-input v-model="songModifyDialog.data.type" />
        </el-col>
      </el-form-item>
      <el-form-item label="歌曲名">
        <el-input v-model="songModifyDialog.data.name"></el-input>
      </el-form-item>
      <el-form-item label="歌手名">
        <el-input v-model="songModifyDialog.data.artist"></el-input>
      </el-form-item>
      <el-form-item label="专辑名">
        <el-input v-model="songModifyDialog.data.album"></el-input>
      </el-form-item>
      <el-form-item label="音乐时长(s)">
        <el-col :span="4">
          <el-input v-model="songModifyDialog.data.duration"></el-input>
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="4">
          <span>点赞数</span>
        </el-col>
        <el-col :span="4">
          <el-input v-model="songModifyDialog.data.pop" />
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="4">
          <span>评论数</span>
        </el-col>
        <el-col :span="4">
          <el-input v-model="songModifyDialog.data.mark" />
        </el-col>
      </el-form-item>

      <el-form-item label="专辑封面">
        <el-upload
          class="avatar-uploader"
          action="#"
          :show-file-list="false"
          list-type="picture-card"
          :limit="1"
          :on-success="smhandleAvatarSuccess"
          :before-upload="smbeforeAvatarUpload"
        >
          <img
            style="width: 147px; height: 147px"
            v-if="songModifyDialog.data.picUrl"
            :src="songModifyDialog.data.picUrl"
            class="avatar"
          />
          <el-icon v-else><Plus /></el-icon>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="songModifyDialog.visable = false">Cancel</el-button>
        <el-button type="primary" @click="songconfirmModify"> Confirm </el-button>
      </span>
    </template>
  </el-dialog>

  <!--专辑对话框-->
  <el-dialog v-model="albumDialog.visable" title="歌曲" width="60%">
    <el-table :data="albumDialog.tableData" height="600" style="width: 100%">
      <el-table-column fixed prop="albumId" label="ID" width="60" />
      <el-table-column fixed prop="picUrl" label="专辑封面" width="120">
        <template #default="scope">
          <el-avatar shape="square" :size="90" :fit="'cover'" :src="scope.row.picUrl" />
        </template>
      </el-table-column>
      <el-table-column prop="album" label="专辑名" width="180" />
      <el-table-column prop="artist" label="歌手名" width="180" />
      <el-table-column prop="size" label="歌曲数量" width="120" />
      <el-table-column prop="type" label="类型" width="120" />
      <el-table-column prop="publishTime" label="发行时间" width="200" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <el-button type="warning" :icon="Edit" @click="albumEditClick(scope.row.albumId)"
            >修改</el-button
          >
          <el-button type="danger" :icon="Delete" @click="albumDeleteClick(scope.row.albumId)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
  <!--修改对话框-->
  <el-dialog v-model="albummodifyDialog.visable" title="修改" width="27%">
    <el-form :model="albummodifyDialog.data" label-width="100px">
      <el-form-item label="ID">
        <el-col :span="3">
          <el-input v-model="albummodifyDialog.data.albumId" :readonly="true" />
        </el-col>
      </el-form-item>
      <el-form-item label="专辑名">
        <el-input v-model="albummodifyDialog.data.album"></el-input>
      </el-form-item>
      <el-form-item label="歌手名">
        <el-input v-model="albummodifyDialog.data.artist"></el-input>
      </el-form-item>
      <el-form-item label="歌曲数量">
        <el-col :span="5">
          <el-input v-model="albummodifyDialog.data.size"></el-input>
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="5">
          <span>类型</span>
        </el-col>
        <el-col :span="7">
          <el-input v-model="albummodifyDialog.data.type"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="发行时间">
        <el-input v-model="albummodifyDialog.data.publishTime"></el-input>
      </el-form-item>
      <el-form-item label="专辑简介">
        <el-input
          v-model="albummodifyDialog.data.profile"
          :rows="5"
          type="textarea"
          placeholder="请输入"
        />
      </el-form-item>

      <el-form-item label="专辑封面">
        <el-upload
          class="avatar-uploader"
          action="#"
          :show-file-list="false"
          list-type="picture-card"
          :limit="1"
          :on-success="amhandleAvatarSuccess"
          :before-upload="ambeforeAvatarUpload"
        >
          <img
            style="width: 147px; height: 147px"
            v-if="albummodifyDialog.data.picUrl"
            :src="albummodifyDialog.data.picUrl"
            class="avatar"
          />
          <el-icon v-else><Plus /></el-icon>
        </el-upload>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="albummodifyDialog.visable = false">Cancel</el-button>
        <el-button type="primary" @click="albumconfirmModify"> Confirm </el-button>
      </span>
    </template>
  </el-dialog>

  <!--删除对话框-->
  <el-dialog v-model="albumdeleteDialog.visable" title="系统提示" width="20%">
    <span>是否确认删除？</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="albumdeleteDialog.visable = false">取消</el-button>
        <el-button type="primary" @click="albumdeleteAlbumInfo"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { Check, Delete, Edit, Search, Share, Upload } from '@element-plus/icons-vue'
import {
  getArtistInfo,
  getTheArtistInfo,
  deleteTheArtistInfo,
  addArtistInfo,
  modifyArtistInfo
} from '@/utils/api/artist'
import { getArtistAllSong, deleteTheSongInfo, modifySongInfo } from '@/utils/api/song'
import { getArtistAllAlbum, modifyAlbumInfo, deleteTheAlbumInfo } from '@/utils/api/album'
import type { ArtistInfo } from '@/model/ArtistInfo'
import type { SongInfo } from '@/model/SongInfo'
import type { UploadProps } from 'element-plus'
import { add, fill } from 'lodash'
import axios from 'axios'
import type { AlbumInfo } from '@/model/albumInfo'

const input = reactive({ input: '' })

const addDialog = reactive({
  visable: false,
  id: -1,
  data: {} as ArtistInfo
})

const deleteDialog = reactive({
  visable: false,
  id: -1
})

const modifyDialog = reactive({
  visable: false,
  id: -1,
  data: {} as ArtistInfo
})

const profileDialog = reactive({
  visable: false,
  profile: {} as string
})

const songDialog = reactive({
  visable: false,
  tableData: [] as SongInfo[]
})

const albumDialog = reactive({
  visable: false,
  tableData: [] as AlbumInfo[]
})

const state = reactive({
  tableData: [] as ArtistInfo[], // 数据列表
  currentPage: 1,
  pageSize: 5,
  totals: 0 // 一共有多少页
})

//加载数据
onMounted(() => {
  getArtistInfo(state.currentPage, state.pageSize).then((data) => {
    state.tableData = data.data
    state.totals = data.totals
    console.log(state)
  })
})

const DBClick = (row: ArtistInfo, cell: any, column: any) => {
  if (cell.property === 'artist') {
    profileDialog.visable = true
    profileDialog.profile = row.profile
    console.log('okokokok')
  } else if (cell.property === 'songSize') {
    getArtistAllSong(row.artistId).then((data) => {
      songDialog.visable = true
      songDialog.tableData = data.data
      console.log(songDialog.tableData)
    })
  } else if (cell.property === 'albumSize') {
    getArtistAllAlbum(row.artistId).then((data) => {
      albumDialog.visable = true
      albumDialog.tableData = data.data
      console.log(albumDialog.tableData)
    })
  }
}

const SearchClick = () => {
  console.log('click')
  getTheArtistInfo(input.input).then((data) => {
    if (data.code === 300) {
      ElMessage.warning('找不到该歌手!')
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
  const song = state.tableData.find((item) => item.artistId === id)
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
  getArtistInfo(state.currentPage, state.pageSize).then((data) => {
    state.tableData = data.data
    state.totals = data.totals
    console.log(state.totals)
  })
}

const deleteArtistInfo = () => {
  // 发送删除请求
  deleteTheArtistInfo(deleteDialog.id)
  // 修改前端数据
  const index = state.tableData.findIndex((item) => item.artistId === deleteDialog.id)
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
  modifyArtistInfo(modifyDialog.data).then((data) => {
    if (data.code === 200) {
      // 修改前端数据
      // 遍历 tableData 数组
      for (let i = 0; i < state.tableData.length; i++) {
        const item = state.tableData[i]
        if (item.artistId === modifyDialog.id) {
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
  addArtistInfo(addDialog.data).then((data) => {
    console.log(data.code)
    if (data.code === 200) {
      ElMessage({ message: '添加成功！', type: 'success' })
      state.currentPage = data.currentPage
      state.totals = data.totals
      state.tableData = data.data
      getArtistInfo(state.currentPage, state.pageSize).then((data) => {
        state.tableData = data.data
        state.totals = data.totals
        console.log(state.totals)
      })
      ElMessage({ message: '添加成功！', type: 'success' })
      console.log(state.tableData)
      ElMessage({ message: '添加成功！', type: 'success' })
    } else {
      ElMessage({ message: '添加失败！', type: 'error' })
    }
  })
  addDialog.data = {} as ArtistInfo
  addDialog.visable = false
}

const changePage = (newPage: number) => {
  state.currentPage = newPage
  getArtistInfo(state.currentPage, state.pageSize).then((data) => {
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

const smhandleAvatarSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
  songModifyDialog.data.picUrl = URL.createObjectURL(uploadFile.raw!)
  console.log(songModifyDialog.data.picUrl)
}

const smbeforeAvatarUpload: UploadProps['beforeUpload'] = (file) => {
  if (file.type !== 'image/png') {
    ElMessage.error('头像必须是PNG文件！')
    return false
  } else if (file.size / 1024 / 1024 > 2) {
    ElMessage.error('头像不能超过 2MB!')
    return false
  }
  smuploadAction(file)
  return false
}

// 照片上传请求
const smuploadAction = (file: File) => {
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
        songModifyDialog.data.picUrl = URL.createObjectURL(file)
        console.log(songModifyDialog.data.picUrl)
      })
  } catch (error) {
    console.log(error)
  }
}

const amhandleAvatarSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
  albummodifyDialog.data.picUrl = URL.createObjectURL(uploadFile.raw!)
  console.log(albummodifyDialog.data.picUrl)
}

const ambeforeAvatarUpload: UploadProps['beforeUpload'] = (file) => {
  if (file.type !== 'image/png') {
    ElMessage.error('头像必须是PNG文件！')
    return false
  } else if (file.size / 1024 / 1024 > 2) {
    ElMessage.error('头像不能超过 2MB!')
    return false
  }
  amuploadAction(file)
  return false
}

// 照片上传请求
const amuploadAction = (file: File) => {
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
        albummodifyDialog.data.picUrl = URL.createObjectURL(file)
        console.log(albummodifyDialog.data.picUrl)
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

const songDeleteDialog = reactive({
  visable: false,
  id: -1
})

const songDeleteClick = (id: number) => {
  console.log('click')
  songDeleteDialog.id = id
  songDeleteDialog.visable = true
}

const deleteSongInfo = () => {
  // 发送删除请求
  deleteTheSongInfo(deleteDialog.id)
  // 修改前端数据
  const index = songDialog.tableData.findIndex((item) => item.songId === songDeleteDialog.id)
  console.log(index)
  if (index !== -1) {
    songDialog.tableData.splice(index, 1)
  }
  console.log(songDialog.tableData)
  songDeleteDialog.id = -1
  songDeleteDialog.visable = false
}

const songModifyDialog = reactive({
  visable: false,
  id: -1,
  data: {} as SongInfo
})

const songEditClick = (id: number) => {
  console.log('click')
  songModifyDialog.id = id
  const song = songDialog.tableData.find((item) => item.songId === id)
  if (song) {
    songModifyDialog.data = { ...song }
  }
  songModifyDialog.visable = true
  console.log(songModifyDialog.id)
}

const songconfirmModify = () => {
  // 发送修改请求
  modifySongInfo(songModifyDialog.data).then((data) => {
    if (data.code === 200) {
      // 修改前端数据
      // 遍历 tableData 数组
      for (let i = 0; i < songDialog.tableData.length; i++) {
        const item = songDialog.tableData[i]
        if (item.songId === songModifyDialog.id) {
          // 找到目标 userId 对应的元素，进行修改
          songDialog.tableData[i] = { ...item, ...songModifyDialog.data }
          break // 找到后可以提前结束循环
        }
      }
      songModifyDialog.visable = false
      ElMessage({ message: '修改成功！', type: 'success' })
    } else {
      ElMessage({ message: '修改失败！', type: 'error' })
    }
  })
}

const albummodifyDialog = reactive({
  visable: false,
  id: -1,
  data: {} as AlbumInfo
})

const albumdeleteDialog = reactive({
  visable: false,
  id: -1
})

const albumEditClick = (id: number) => {
  console.log('click')
  albummodifyDialog.id = id
  const album = albumDialog.tableData.find((item) => item.albumId === id)
  if (album) {
    albummodifyDialog.data = { ...album }
  }
  albummodifyDialog.visable = true
  console.log(albummodifyDialog.id)
}

const albumDeleteClick = (id: number) => {
  console.log('click')
  albumdeleteDialog.id = id
  albumdeleteDialog.visable = true
}

const albumconfirmModify = () => {
  // 发送修改请求
  modifyAlbumInfo(albummodifyDialog.data).then((data) => {
    if (data.code === 200) {
      // 修改前端数据
      // 遍历 tableData 数组
      for (let i = 0; i < albumDialog.tableData.length; i++) {
        const item = albumDialog.tableData[i]
        if (item.albumId === albummodifyDialog.id) {
          // 找到目标 userId 对应的元素，进行修改
          albumDialog.tableData[i] = { ...item, ...albummodifyDialog.data }
          break // 找到后可以提前结束循环
        }
      }
      albummodifyDialog.visable = false
      ElMessage({ message: '修改成功！', type: 'success' })
    } else {
      ElMessage({ message: '修改失败！', type: 'error' })
    }
  })
}

const albumdeleteAlbumInfo = () => {
  // 发送删除请求
  deleteTheAlbumInfo(albumdeleteDialog.id)
  // 修改前端数据
  const index = albumDialog.tableData.findIndex((item) => item.albumId === albumdeleteDialog.id)
  console.log(index)
  if (index !== -1) {
    albumDialog.tableData.splice(index, 1)
  }
  console.log(albumDialog.tableData)
  albumdeleteDialog.id = -1
  albumdeleteDialog.visable = false
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
