import { ElMessage, ElMessageBox, ElNotification } from 'element-plus'

const AlertSuccess = (msg: string) => {
  ElMessage({
    message: msg,
    type: 'success'
  })
}

const AlertError = (msg: string) => {
  ElMessage.error(msg)
}

const AlertAxiosError = (msg: string) => {
  ElNotification({
    title: 'Api Error',
    message: msg,
    type: 'error',
    customClass: 'dark:bg-zinc-800',
    offset: 50,
    showClose: false
  })
}

const AlertRouterInfo = (msg: string) => {
  ElNotification({
    title: 'Router Info',
    message: msg,
    type: 'info',
    customClass: 'dark:bg-zinc-800',
    offset: 50,
    showClose: false
  })
}

const AlertRouterWarning = (msg: string) => {
  ElNotification({
    title: 'Router Warn',
    message: msg,
    type: 'warning',
    customClass: 'dark:bg-zinc-800',
    offset: 50,
    showClose: false
  })
}

const AlertMsgWarning = (msg: string) => {
  return ElMessageBox.confirm(msg, 'Warning', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning',
    center: false
  })
}

export {
  AlertError,
  AlertSuccess,
  AlertAxiosError,
  AlertRouterInfo,
  AlertRouterWarning,
  AlertMsgWarning
}
