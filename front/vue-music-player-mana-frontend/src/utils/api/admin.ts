import axios from '@/utils/api/axios'
import type { UserInfo, CustomerInfo, AdminInfo } from '@/model/UserInfo'

// 获取特定页管理员信息
const getAdminInfo = async (currentPage: number, pageSize: number) => {
  // let customerInfoArray = ref<AdminInfo[]>([]);
  try {
    const response = await axios.get('/adminUser/pageAllInfo', {
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

// 获取特定管理员信息
const getTheAdminInfo = async (name: string) => {
  try {
    const response = await axios.get('/adminUser/theInfo', {
      params: {
        adminname: name
      }
    })
    console.log(response)
    return response.data
  } catch (error) {
    console.log(error)
  }
}

// 删除特定用户
const deleteTheAdminInfo = async (adminId: number) => {
  try {
    const response = await axios.get('/adminUser/deleteInfo', {
      params: {
        adminId: adminId
      }
    })
    console.log(response)
  } catch (error) {
    console.log(error)
  }
}

// 修改用户信息
const modifyTheAdminInfo = async (data: AdminInfo) => {
  try {
    const jsonData = JSON.stringify(data) // 将对象转换为 JSON 字符串
    const response = await axios.post('/adminUser/modifyInfo', jsonData)
    // 处理响应
    console.log(response)
  } catch (error) {
    // 处理错误
    console.log(error)
  }
}

// 添加用户信息
const addTheAdminInfo = async (data: AdminInfo) => {
  try {
    const jsonData = JSON.stringify(data) // 将对象转换为 JSON 字符串
    const response = await axios.post('/adminUser/addInfo', jsonData)
    // 处理响应
    console.log(response)
    return response.data
  } catch (error) {
    // 处理错误
    console.log(error)
  }
}
export { getAdminInfo, getTheAdminInfo, deleteTheAdminInfo, modifyTheAdminInfo, addTheAdminInfo }
