/* router根据路由名称和id跳转 */
import router from '@/router' // 导入Vue Router实例
import { NavigationFailureType, isNavigationFailure } from 'vue-router'

//带参数跳转
export const routerPushByNameId = (name: string, id: number) => {
  router.push({ name: name, query: { id: id } }).then((failure) => {
    if (isNavigationFailure(failure, NavigationFailureType.aborted)) {
      //failure.to.path
      //failure.from.path
    }
  })
}

//跳转搜索
export const routerPushByNameKeyWord = (name: string, keyWord: string) => {
  router.push({ name: name, query: { keyWord: keyWord } }).then((failure) => {
    if (isNavigationFailure(failure, NavigationFailureType.aborted)) {
      //failure.to.path
      //failure.from.path
    }
  })
}

export const routerPush = (name: string) => {
  router.push({ name: name }).then((failure) => {
    if (isNavigationFailure(failure, NavigationFailureType.aborted)) {
      //failure.to.path
      //failure.from.path
    }
  })
}
