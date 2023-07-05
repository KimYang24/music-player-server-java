export interface PlayList {
  playListId: number //播放列表ID
  playList: string //歌单名称
  profile: string //歌单描述
  tag: string //歌单类别 #流行#国语
  picUrl: string //封面
  userId: number //创建人id
  user: string //创建人名称
  createTime: string //创建时间
  mark: number //评论数
  size: number //歌曲数量
}

export interface PlayListTag {
  tagName: string
}
