import myAxios from './myAxios'

//添加播放日志
export async function apiLogPlay(userId: number, songId: number) {
  const data = await myAxios.post<{
    code: number
  }>('log/play', {
    userId: userId,
    songId: songId
  })
  return data
}

//添加下载日志
export async function apiLogDownload(userId: number, songId: number) {
  const data = await myAxios.post<{
    code: number
  }>('log/download', {
    userId: userId,
    songId: songId
  })
  return data
}
