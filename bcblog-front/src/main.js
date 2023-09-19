import './assets/reset.css'
// 全局生效的css

import { createApp } from 'vue'
import App from './App.vue'
// 入口

const app = createApp(App)
// 所有的内容从App.vue开始读

import router from './router/index.js'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 全局生效

import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8088/'

app.use(ElementPlus)
app.use(router)
app.mount('#app')
// 绑定App.vue中的 id="app"


