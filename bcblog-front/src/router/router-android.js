import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'index',
        component: () => import("../views/AndroidIndex.vue")
    },

    {
        path: '/application',
        name: 'androidApplication',
        component: () => import("../views/AndroidApplication.vue")
    },

    
    {
        path: '/waiting',
        name: 'androidWaiting',
        component: () => import("../views/AndroidWaiting.vue")
    },
]

const router = createRouter({ //设置为history模式
    base:'bc208',
    history: createWebHistory(),
    routes: routes
})

export default router;

