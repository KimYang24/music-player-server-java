import { createApp } from 'vue'
import { createPinia } from 'pinia'

import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import App from './App.vue'
import router from './router'
//全局路由钩子
import './router/interceptor'

//引入tailwind
import '@/assets/style/theme.scss'
import '@/assets/style/base.scss'
import '@/assets/style/element-ui.scss'
//element message样式
import 'element-plus/dist/index.css'
//animate css样式
import 'animate.css'

const app = createApp(App)

// add ElementPlusIconsVue
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.use(createPinia().use(piniaPluginPersistedstate))
app.use(router)

app.mount('#app')
