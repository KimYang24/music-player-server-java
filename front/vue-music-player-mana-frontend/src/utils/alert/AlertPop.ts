const AlertSuccess = (msg: string) => {
  ElMessage({
    message: msg,
    type: 'success'
  })
}

const AlertError = (msg: string) => {
  ElMessage.error(msg)
}

export { AlertError, AlertSuccess }
