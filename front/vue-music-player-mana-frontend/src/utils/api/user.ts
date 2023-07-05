import axios from '@/utils/api/axios'
import type { UserInfo, CustomerInfo } from '@/model/UserInfo'

// 获取管理员登录信息
const getUserInfo = async (userId: number) => {
  const userInfo: UserInfo = { loginUserName: '' }
  try {
    const response = await axios.get('/adminUser/profile', { params: { adminId: userId } })
    userInfo.loginUserName = response.data.adminName
  } catch (error) {
    console.log(error)
  }
  return userInfo
}

// 获取特定页客户信息
const getCustomerInfo = async (currentPage: number, pageSize: number) => {
  // let customerInfoArray = ref<CustomerInfo[]>([]);
  try {
    const response = await axios.get('/User/pageAllInfo', {
      params: {
        currentPage: currentPage,
        pageSize: pageSize
      }
    }) // 替换成你的 API 接口地址
    console.log(response)
    return response.data
  } catch (error) {
    console.error('Failed to fetch customer info:', error)
  }
}

// 获取特定用户信息
const getTheCustomerInfo = async (name: string) => {
  try {
    const response = await axios.get('/User/theInfo', {
      params: {
        name: name
      }
    })
    console.log(response)
    return response.data
  } catch (error) {
    console.log(error)
  }
}

// 获取特定用户信息
const getACustomerInfo = async (index: number) => {
  try {
    const response = await axios.get('/User/aInfo', {
      params: {
        index: index
      }
    })
    console.log(response)
    return response.data
  } catch (error) {
    console.log(error)
  }
}

// 删除特定用户
const deleteTheCustomerInfo = async (userId: number) => {
  try {
    const response = await axios.get('/User/deleteInfo', {
      params: {
        user_id: userId
      }
    })
    console.log(response)
  } catch (error) {
    console.log(error)
  }
}

// 修改用户信息
const modifyTheCustomerInfo = async (data: CustomerInfo) => {
  try {
    const jsonData = JSON.stringify(data) // 将对象转换为 JSON 字符串
    const response = await axios.post('/User/modifyInfo', jsonData)
    // 处理响应
    console.log(response)
  } catch (error) {
    // 处理错误
    console.log(error)
  }
}

// 添加用户信息
const addTheCustomerInfo = async (data: CustomerInfo) => {
  try {
    const jsonData = JSON.stringify(data) // 将对象转换为 JSON 字符串
    const response = await axios.post('/User/addInfo', jsonData)
    // 处理响应
    console.log(response)
    return response.data
  } catch (error) {
    // 处理错误
    console.log(error)
  }
}

export {
  getUserInfo,
  getCustomerInfo,
  getTheCustomerInfo,
  deleteTheCustomerInfo,
  modifyTheCustomerInfo,
  addTheCustomerInfo,
  getACustomerInfo
}
