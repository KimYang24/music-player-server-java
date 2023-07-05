import axios from '@/utils/api/axios'
import type { ArtistInfo } from '@/model/ArtistInfo'

// 获取特定页歌手信息
const getArtistInfo = async (currentPage: number, pageSize: number) => {
  try {
    const response = await axios.get('/admin/getPageArtist', {
      params: {
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

// 获取特定歌手信息
const getTheArtistInfo = async (name: string) => {
  try {
    const response = await axios.get('/admin/getArtist', {
      params: {
        name: name
      }
    })
    console.log(response)
    return response.data
  } catch (error) {
    console.log(error)
  }
}

// 删除特定歌手
const deleteTheArtistInfo = async (artistId: number) => {
  try {
    const response = await axios.get('/admin/deleteArtist', {
      params: {
        artistId: artistId
      }
    })
    console.log(response)
    return response.data
  } catch (error) {
    console.log(error)
  }
}

// 添加特定歌手
const addArtistInfo = async (data: ArtistInfo) => {
  try {
    const send = {
      data: data
    }
    const jsonData = JSON.stringify(send) // 将对象转换为 JSON 字符串
    const response = await axios.post('/admin/addArtist', jsonData)
    // 处理响应
    console.log(response)
    return response.data
  } catch (error) {
    // 处理错误
    console.log(error)
  }
}

// 修改特定歌手
const modifyArtistInfo = async (modiArtist: ArtistInfo) => {
  try {
    console.log('modiArtist.albumSize',modiArtist.albumSize);
    const send={
      data:modiArtist
    }
    const jsonData = JSON.stringify(send) // 将对象转换为 JSON 字符串
    const response = await axios.post('/admin/modifyArtist', jsonData)
    // 处理响应
    console.log(response)
    return response.data
  } catch (error) {
    // 处理错误
    console.log(error)
  }
}

// 根据专辑获得歌手
const getArtistByAlbum = async (albumId: number) => {
  try {
    const response = await axios.get('/admin/getArtistByAlbum', {
      params: {
        albumId: albumId
      }
    })
    console.log(response)
    return response.data
  } catch (error) {
    console.log(error)
  }
}

export {
  getArtistInfo,
  getTheArtistInfo,
  deleteTheArtistInfo,
  addArtistInfo,
  modifyArtistInfo,
  getArtistByAlbum
}
