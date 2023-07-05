import axios, { AxiosError, type AxiosResponse } from 'axios'

import { useAuthStore } from '@/stores/auth'
import { storeToRefs } from 'pinia'
import { AlertAxiosError } from '../alert/AlertPop'

// 设置baseurl
axios.defaults.baseURL = 'http://127.0.0.1:4000'
// axios.defaults.baseURL = 'http://localhost:4000'
// 请求头，headers 信息
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
// 默认 post 请求，使用 application/json 形式
axios.defaults.headers.post['Content-Type'] = 'application/json'

//发送interceptor,设置token
axios.interceptors.request.use((config) => {
  const { token, isLogin } = storeToRefs(useAuthStore())
  if (isLogin) {
    config.headers.Authorization = token.value
  }
  //todo token设置
  return config
})

// 响应interceptor
axios.interceptors.response.use(
  (res) => {
    // 2xx 范围内的状态码都会触发该函数。
    //打印每次成功接受的数据
    console.log('axios success   ' + res.config.url)
    console.log(res)
    return res
  },
  function (error: AxiosResponse) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    console.log(error)
    AlertAxiosError('访问' + error.config.url! + '发送参数' + error.config.params)
    return Promise.reject(error)
  }
)

// 配置myAxios接口，封装请求库
interface MyAxios {
  get<T>(url: string, params?: unknown): Promise<T>
  post<T>(url: string, params?: unknown): Promise<T>
  put<T>(url: string, params: unknown): Promise<T>
  delete<T>(url: string, params: unknown): Promise<T>
}

//实现接口
const myAxios: MyAxios = {
  get(url, params) {
    return new Promise((resolve, reject) => {
      axios
        .get(url, { params })
        .then((res) => {
          resolve(res.data)
        })
        .catch((err) => {
          reject(err.data)
        })
    })
  },

  post(url, params) {
    return new Promise((resolve, reject) => {
      axios
        .post(url, JSON.stringify(params))
        .then((res) => {
          resolve(res.data)
        })
        .catch((err) => {
          reject(err.data)
        })
    })
  },

  put(url, params) {
    return new Promise((resolve, reject) => {
      axios
        .put(url, JSON.stringify(params))
        .then((res) => {
          resolve(res.data)
        })
        .catch((err) => {
          reject(err.data)
        })
    })
  },

  delete(url, params) {
    return new Promise((resolve, reject) => {
      axios
        .delete(url, { params })
        .then((res) => {
          resolve(res.data)
        })
        .catch((err) => {
          reject(err.data)
        })
    })
  }
}

export default myAxios
