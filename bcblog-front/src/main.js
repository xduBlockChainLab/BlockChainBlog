import { createApp } from 'vue'
import App from './App.vue'
import './assets/reset.css'

const app = createApp(App)

import router from './router/index.js'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8080/'

app.use(ElementPlus)
app.use(router)
app.mount('#app')


