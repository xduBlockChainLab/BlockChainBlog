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
        path: '/userpage',
        name: 'userpage',
        component: () => import("../views/UserPage.vue"),
        meta: {
            needLogin: true,
            roles: ["user"],
        },
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
            needLogin: true,
            roles: ["admin"],
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
    if (to.meta.needLogin) {
      // 获取token
        const token = localStorage.getItem("token");
        //TODO: 这里的认证逻辑需要处理.
        // alert(token)
      // 如果有token 则直接放行
        if (token) {
            // 获取用户信息，从store里面获取
            let role = localStorage.getItem("role");
            // alert(role)
            if(to.meta.roles && to.meta.roles.includes(role)){
                next();
            }else{
                alert("warning");
                next("/login");
            }
        } else {
        // 否则去登录页
        alert("warning");
        next("/login");
        }
    } else {
        // 不需要登录则直接放行
        next();
    }
});

export default router;

