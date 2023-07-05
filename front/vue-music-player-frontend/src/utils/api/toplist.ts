//获取排行榜数据
export async function apiTopListDetail() {
  const allTopList = {
    code: 200,
    topLists: [
      {
        type: '官方榜',
        topList: [
          {
            topListId: 1,
            listeners: 123.3,
            name: '飙升榜',
            picUrl: 'sss'
          },
          {
            topListId: 2,
            listeners: 123.3,
            name: '热歌榜',
            picUrl: 'sss'
          },
          {
            topListId: 3,
            listeners: 123.3,
            name: '新歌榜',
            picUrl: 'sss'
          },
          {
            topListId: 4,
            listeners: 123.3,
            name: '流行指数榜',
            picUrl: 'sss'
          },
          {
            topListId: 5,
            listeners: 123.3,
            name: '腾讯音乐人原创榜',
            picUrl: 'sss'
          },
          {
            topListId: 6,
            listeners: 123.3,
            name: 'MV榜',
            picUrl: 'sss'
          }
        ]
      },
      {
        type: '地区榜',
        topList: [
          {
            topListId: 1,
            listeners: 123.3,
            name: '飙升榜',
            picUrl: 'asdasdasd'
          },
          {
            topListId: 2,
            listeners: 123.3,
            name: '热歌榜',
            picUrl: 'asdasdasd'
          },
          {
            topListId: 3,
            listeners: 123.3,
            name: '新歌榜',
            picUrl: 'asdasdasd'
          },
          {
            topListId: 4,
            listeners: 123.3,
            name: '流行指数榜',
            picUrl: 'asdasdasd'
          },
          {
            topListId: 5,
            listeners: 123.3,
            name: '腾讯音乐人原创榜',
            picUrl: 'asdasdasd'
          },
          {
            topListId: 6,
            listeners: 123.3,
            name: 'MV榜',
            picUrl: 'asdasdasd'
          }
        ]
      },
      {
        type: '全球榜',
        topList: [
          {
            topListId: 1,
            listeners: 123.3,
            name: 'billboard',
            picUrl: 'asdasdasd'
          },
          {
            topListId: 2,
            listeners: 123.3,
            name: '热歌榜',
            picUrl: 'asdasdasd'
          },
          {
            topListId: 3,
            listeners: 123.3,
            name: '新歌榜',
            picUrl: 'asdasdasd'
          },
          {
            topListId: 4,
            listeners: 123.3,
            name: '流行指数榜',
            picUrl: 'asdasdasd'
          },
          {
            topListId: 5,
            listeners: 123.3,
            name: '腾讯音乐人原创榜',
            picUrl: 'asdasdasd'
          },
          {
            topListId: 6,
            listeners: 123.3,
            name: 'MV榜',
            picUrl: 'asdasdasd'
          }
        ]
      }
    ]
  }
  return allTopList
}
