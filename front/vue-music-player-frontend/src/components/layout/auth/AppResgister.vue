<template>
  <!--更新信息对话框-->
  <el-dialog
    v-model="showRegister"
    title="新用户注册"
    append-to-body
    @close="clearForm"
    :destroy-on-close="true"
    width="420px"
  >
    <!--提交表单-->
    <el-form :model="ruleForm" ref="ruleFormRef" :rules="rules" status-icon class="space-y-7">
      <el-form-item label="用户名" prop="username" class="item">
        <el-input
          v-model="ruleForm.username"
          autocomplete="off"
          label-position="right"
          class="input"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone" class="item">
        <el-input v-model="ruleForm.phone" autocomplete="off" class="input" />
      </el-form-item>
      <el-form-item label="昵称" prop="nickname" class="item">
        <el-input v-model="ruleForm.nickname" autocomplete="off" class="input" />
      </el-form-item>
      <el-form-item label="邮箱" prop="email" class="item">
        <el-input v-model="ruleForm.email" autocomplete="off" class="input" />
      </el-form-item>
      <el-form-item label="密码" prop="password" class="item">
        <el-input v-model="ruleForm.password" autocomplete="off" class="input" />
      </el-form-item>
      <el-form-item label="确认密码" prop="repassword" class="item">
        <el-input v-model="ruleForm.repassword" autocomplete="off" class="input" />
      </el-form-item>
    </el-form>
    <!--确认按钮-->
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="clearForm" class="button">取消</el-button>
        <el-button type="primary" @click="submitForm(ruleFormRef)" class="button"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import type { FormRules, FormInstance } from 'element-plus'
import { useAuthStore } from '@/stores/auth'
import { storeToRefs } from 'pinia'
import type { registerUser } from '@/models/user'

//表单相关
const ruleFormRef = ref<FormInstance>()
const { ruleForm, showRegister } = storeToRefs(useAuthStore())
const { register } = useAuthStore()

//验证二次密码
const validateRepassword = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('不得为空'))
  } else if (value !== ruleForm.value.password) {
    callback(new Error('两次密码需要一致！'))
  } else {
    callback()
  }
}

const rules = reactive<FormRules>({
  username: [{ required: true, message: '不得为空', trigger: 'blur' }],
  phone: [{ required: true, message: '不得为空', trigger: 'blur' }],
  nickname: [{ required: true, message: '不得为空', trigger: 'blur' }],
  email: [{ required: true, message: '不得为空', trigger: 'blur' }],
  password: [{ required: true, message: '不得为空', trigger: 'blur' }],
  repassword: [{ validator: validateRepassword, trigger: 'blur' }]
})

//提交表单
const submitForm = async (formEl: FormInstance | undefined) => {
  //如果表单不为undefined
  if (!formEl) return
  await formEl.validate((valid: any, fields: any) => {
    if (valid) {
      register(ruleForm.value)
    }
  })
}

//清空表单
const clearForm = () => {
  ruleForm.value = {} as registerUser
  showRegister.value = false
  ruleForm.value.repassword = ''
}
</script>

<style scoped lang="scss">
.item {
  @apply mx-4;
}

.input {
  @apply flex-none;
}
</style>
