<template>
    <div class="shell">
        <div class="image img1">
            <div style="transform: translateY(-20vh)" class="BCLogo">
                <img style="width: 300px;" src="../assets/index/BClogo.png">
                <div style="font-size: 60px;      
                color: #fefefe;
                text-shadow: 0 0 0.5em #00ffff, 0 0 0.2em #5c5c5c;
                " class="BCName">BlockChain Studio 208</div>
            </div>
        </div>
        <div style="transform: translateY(-40vh);" class="someButton">
            <el-button style="position: absolute;
                    background-color:#00ffff;
                    color:white;
                    width: 300px;
                    height: 47px;
                    border:0;
                    font-size: 16px;			
                    border-radius: 30px;
                    font-size: large;
                    margin-right: 500px;
                    " round @click="Application">
                    加入我们吧
            </el-button>
            <el-dropdown >
                <el-button type="primary" size="large"
                    style="position: absolute;
                        background-color:#00ffff;
                        color:white;
                        width: 300px;
                        height: 47px;
                        border:0;		
                        border-radius: 30px;
                        font-size: large;
                        margin-left: 500px;
                    ">
                    探索新世界<el-icon></el-icon>
                </el-button>
                <template #dropdown>
                    <el-dropdown-menu>
                    <el-dropdown-item style="
                        background-color:#00ffff;
                        color:white;
                        width: 250px;
                        font-size: medium;
                        " round @click="Login" >成员登录</el-dropdown-item>
                    <el-dropdown-item style="
                        background-color:#00ffff;
                        color:white;
                        width: 250px;
                        font-size: medium;
                        " round @click="Visitor">游客访问</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
        <div class="text">
            <h1>Set goals in your mind</h1>
        </div>
        <div class="image img2"></div>
        <div class="heading">
            <h1>When you're down</h1>
        </div>
        <div class="text">
            <h1>Try to wake up the beast in your heart</h1>
        </div>

        <div class="image img3"></div>
        <div class="heading">
            <h1>When people leave you</h1>
        </div>
        <div class="text">
            <h1>It's time to start your season</h1>
        </div>

        <div class="image img4"></div>
        <div class="heading">
            <h1>Come on, stranger</h1>
        </div>
    </div>
    <div style="position:fixed; left:0px;bottom:0px; font-size: 14px">
        互联网ICP备案：<el-link href="https://beian.miit.gov.cn" target="_blank">陕ICP备2021013237号</el-link>
    </div>
</template>

<script se>
export default {
    name: 'index',
    methods: {
        Application() {
            this.$router.push('/application')
        },
        Login() {
            this.$router.push('/login')
        },
        Visitor() {
            alert("游客访问")
        }
    }
}
</script>

<style scoped>
.shell {
    height: 100vh;
    overflow-x: hidden;
    /* 设置当内容溢出块级元素的左右两侧时显示的内容, 或不显示, hidden为不显示溢出的*/
    perspective: 3px;
    /* 观察者与z=0屏幕的距离, 具有三维位置变换的元素产生透视效果 */
    overflow: auto;
    overflow-x: hidden;
}
.shell::-webkit-scrollbar{
    width: 0 !important;
}

.shell div {
    position: relative;
    /* 元素在文档中的定位方式, relative元素定位, 不改变页面布局下调整位置*/
    display: flex;
    /* 元素是否被视为块或内联元素, 用于布局, flex弹性盒模型 */
    justify-content: center;
    /* 浏览器如何沿着弹性容器的株洲分配元素之间和周围的空间 */
    align-items: center;
    /* 当页居中? */
    font-style: 30px;
    font-family: 'Times New Roman', Times, serif;
    letter-spacing: 2px;
}

.image {
    transform: translateZ(-1px) scale(1.6);
    /* 视觉效果, 沿着z轴重定位元素, 观察者视角拉近或拉远图片*/
    /* scale放缩图片 */
    background-size: cover;
    /* 背景图片尺寸大小 */
    height: 100vh;
    z-index: -1;
}

.image.img1 {
    background-image: url('../assets/index/newyou.png');
    height: 100%;
    width: 100%;
}

.image.img2 {
    background-image: url('../assets/index/stat.jpg');
    height: 100%;
    width: 100%;
}

.image.img3 {
    background-image: url('../assets/index/street.jpg');
    height: 100%;
    width: 100%;
}

.image.img4 {
    background-image: url('../assets/index/fire2.jpg');
    height: 100%;
    width: 100%;
}

.text {
    height: 50vh;
    background-color: white;
}

.text h1 {
    color: black;
}

.heading {
    z-index: -1;
    transform: translateY(-50vh) translateZ(1px);
    color: white;
    font-size: 30px;
}

</style>