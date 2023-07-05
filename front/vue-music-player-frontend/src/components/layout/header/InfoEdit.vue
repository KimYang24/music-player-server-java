<template>
  <!--修改对话框-->
  <el-dialog v-model="showModify" title="信息修改" width="27%">
    <el-form :model="modifyDialog.data" label-width="100px">
      <el-form-item label="">
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
        <el-button @click="showModify = false">Cancel</el-button>
        <el-button type="primary" @click="confirmModify"> Confirm </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { useAuthStore } from '@/stores/auth'
import { storeToRefs } from 'pinia'
import type { UploadProps } from 'element-plus'
import { AlertError, AlertSuccess } from '@/utils/alert/AlertPop'
import myAxios from '@/utils/api/myAxios'
import type { UserProfile } from '@/models/user'
import axios from 'axios'

const { profile, showModify, userId } = storeToRefs(useAuthStore())
const { checkLogin } = useAuthStore()

interface CustomerInfo extends UserProfile {
  userId: number
}
const modifyDialog = ref({
  data: profile.value
})

const mhandleAvatarSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
  modifyDialog.value.data.picUrl = URL.createObjectURL(uploadFile.raw!)
  console.log(modifyDialog.value.data.picUrl)
}

const mbeforeAvatarUpload: UploadProps['beforeUpload'] = async (file) => {
  if (file.type !== 'image/png') {
    AlertError('头像必须是PNG文件！')
    return false
  } else if (file.size / 1024 / 1024 > 2) {
    AlertError('头像不能超过 2MB!')
    return false
  }
  console.log('file：', file)
  try {
    let formData = new FormData()
    formData.append('file', file)
    axios
      .post('user/profile/edit', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then((response) => {
        console.log(response)
        modifyDialog.value.data.picUrl = URL.createObjectURL(file)
        console.log(modifyDialog.value.data.picUrl)
        if (response.data.code == 200) {
          AlertSuccess('成功上传照片')
          modifyDialog.value.data.picUrl = response.data.picUrl
          console.log('response.data.picUrl' + response.data.picUrl)
        } else {
          AlertError('上传照片失败')
        }
      })
  } catch (error) {
    console.log(error)
  }
}

const confirmModify = () => {
  // 发送修改请求
  const c = {
    age: modifyDialog.value.data.age,
    email: modifyDialog.value.data.email,
    gender: modifyDialog.value.data.gender,
    nickname: modifyDialog.value.data.gender,
    password: modifyDialog.value.data.password,
    phone: modifyDialog.value.data.phone,
    picUrl: modifyDialog.value.data.picUrl,
    userId: userId.value,
    username: modifyDialog.value.data.username
  } as CustomerInfo
  modifyTheCustomerInfo(c)
  // 修改前端数据
  showModify.value = false
}

// 修改用户信息
const modifyTheCustomerInfo = async (data: CustomerInfo) => {
  const res = await myAxios.post<{
    code: number
  }>('/User/modifyInfo', data)
  if (res.code == 200) {
    AlertSuccess('修改信息成功！')
    checkLogin()
  } else {
    AlertError('上修改信息失败')
  }
}
</script>
