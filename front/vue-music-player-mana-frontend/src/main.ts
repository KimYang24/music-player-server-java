import { createApp } from 'vue'
import { createPinia } from 'pinia'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import App from './App.vue'
import router from './router'

//TODO:记得删除
//mock js 配置

/*
import '../mock/index'
import '../mock/user.js'
import '../mock/dataAnalyse.js'
import '../mock/song.js'
*/

//add axios config
// import '@/utils/axios'

const app = createApp(App)
const pinia = createPinia()
//持久化存储插件
pinia.use(piniaPluginPersistedstate)

app.use(pinia)
app.use(router)

// add ElementPlusIconsVue
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.mount('#app')
