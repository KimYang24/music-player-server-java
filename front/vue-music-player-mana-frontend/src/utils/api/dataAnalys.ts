import axios from '@/utils/api/axios'
import type { DataAnalyse } from '@/model/DataAnalyse'

// 获取用户信息
const getDataAnalyse = async () => {
  const dataAnalyse: DataAnalyse = {
    numOfDownloadSong: [123, 64, 12, 56, 12, 66, 11],
    numOfLoginUsers: [76, 4, 26, 12, 67, 2, 58],
    numOfPlaySong: [16, 23, 36, 21, 94, 69, 35],
    numOfRegisterUsers: [28, 63, 92, 48, 92, 38, 53]
  }
  // try {
  //   const response = await axios.get('/adminUser/dataAnalyse', {})
  //   dataAnalyse = response.data.dataAnalyse
  // } catch (error) {
  //   console.log(error)
  // }
  return dataAnalyse
}

export { getDataAnalyse }
