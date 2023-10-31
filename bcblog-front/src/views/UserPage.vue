<template>
    <div class="shell">
        <el-container style="overflow: hidden;">
            <el-header style="border-radius: 10px;">
                <div style="
                        font-size: 20px;
                        color: #fefefe;
                        text-shadow: 0 0 0.5em #00ffff, 0 0 0.2em #5c5c5c;
                        float: left;
                        padding-left: 10px;
                        " class="BCName">
                    <router-link to="/" class="toIndex">BlockChain Studio 208</router-link>
                </div>
                <div style="float: left; margin-left: 8%" id="box">
                    <router-link to="/blogShow">首页</router-link>    
                </div>
                <div style="float: left; margin-left: 8%" id="box">
                    <router-link to="/taskShow">待办</router-link>
                </div>
                <div style="float: left; margin-left: 8%" id="box">
                    <router-link to="/ideaShow">想法</router-link>
                </div>
                <div style="float: left; margin-left: 8%" id="box">
                    <router-link to="/matchShow">比赛</router-link>
                </div>
                <div style="float: left; margin-left: 8%" id="box">
                    <router-link to="/resourceShow">资料</router-link>
                </div>
                <div style="float: left; margin-left: 8%" id="box">
                    <router-link to="/resourceShow">这里"搜索框"</router-link>
                </div>
                <div style="float: left; margin-left: 8%" id="box">
                    <router-link to="/resourceShow">这里"创作中心"</router-link>
                </div>
                <div style="float: right; padding-right: 50px; padding-top: 20px;">
                    <el-dropdown>
                    <span class="el-dropdown-link">
                    个人中心
                    </span>
                    <template #dropdown>
                    <el-dropdown-menu>    
                        <el-dropdown-item><router-link to="/login">登录</router-link></el-dropdown-item>
                        <el-dropdown-item>修改密码</el-dropdown-item>
                        <el-dropdown-item>账号退出</el-dropdown-item>
                    </el-dropdown-menu>
                    </template>
                </el-dropdown>
                </div>
            </el-header>
            <el-container class="contextContainer" style="overflow: auto;">
                <el-aside width="200px" >
                    <div style="height: 80%; margin-top: 30%;" id="box">文章分类</div>
                </el-aside>
                <el-main style="border-radius: 20px;">
                    <router-view>
                    </router-view>
                </el-main>
                <el-aside width="200px">
                    <div style="height: 80%; margin-top: 30%;" id="box">近期比赛</div>
                </el-aside>
            </el-container>
        </el-container>
    </div>
</template>

<style scoped>
* {
    padding: 0;
    margin: 0;
}

.shell {
    position: absolute;
    width: 100%;
    height: 100%;
}

.el-header,
.el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
}

.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
}

.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    /* line-height: 160px; */
}

body>.el-container {
    margin-bottom: 40px;
}

.el-container {
    height: 100%;
}


.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}

.toIndex {
    text-decoration: none;
    color: #00ffff;
}

#box {
    border: 1px solid #92B0DD;
    background-color: #FFFFFF;
    border-radius: 10px;
    box-shadow: 0 0 0 10px white;
}
</style>
  
