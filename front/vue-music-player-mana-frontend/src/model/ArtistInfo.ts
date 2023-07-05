export interface ArtistInfo {
  artistId: number
  artist: string
  picUrl: string
  songSize: number
  albumSize: number
  profile: string //简短介绍
  location: string
}

export interface ArtistDesc {
  profile: string //简短介绍
  basicInfo: string //基本信息
  timeLine: string //从艺历程
  award: string //获奖荣誉
}
