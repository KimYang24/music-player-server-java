import { defineStore, storeToRefs } from 'pinia'
import { apiGetSong } from '@/utils/api/song'
import type { Song } from '@/models/song'
import { AlertError } from '@/utils/alert/AlertPop'
import _ from 'lodash'
import { useAuthStore } from './auth'
import { apiLogPlay } from '@/utils/api/log'
import { useRecentPlayStore } from './recentPlay'

// 将00:00.00转换为秒数
function timeStrToNum(str: string) {
  const minute = Number(str.slice(0, 2))
  const second = Number(str.slice(3, 5))
  const minSec = Number(str.slice(6, 8))
  return minute * 60 + second + minSec / 100
}

interface LineLrc {
  time: number
  content: string
}

// 将歌词字符串转换为对象，格式为{开始时间: 歌词, ...}
function lyricToObj(lyricStr: string) {
  const obj: LineLrc[] = []
  let perLyric
  let time
  let index = 0
  lyricStr.split('\n').forEach((item, idx) => {
    perLyric = item.slice(item.indexOf(']') + 1)
    if (perLyric) {
      time = timeStrToNum(item.slice(1, 9))
      obj[index] = {
        time: time,
        content: perLyric
      }
      index++
    }
  })
  //删掉未定义的
  return obj
}

export const usePlayerStore = defineStore('player', {
  state: () => ({
    audio: new Audio(),

    playList: [] as Song[], //播放列表,
    song: {} as Song, //正在播放歌曲

    showPlayList: false,
    loopType: 0, //0 单曲循环 1 列表循环 2列表随机播放
    volume: 60, //音量
    isPlaying: false, //是否正在播放
    ended: false, //是否播放结束
    muted: false, //是否静音
    currentTime: 0, //当前播放时间
    duration: 0, //总播放时长

    /* --------------------------------------------------------------- */
    showPlayWindow: false, //是否展示播放窗口
    // 原始歌词
    lyric: [] as LineLrc[],
    // 原始译词
    tlyric: [] as LineLrc[]
  }),
  getters: {
    //播放列表歌曲数量
    getPlayListCount: (state) => {
      return state.playList.length
    },
    //正在播放歌曲在播放列表中index
    getPlayListIndex: (state) => {
      return state.playList.findIndex((song) => song.songId === state.song.songId)
    },
    //获取下一首歌曲
    getNextSong(state): Song {
      if (this.getPlayListIndex === this.getPlayListCount - 1) {
        return _.first(state.playList)! //回到播放列表头部
      } else {
        //正常情况
        const nextIndex: number = this.getPlayListIndex + 1
        return state.playList[nextIndex]
      }
    },
    //获取前一首歌曲
    prevSong(state): Song {
      if (this.getPlayListIndex === 0) {
        return state.playList[this.playList.length - 1] //回到播放列表尾部
      } else {
        //正常情况
        const prevIndex: number = this.getPlayListIndex - 1
        return state.playList[prevIndex]
      }
    }
  },
  actions: {
    //播放列表里面添加音乐
    pushPlayList(replace: boolean, ...list: Song[]) {
      //替换播放列表
      if (replace) {
        this.playList = list
        return
      }
      //不替换播放列表
      list.forEach((song) => {
        //播放列表中没有该歌曲
        if (this.playList.filter((s) => s.songId == song.songId).length <= 0) {
          this.playList.push(song)
        }
      })
    },
    //清空播放列表
    clearPlayList() {
      this.song = {} as Song
      this.playList = [] as Song[]
      this.isPlaying = false
      this.ended = false
      this.muted = false
      this.currentTime = 0
      this.showPlayList = false
      this.audio.load() //Resets the audio
      setTimeout(() => {
        this.duration = 0
      }, 500)
    },
    //开始播放
    async play(id: number) {
      if (id == this.song.songId) return
      this.isPlaying = false
      //获取歌曲信息
      const res = await apiGetSong(id)
      if (res.code == 200) {
        this.song = res.song
      } else {
        AlertError('获取歌曲失败id=' + this.song.songId)
      }
      this.audio.src = this.song.url
      this.audio
        .play()
        .then((res) => {
          const authStore = useAuthStore()
          //播放成功
          this.isPlaying = true
          this.pushPlayList(false, this.song)
          //记录日志
          apiLogPlay(authStore.userId, this.song.songId)
          //添加到最近播放
          const recentPlay = useRecentPlayStore()
          recentPlay.addRecentSongs(this.song)
        })
        .catch((res) => {
          //播放失败
          console.log(res)
        })
    },
    //重新播放
    rePlay() {
      setTimeout(() => {
        this.currentTime = 0
        this.audio.play()
      }, 1000)
    },
    //下一曲
    nextPlay() {
      switch (this.loopType) {
        //单曲循环
        case 0:
          this.rePlay()
          break
        //列表
        case 1:
          this.play(this.getNextSong.songId)
          break
        //随机
        case 2:
          this.play(_.sample(this.playList)!.songId)
          break
      }
    },
    //上一曲
    prev() {
      this.play(this.prevSong.songId)
    },
    //播放、暂停
    togglePlay() {
      //没有歌曲在播放
      if (!this.song.songId) return
      //开始播放
      if (!this.isPlaying) {
        this.audio.play()
        this.isPlaying = true
      } //暂停播放
      else {
        this.audio.pause()
        this.isPlaying = false
      }
    },

    //切换循环类型
    toggleLoop() {
      if (this.loopType == 2) {
        this.loopType = 0
      } else {
        this.loopType++
      }
    },
    //静音切换
    toggleMuted() {
      this.muted = !this.muted
      this.audio.muted = this.muted
    },
    //音量设置
    setVolume(n: any) {
      //范围约束
      n = n > 100 ? 100 : n
      n = n < 0 ? 0 : n
      this.volume = n
      this.audio.volume = n / 100
    },
    //修改进度条
    onSliderChange(val: any) {
      //待做转换成时间格式
      if (this.song.songId) {
        this.currentTime = val
        this.audio.currentTime = val
      } else {
        console.log('song为空')
      }
    },
    //定时器更新信息
    interval() {
      //如果在播放
      if (this.isPlaying) {
        this.currentTime = parseInt(this.audio.currentTime.toString())
        this.duration = this.audio.duration
        this.ended = this.audio.ended
      }
    },
    /* ------------------------------------------------------------------- */

    changePlayerShow() {
      this.showPlayWindow = !this.showPlayWindow
    },
    updateLrc(lrc1: string, lrc2: string) {
      this.lyric = lyricToObj(lrc1)
      this.tlyric = lyricToObj(lrc2)
    }
  }
})

export const userPlayerInit = () => {
  let timer: any
  const { setVolume, interval, nextPlay } = usePlayerStore()
  const { ended } = storeToRefs(usePlayerStore())

  //监听播放结束
  watch(ended, (ended) => {
    if (!ended) return
    nextPlay()
  })

  //启动定时器
  onMounted(() => {
    setVolume(60)
    console.log('启动定时器')
    timer = setInterval(interval, 1000)
  })
  //清除定时器
  onUnmounted(() => {
    console.log('清除定时器')
    clearInterval(timer)
  })
}
