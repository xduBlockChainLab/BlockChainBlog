import {createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'index',
        component: () => import("../views/Index.vue")
    },
    {
        path: '/application',
        name: 'application',
        component: () => import("../views/Application.vue")
    },
    {
        path: '/login',
        name: 'login',
        component: () => import("../views/UserLogin.vue")
    },
    {
        path: '/register',
        name: 'register',
        component: () => import("../views/UserRegister.vue")
    },
    {
        path: '/adminlogin',
        name: 'adminlogin',
        component: () => import("../views/AdminLogin.vue")
    },
    {
        path: '/adminregister',
        name: 'adminregister',
        component: () => import("../views/AdminRegister.vue")
    },
    {
        path: '/adminpage',
        name: 'adminpage',
        component: () => import("../views/AdminPage.vue"),
        redirect: "/applicationmanage",
        children: [
            {
                path: '/applicationmanage',
                name: 'applicationmanage',
                component: () => import("../views/ApplicationManage.vue")
            },
            {
                path: '/membermanage',
                name: 'membermanage',
                component: () => import("../views/MemberManager.vue")
            },            {
                path: '/applicationmanage',
                name: 'applicationmanage',
                component: () => import("../views/ApplicationManage.vue")
            },
            {
                path: '/upcomemanage',
                name: 'upcomemanage',
                component: () => import("../views/UpcomeManage.vue")
            },
            {
                path: '/webmanage',
                name: 'webmanage',
                component: () => import("../views/WebManage.vue")
            },
        ]
    },


]

const router = createRouter({ //设置为history模式
    history: createWebHistory(),
    routes: routes
})

export default router;

