import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'androidIndex',
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
    base: 'bc208',
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
            if (to.meta.roles && to.meta.roles.includes(role)) {
                next();
            } else {
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

