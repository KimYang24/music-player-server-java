import Mock from 'mockjs'

Mock.mock('/adminUser/login', 'post', {
  code: 200,
  token: 'aaaaaaaaaaaaaaaaaaaaaaaa' //token随便乱写
})

Mock.mock('/adminUser/profile', 'get', {
  code: 200,
  userInfo: {
    nickName: 'lpt',
    loginUserName: 'admin'
  }
})


Mock.mock('/user/info', 'post', {
  code: 200,
  
  data  [
    {
      date: '2016-05-03',
      name: 'Tom',
      nickname: 'Tom',
      gender: '男',
      age: '18',
      email: 'xxx@xxx.xxx',
      password: 'Home',
    },
    {
      date: '2016-05-03',
      name: 'Tm',
      nickname: 'Tm',
      gender: '男',
      age: '18',
      email: 'yyy@yyy.xxx',
      password: 'yyy',
    },
    {
      date: '2016-05-03',
      name: 'Tom',
      nickname: 'Tom',
      gender: '男',
      age: '18',
      email: 'xxx@xxx.xxx',
      password: 'Home',
    }
  ]
)

