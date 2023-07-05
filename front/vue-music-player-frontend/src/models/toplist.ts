export interface TopList {
  topListId: number
  name: string //排行榜名称
  listeners: number
  picUrl: string
}

export interface AllTopList {
  type: string //排行榜类别
  topList: TopList[]
}
