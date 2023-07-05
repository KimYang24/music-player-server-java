import Mock from 'mockjs'

const Random = Mock.Random

const generateRandomSongInfo = () => {
  const songInfo = {
    name: Random.cword(3, 7),
    artist: Random.cword(2, 5),
    album: Random.cword(2, 5),
    time: Random.date(),
    style: Random.cword(2, 5)
  }
  return songInfo
}

const generateMultipleSongInfo = (count) => {
  const songInfoList = []
  for (let i = 0; i < count; i++) {
    const songInfo = generateRandomSongInfo()
    songInfoList.push(songInfo)
  }
  return songInfoList
}

//模拟获取歌曲信息
Mock.mock(RegExp('/adminUser/songInfo' + '.*'), 'get', () => {
  let data = generateMultipleSongInfo(10)
  return {
    code: 200,
    totals: 250,
    songInfo: data
  }
})

//模拟添加歌曲
Mock.mock('/admin/addSong', 'post', {
  code: 200,
  songID: 666
})

//模拟添加歌曲
Mock.mock('/admin/modiSong', 'post', {
  code: 200,
  songID: 777
})
