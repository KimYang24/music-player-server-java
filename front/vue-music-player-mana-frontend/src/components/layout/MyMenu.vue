<template>
  <el-aside class="aside">
    <!--icon-->
    <div class="head">
      <div>
        <span style="color: black">~音乐管理端~</span>
        <img :src="cover" alt="logo" />
      </div>
    </div>
    <!--icon menu devide-->
    <div class="line"></div>
    <!--默认展开菜单栏-->
    <el-menu backgroud-color="#222832" :router="true" :default-openeds="['1', '2', '3', '4']">
      <!--一级menu-->
      <el-sub-menu index="1">
        <template #title>
          <span>首页</span>
        </template>
        <!--二级menu-->
        <el-menu-item-group>
          <el-menu-item index="/">
            <el-icon> <DataLine /> </el-icon>欢迎页
          </el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>

      <!--一级menu-->
      <el-sub-menu index="2">
        <template #title>
          <span>用户管理</span>
        </template>
        <!--二级menu-->
        <el-menu-item-group>
          <el-menu-item index="/userEdit">
            <el-icon> <DataLine /> </el-icon>用户管理
          </el-menu-item>
          <el-menu-item index="/adminEdit">
            <el-icon> <DataLine /> </el-icon>管理员管理
          </el-menu-item>
          <el-menu-item index="/userLikeEdit">
            <el-icon> <DataLine /> </el-icon>用户收藏管理
          </el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>

      <!--一级menu-->
      <el-sub-menu index="3">
        <template #title>
          <span>歌曲管理</span>
        </template>
        <!--二级menu-->
        <el-menu-item-group>
          <el-menu-item index="/songEdit">
            <el-icon> <DataLine /> </el-icon>歌曲配置
          </el-menu-item>
          <el-menu-item index="/albumEdit">
            <el-icon> <DataLine /> </el-icon>专辑配置
          </el-menu-item>
          <el-menu-item index="/singerEdit">
            <el-icon> <DataLine /> </el-icon>歌手配置
          </el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>

      <!--一级menu-->
      <el-sub-menu index="4">
        <template #title>
          <span>日志管理</span>
        </template>
        <!--二级menu-->
        <el-menu-item-group>
          <el-menu-item index="/loginHistory">
            <el-icon> <DataLine /> </el-icon>下载日志
          </el-menu-item>
          <el-menu-item index="/registerHistory">
            <el-icon> <DataLine /> </el-icon>注册日志
          </el-menu-item>
          <el-menu-item index="/playHistory">
            <el-icon> <DataLine /> </el-icon>播放日志
          </el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
    </el-menu>
  </el-aside>
</template>

<script setup lang="ts">
import cover from '@/assets/images/cover.jpeg'
</script>

<style scoped>
.line {
  color: beige;
}

.aside {
  width: 250px;
  background-color: white;
}

.head {
  display: flex;
  align-items: center;
}

.head > div {
  display: flex;
  align-items: center;
}

.head img {
  width: 140px;
  height: 140px;
}

.head span {
  color: antiquewhite;
  font-size: medium;
}
.line {
  border-top: 5px solid hsla(0, 0%, 100%, 0.05);
  border-bottom: 5px solid rgba(0, 0, 0, 0.2);
}
</style>
