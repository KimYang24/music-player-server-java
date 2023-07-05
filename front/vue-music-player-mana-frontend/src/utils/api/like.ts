import axios from '@/utils/api/axios'
import type { SongInfo } from '@/model/SongInfo'
import type { ArtistInfo } from '@/model/ArtistInfo'
import type { AlbumInfo } from '@/model/AlbumInfo'

// 获取用户所有喜欢
const getUserLikeInfo = async (userId: number) => {
  try {
    const response = await axios.get('/user/like', {
      params: {
        userId: userId
      }
    }) // 替换成你的 API 接口地址
    console.log(response)
    return response.data
  } catch (error) {
    console.error('Failed to fetch customer info:', error)
  }
}

export { getUserLikeInfo }
