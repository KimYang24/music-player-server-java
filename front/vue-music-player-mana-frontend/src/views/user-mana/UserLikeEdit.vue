<template>
  <el-card class="main-card">
    <el-row class="el-row">
      <el-col :span="4" class="el-col">
        <el-card class="user-card" shadow="hover" :body-style="{ padding: '0px' }">
          <img class="image" :src="state.userData.picUrl" :fit="'cover'" />
          <div style="margin: 14px">
            <span style="font-size: 25px; font-weight: bold">{{ state.userData.nickname }}</span>
            <span style="font-size: 25px; font-weight: bold; margin-bottom: 10px">的收藏</span>
          </div>
        </el-card>
      </el-col>
      <!--歌曲-->
      <el-col :span="4" class="el-col-3">
        <el-card class="data-card" :body-style="{ padding: '0px' }">
          <template #header>
            <div class="card-header">
              <span style="font-size: 30px; font-weight: bold">收藏的歌</span>
            </div>
          </template>
          <el-table :data="state.songData" style="width: 300px" :show-header="false" :height="530">
            <el-table-column prop="picUrl" width="100">
              <template #default="scope">
                <el-avatar shape="square" :size="70" :fit="'cover'" :src="scope.row.picUrl" />
              </template>
            </el-table-column>
            <el-table-column prop="artist" width="165" />
            <el-table-column type="expand" width="35">
              <template #default="props">
                <p class="text">专辑信息:《 {{ props.row.album }} 》</p>
                <p class="text">歌手信息: {{ props.row.artist }}</p>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <!--歌手-->
      <el-col :span="4" class="el-col-3">
        <el-card class="data-card" :body-style="{ padding: '0px' }">
          <template #header>
            <span style="font-size: 30px; font-weight: bold">收藏的歌手</span>
          </template>
          <el-table
            :data="state.artistData"
            style="width: 300px"
            :show-header="false"
            :height="530"
          >
            <el-table-column prop="picUrl" width="100">
              <template #default="scope">
                <el-avatar shape="circle" :size="70" :fit="'cover'" :src="scope.row.picUrl" />
              </template>
            </el-table-column>
            <el-table-column prop="artist" width="165" />
            <el-table-column type="expand" width="35">
              <template #default="props">
                <p class="text">歌手简介: {{ props.row.profile }}</p>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <!--专辑-->
      <el-col :span="4" class="el-col-3">
        <el-card class="data-card" :body-style="{ padding: '0px' }">
          <template #header>
            <span style="font-size: 30px; font-weight: bold">收藏的专辑</span>
          </template>
          <el-table :data="state.albumData" style="width: 300px" :show-header="false" :height="530">
            <el-table-column prop="picUrl" width="100">
              <template #default="scope">
                <el-avatar shape="square" :size="70" :fit="'cover'" :src="scope.row.picUrl" />
              </template>
            </el-table-column>
            <el-table-column prop="album" width="165" />
            <el-table-column type="expand" width="35">
              <template #default="props">
                <p class="text">专辑描述: {{ props.row.profile }}</p>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </el-card>
  <!--分页按钮-->
  <el-pagination
    background
    layout="prev, pager, next"
    :total="state.totals"
    :page-size="state.pageSize"
    :current-page="state.currentPage"
    @current-change="handleCurrentChange"
  />
</template>

<script setup lang="ts">
import type { CustomerInfo } from '@/model/UserInfo'
import type { SongInfo } from '@/model/SongInfo'
import type { ArtistInfo } from '@/model/ArtistInfo'
import type { AlbumInfo } from '@/model/albumInfo'
import { getACustomerInfo } from '@/utils/api/user'
import { getUserLikeInfo } from '@/utils/api/like'

const state = reactive({
  userData: {
    userId: 0,
    age: '',
    email: '',
    gender: '',
    nickname: '',
    password: '',
    phone: '',
    picUrl: '',
    username: ''
  } as CustomerInfo, // 用户信息
  songData: [] as SongInfo[], // 歌曲信息
  artistData: [] as ArtistInfo[], // 歌手信息
  albumData: [] as AlbumInfo[], // 专辑信息
  currentPage: 1,
  pageSize: 1,
  totals: 400 // 一共有多少条目
})

//加载数据
onMounted(async() => {
  state.currentPage = 1
  console.log('state.currentPage');
  console.log(state.currentPage);
  await getACustomerInfo(state.currentPage).then((data) => {
    if (data.code == 200) {
      state.userData = data.data
      state.totals = data.totals
      console.log('state.totals')
      console.log(state.totals)
      console.log('data')
      console.log(data.data)
      console.log('state.userData')
      console.log(state.userData)
    }
  })
  console.log('state.userData.userId' + state.userData.userId)
  await getUserLikeInfo(state.userData.userId).then((data) => {
    if (data.code == 200) {
      state.songData = data.songs
      state.albumData = data.albums
      state.artistData = data.artists
      console.log('state.songData')
      console.log(state.songData)
    }
  })
})
const handleCurrentChange = async(val: number) => {
  state.currentPage = val
  console.log(`current page: ${val}`)
  await getACustomerInfo(val).then((data) => {
    if (data.code == 200) {
      state.userData = data.data
      state.totals = data.totals
      console.log(state.totals)
      console.log(data.data)
      console.log('data')
      console.log(data.data)
      console.log('state.userData')
      console.log(state.userData)
    }
  })
  console.log('state.userData.userId' + state.userData.userId)
  await getUserLikeInfo(state.userData.userId).then((data) => {
    if (data.code == 200) {
      state.songData = data.songs
      state.albumData = data.albums
      state.artistData = data.artists
      console.log('state.songData')
      console.log(state.songData)
    }
  })
}
</script>

<style scoped>
.main-card {
  display: flex;
  border-radius: 10px;
  margin-bottom: 20px;
  width: auto;
  height: 750px;
}
.el-row {
  margin-bottom: 40px;
}

.el-col {
  margin-left: 20px;
}
.el-col-3 {
  margin-left: 100px;
}
.user-card {
  margin-top: 10px;
  margin-bottom: 10px;
  display: flex;
  width: 300px;
  height: 375px;
}
.data-card {
  margin-top: 10px;
  margin-bottom: 10px;
  margin-left: 5px;
  width: 300px;
  height: 600px;
}
.image {
  width: 300px;
  height: 300px;
  display: block;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.infinite-list {
  height: 500px;
  padding: 0;
  margin: 0;
  list-style: none;
}
.data {
  font-size: 20px;
  margin-left: 20px;
}
.pic {
  border-radius: 50%;
  margin-left: 20px;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-right: 15px;
}
.pic-2 {
  margin-left: 20px;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-right: 15px;
}
.text {
  font-size: 15px;
  font-family: cursive;
  color: darkslategray;
  margin-left: 10px;
  margin-right: 10px;
}
</style>
