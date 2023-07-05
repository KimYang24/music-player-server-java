export interface Song {
  songId: number //歌曲ID
  albumId: number //专辑id
  artistId: number //歌手id

  name: string //歌曲名称
  album: string //歌曲专辑名
  artist: string //歌手名称

  pop?: number //歌曲播放次数
  mark?: number //评论数
  publishTime?: string //发行时间
  type?: string //歌曲类别
  duration: number //歌曲秒数时长

  url: string //歌曲url
  lyricUrl?: string //歌词url
  picUrl?: string //图片url，根据专辑id,可以减少数量
}
