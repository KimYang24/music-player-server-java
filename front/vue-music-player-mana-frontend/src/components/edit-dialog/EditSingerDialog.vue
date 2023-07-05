<template>
  <!--更新信息对话框-->
  <el-dialog
    :model-value="songManaStore.$state.isVisible"
    :title="title"
    @close="songManaStore.$state.isVisible = false"
  >
    <!--提交表单-->
    <el-form :model="ruleForm" ref="ruleFormRef" :rules="rules" status-icon>
      <el-form-item label="歌曲名" :label-width="state.formLabelWidth" prop="name">
        <el-input v-model="ruleForm.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="演唱者" :label-width="state.formLabelWidth" prop="artist">
        <el-input v-model="ruleForm.artist" autocomplete="off" />
      </el-form-item>
      <el-form-item label="专辑" :label-width="state.formLabelWidth" prop="album">
        <el-input v-model="ruleForm.album" autocomplete="off" />
      </el-form-item>
      <el-form-item label="发行时间" :label-width="state.formLabelWidth" prop="time">
        <el-date-picker v-model="ruleForm.time" type="date" placeholder="选择日期" />
      </el-form-item>
      <el-form-item label="歌曲分类" :label-width="state.formLabelWidth" prop="style">
        <el-select v-model="ruleForm.style" placeholder="选取歌曲类别">
          <el-option v-for="(style, index) in musicStyles" :key="index" :value="musicStyles[index]">
            {{ style }}
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <!--确认按钮-->
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="songManaStore.$state.isVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm(ruleFormRef)"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import type { SongInfo } from '@/model/SongInfo'
import { musicStyles } from '@/model/musicStyles'
import { useSongManaStore } from '@/stores/songMana'
import { AlertError, AlertSuccess } from '@/utils/alert/AlertPop'
import { addSong, modiSong } from '@/utils/api/song'
import type { FormRules, FormInstance } from 'element-plus'

const songManaStore = useSongManaStore()
const title = computed(() => {
  if (state.app == 'add') return '添加歌曲'
  else return '修改歌曲'
})
const state = reactive({
  app: '',
  formLabelWidth: '120px'
})
//表单相关
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<SongInfo>({
  album: '',
  artist: '',
  name: '',
  style: '',
  time: ''
})
const rules = reactive<FormRules>({
  name: [{ required: true, message: '不得为空', trigger: 'blur' }],
  artist: [{ required: true, message: '不得为空', trigger: 'blur' }],
  album: [{ required: true, message: '不得为空', trigger: 'blur' }],
  time: [{ required: true, message: '不得为空', trigger: 'change' }],
  style: [{ required: true, message: '不得为空', trigger: 'change' }]
})

//提交表单
const submitForm = async (formEl: FormInstance | undefined) => {
  //如果表单不为undefined
  if (!formEl) return
  await formEl.validate((valid: any, fields: any) => {
    if (valid) {
      addSong(ruleForm).then((data) => {
        if (data.code == 200) {
          AlertSuccess('添加歌曲成功,新增歌曲编号：' + data.songID)
        } else {
          AlertError('添加歌曲失败')
        }
        songManaStore.$state.isVisible = false
      })
    }
  })
}
</script>
<style scoped></style>
