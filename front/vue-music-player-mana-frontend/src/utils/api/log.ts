import axios from '@/utils/api/axios'
import type { Log } from '@/model/Log'

// 获取信息
const getLogInfo = async (currentPage: number, pageSize: number, type: number) => {
  let customerInfoArray = ref<Log[]>([])
  try {
    const response = await axios.get('/admin/getLog', {
      params: {
        type: type,
        currentPage: currentPage,
        pageSize: pageSize
      }
    }) // 替换成你的 API 接口地址
    console.log(response)
    return response.data
  } catch (error) {
    console.error('Failed to fetch customer info:', error)
  }
}

export { getLogInfo }
