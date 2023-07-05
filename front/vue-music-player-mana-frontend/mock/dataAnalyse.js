import Mock from 'mockjs'

const mockData = Mock.mock({
  'array1|7': [Mock.Random.natural(3000, 5000)], // 第一个数组
  'array2|7': [Mock.Random.natural(1000, 5000)], // 第二个数组
  'array3|7': [Mock.Random.natural(4000, 5000)], // 第三个数组
  'array4|7': [Mock.Random.natural(1500, 5000)] // 第四个数组
})

Mock.mock('/adminUser/dataAnalyse', 'get', {
  code: 200,
  dataAnalyse: {
    numOfDownloadSong: mockData['array1'],
    numOfLoginUsers: mockData['array2'],
    numOfPlaySong: mockData['array3'],
    numOfRegisterUsers: mockData['array4']
  }
})
