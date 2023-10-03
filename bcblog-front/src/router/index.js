import {createRouter, createWebHistory } from 'vue-router'
// import { isAuthenticated } from './auth';

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
        path: '/userpage',
        name: 'userpage',
        component: () => import("../views/UserPage.vue"),
        redirect: "/blogshow",
        children: [
            {
                path: '/blogshow',
                name: 'blogshow',
                component: () => import("../views/BlogShow.vue")
            },
            {
                path: '/ideashow',
                name: 'ideashow',
                component: () => import("../views/ideaShow.vue")
            },            {
                path: '/taskshow',
                name: 'taskshow',
                component: () => import("../views/taskShow.vue")
            },
            {
                path: '/matchshow',
                name: 'matchshow',
                component: () => import("../views/matchShow.vue")
            },
            {
                path: '/resourceshow',
                name: 'resourceshow',
                component: () => import("../views/resourceShow.vue")
            },
        ],      
    },

    {
        path: '/waiting',
        name: 'waiting',
        component: () => import("../views/Waiting.vue")
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
        meta: {
            requiresAuth: true
        },
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
        ],        
    },
]

const router = createRouter({ //设置为history模式
    base:'bc208',
    history: createWebHistory(),
    routes: routes
})

router.beforeEach(async (to, from, next) => {
    // 如果需要登录
    if (to.meta.requiresAuth) {
        const token = localStorage.getItem("token");
        if (token) {
            next();
        } else {
            alert("尚未登录, 请先登录");
            next({
                path: '/adminlogin',
                query: {redirect: to.fullPath}
            })
        }   
    } else {
        next();
    }
});

export default router;

