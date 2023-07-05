<template>
  <el-dialog v-model="showLogin" title="请登录" width="330px" append-to-body>
    <div>
      <el-input size="large" placeholder="手机号码" :prefix-icon="Phone" v-model="phone" />
      <el-input
        size="large"
        class="mt-5"
        placeholder="登录密码"
        :prefix-icon="Lock"
        v-model="password"
      />

      <el-checkbox v-model="saveLoginStatus" class="mt-10 ml-1">记住我</el-checkbox>
      <el-button
        @click="loginSubmit"
        type="primary"
        class="w-full mt-0 py-5"
        style="border-radius: 5px"
      >
        登录
      </el-button>
      <el-button link class="mt-3" @click="register">还没有账号？这里注册！</el-button>
    </div>
  </el-dialog>
</template>

<script setup lang="ts">
import { Lock, Phone } from '@icon-park/vue-next'
import { useAuthStore } from '@/stores/auth'
import { storeToRefs } from 'pinia'

const saveLoginStatus = ref(true)
const phone = ref('')
const password = ref('')
const { login } = useAuthStore()
const { showLogin, showRegister } = storeToRefs(useAuthStore())

const loginSubmit = () => {
  login(phone.value, password.value)
}
const register = () => {
  showLogin.value = false
  showRegister.value = true
}
</script>
