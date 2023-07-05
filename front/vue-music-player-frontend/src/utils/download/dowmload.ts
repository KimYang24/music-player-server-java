import fileDownload from 'js-file-download'
import { AlertError } from '../alert/AlertPop'
import { apiLogDownload } from '@/utils/api/log'
import { storeToRefs } from 'pinia'
import { useAuthStore } from '@/stores/auth'
import type { Song } from '@/models/song'
import { useDwldStore } from '@/stores/download'

const { userId } = storeToRefs(useAuthStore())
const { addDwlnSongs } = useDwldStore()

// 调用下载函数
// const fileUrl = '文件的URL';
// const fileName = '文件名.mp3';
export const downloadSong = (song: Song): void => {
  fetch(song.url)
    .then((response) => response.blob())
    .then((blob) => {
      //获取名称
      const fType = song.url.substring(song.url.lastIndexOf('.'))
      const fileName = song.name + fType
      fileDownload(blob, fileName)
      //记录下载日志
      apiLogDownload(userId.value, song.songId)
      //往下载store里push
      addDwlnSongs(song)
    })
    .catch((err) => {
      AlertError('抱歉，下载出错了')
      console.error('文件下载失败', err)
    })
}
