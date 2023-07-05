import axios from '@/utils/api/axios'
import type { SongInfo } from '@/model/SongInfo'

// 获取全部歌曲信息
const getSongInfo = async (currentPage: number, pageSize: number) => {
  try {
    const response = await axios.get('/admin/getPageSong', {
      params: {
        currentPage: currentPage,
        pageSize: pageSize
      }
    })
    console.log(response.data)
    return response.data
  } catch (error) {
    console.log(error)
  }
}

// 获取歌手全部歌曲信息
const getArtistAllSong = async (artistId: number) => {
  try {
    const response = await axios.get('/admin/getSongByArtist', {
      params: {
        artistId: artistId
      }
    })
    console.log(response.data)
    return response.data
  } catch (error) {
    console.log(error)
  }
}

// 获得特定歌曲
const getTheSongInfo = async (songName: string) => {
  try {
    const response = await axios.get('/admin/getSongByName', {
      params: {
        songName: songName
      }
    })
    console.log(response.data)
    return response.data
  } catch (error) {
    console.log(error)
  }
}

// 添加歌曲
const addSongInfo = async (newSong: SongInfo) => {
  try {
    const jsonData = JSON.stringify(newSong) // 将对象转换为 JSON 字符串
    const response = await axios.post('/admin/addSong', jsonData)
    // 处理响应
    console.log(response)
    return response.data
  } catch (error) {
    // 处理错误
    console.log(error)
  }
}

// 修改歌曲
const modifySongInfo = async (modiSong: SongInfo) => {
  try {
    const send = {
      data:modiSong
    }
    const jsonData = JSON.stringify(send) // 将对象转换为 JSON 字符串
    const response = await axios.post('/admin/modifySong', jsonData)
    // 处理响应
    console.log(response)
    return response.data
  } catch (error) {
    // 处理错误
    console.log(error)
  }
}

// 删除歌曲
const deleteTheSongInfo = async (songId: number) => {
  try {
    const response = await axios.get('/admin/deleteSong', {
      params: {
        songId: songId
      }
    })
    console.log(response.data)
    return response.data
  } catch (error) {
    console.log(error)
  }
}

// 获取专辑所有歌
const getSongByAlbum = async (albumId: number) => {
  try {
    const response = await axios.get('/admin/getSongByAlbum', {
      params: {
        albumId: albumId
      }
    })
    console.log(response.data)
    return response.data
  } catch (error) {
    console.log(error)
  }
}

export {
  getSongInfo,
  addSongInfo,
  modifySongInfo,
  getTheSongInfo,
  deleteTheSongInfo,
  getArtistAllSong,
  getSongByAlbum
}
