import { createApp } from 'vue'
import App from './App.vue'
import './assets/reset.css'

const app = createApp(App)

import router from './router/index.js'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

app.use(ElementPlus)
app.use(router)
app.mount('#app')


