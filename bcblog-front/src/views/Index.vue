<template>
    <div class="shell">
        <div class="page0">
            <div class="BCLogo">
                <img style="width: 15vw" src="../assets/index/BClogo.png" />
                <div style="font-size: 5vw; color: black; text-shadow: 0 0 0.5em #38a1ff" class="BCName">
                    西电区块链协会
                </div>
            </div>
            <div class="page0_button">
                <el-button style="margin-top: 5vh;" round @click="Application">
                    加入我们
                </el-button>
                <el-button round @click="Home">
                    进入主页
                </el-button>
            </div>
        </div>
        <div class="page1">
            <div class="heading">
                <p class="heading-text">一. 协会简介</p>
            </div>
            <div class="content">
                <p class="content_text0">
                    &sect; 西安电子科技大学区块链协会社团是在学校党委领导，团委指导下，
                    由网络与信息安全学院具体指导，西安电子科技大学区块链技术应用与评测研究中心提供专业指导，
                    教育部区块链技术应用与评测工程研究中心、陕西省区块链与安全计算实验室、
                    微众银行、蚂蚁链等单位提供合作支持的学术科技类社团。
                    社团期望建立一个西电开源社区，主要的活动方向为区块链、科技金融等领域，
                    聚焦涉及金融、计算机、密码学与信息安全等各个方面。
                </p>
                <p class="content_text1">
                    &sect; 协会的区块链爱好者们，共同研发创新创业项目，在人才培养、平台测试、应用研发和底层技术研究
                    等方面进行深入探索，争取在视野、思维、技术上将区块链及其应用达到行业前沿水准。
                </p>
            </div>
        </div>
        <div class="page2">
            <div class="heading">
                <p class="heading-text">二. 区块链开发者计划</p>
            </div>
            <div class="content">
                <p class="content_text0">
                    &sect; 协会旨在对有志于从事区块链技术开发的同学进行初步掌握区块链全栈技术的培养。
                    我们可为参与此活动表现优异成员提供合作企业的实习资格，提高社团核心成员的荣誉感，提供合理助力，为社团成员提供经验积累。
                </p>
                <p class="content_text1">
                    &sect; 掌握区块链开发流程
                    <br>
                    &sect;&sect; 基于FISCO-BCOS的web3sdk应用开发
                    <br>
                    &sect;&sect; 基于hyperledgerfabric的sdk应用开发
                    <br>
                    &sect;&sect; solidity&go智能合约开发
                    <br>
                    &sect; 完善web开发技术
                    <br>
                    &sect;&sect; 前后端流行技术栈学习与开发
                    <br>
                    &sect;&sect; 流行数据库学习与开发
                    <br>
                    &sect; 软件领域的先进技术学习及创新
                    <br>
                    &sect;&sect; 学习分布式, 微服务相关技术及系统的开发和设计
                    <br>
                    &sect;&sect; 探索新的技术方向，开发新的源码包
                </p>
            </div>
        </div>
        <div class="page3">
            <div class="heading">
                <p class="heading-text">三. 荣誉回顾</p>
            </div>
            <div class="content">
                <p class="content_text0">
                    &sect; 第八届中国国际“互联网+”大学生创新创业大赛国际铜奖
                    <br>
                    &sect; 2023年全国大学生节能减排社会实践与科技竞赛国三
                    <br>
                    &sect; 国家级大学生创新创业训练计划优秀结题
                    <br>
                    &sect; 第13届全国大学生电子商务三创赛陕西省一等奖
                    <br>
                    &sect; 第九届中国国际“互联网+”大学生创新创业大赛陕西省银
                    <br>
                    &sect; 2023年陕西省工业工程改善创意竞赛陕西省三等奖
                    <br>
                    &sect; 第21届微软Imagine Cup复赛赛区优秀奖
                    <br>
                    &sect; 2022年西安市大学生创新创业成长计划优秀创新创业项目
                    <br>
                    &sect; 首届蚂蚁链Web3.0与产业创新应用孵化营
                    <br>
                    &sect; 微众银行2022 FinTechaton金融科技高校大赛
                    <br>
                    &sect; 第18届“花旗杯”金融创新应用大赛
                </p>
            </div>
        </div>
        <div class="page4">
            <div class="heading">
                <p class="heading-text">加入我们!</p>
            </div>
            <div class="content">
                <p class="content_text0">
                    &sect; &nbsp;技术部:
                    <br>
                    &sect;&sect; 开发组: 前端, 后端开发; 区块链开发; 信息安全与加密算法.
                    <br>
                    &sect;&sect; 应用组: 产品经历, 行业研究.
                    <br>
                    &sect; &nbsp;宣传部:
                    <br>
                    &sect;&sect; 平面设计, 新媒体运营, 推文写作, 活动策划, 对外交流.
                    <br>
                    &sect; &nbsp;活动部:
                    <br>
                    &sect;&sect; 活动策划: 社会活动方案的指定.
                    <br>
                    &sect;&sect; 对外交流: 与其他高校, 企业的区块链社团交流与联系.
                </p>
            </div>
            <div class="page4_button">
                <el-button style="margin-top: 5vh;" round @click="Application">
                    加入我们
                </el-button>
            </div>
        </div>
    </div>
</template>

<script>
import { ElMessage } from "element-plus";
export default {
    name: "index",
    methods: {
        Application() {
            ElMessage({
                message: "正在跳转...",
                type: "success",
            });
            setTimeout(() => {
                this.$router.push("/application");
            }, 1000);
        },
        Home() {
            ElMessage({
                message: "正在跳转...",
                type: "success",
            });
            setTimeout(() => {
                this.$router.push("/login");
            }, 1000);
        },
    },
};
</script>

<style scoped>
.BCLogo {
    display: flex;
    /* 使用 Flexbox 布局 */
    align-items: center;
    /* 在交叉轴上居中对齐（垂直居中） */
    justify-content: center;
    /* 在主轴上居中对齐（水平居中） */
    padding-top: 8vh;
}

.page0 {
    height: 100vh;
    width: 100%;
    background-image: url("../assets/index/img1.png");
    background-size: cover;
    background-position: center;
    /* 让背景图片水平和垂直居中 */
}

.page0_button {
    /* 使用 Flexbox 布局 */
    /* flex-direction: column; */
    /* align-items: center; */
    /* 在垂直方向上居中对齐 */
    display: flex; /* 使用 flex 布局 */
    justify-content: space-between; /* 按钮之间均匀分布 */
    padding: 30vh 30vw; /* 左右两边各添加 20px 的间距 */
}

.page0_button .el-button {
    background-color: #38a1ff;
    color: white;
    width: 10vw;
    height: 3vw;
    border-radius: 30px;
    font-size: 2vw;
    margin: 3vh !important;
}



.page4_button {
    display: flex; /* 使用 flex 布局 */
    justify-content: center; /* 水平居中 */
    align-items: center; /* 垂直居中 */
}

.page4_button .el-button{
    background-color: #38a1ff;
    color: white;
    width: 20vh;
    height: 6vh;
    border-radius: 30px;
    font-size: 4vh;
}
.page1 {
    height: 100vh;
    width: 100%;
    background-image: url("../assets/index/img2.png");
    background-size: cover;
    background-position: center;
    /* 让背景图片水平和垂直居中 */
}

.page2 {
    height: 100vh;
    width: 100%;
    background-image: url("../assets/index/img3.png");
    background-size: cover;
    background-position: center;
    /* 让背景图片水平和垂直居中 */
}

.page3 {
    height: 100vh;
    width: 100%;
    background-image: url("../assets/index/img4.png");
    background-size: cover;
    background-position: center;
    /* 让背景图片水平和垂直居中 */
}

.page4 {
    height: 100vh;
    width: 100%;
    background-image: url("../assets/index/applicationSubmit.png");
    background-size: cover;
    background-position: center;
    /* 让背景图片水平和垂直居中 */
}


.heading {
    display: flex;
    /* 使用 Flexbox 布局 */
    height: 10vw;
    background-color: #38a1ff;
    flex-direction: column;
    /* 垂直方向排列子元素 */
    justify-content: center;
    /* 在垂直方向上居中对齐 */
}

.heading-text {
    text-align: center;
    /*文本水平居中对齐*/
    font-size: 8vh;
    font-family: 'Times New Roman', Times, serif;
}

.content {
    margin: 4vw;
    text-align: center;
}

.content_text0 {
    margin-top: 10vh;
    border: 2px solid #38a1ff;
    font-size: 3vh;
    border-radius: 20px;
    padding: 2vw;
}

.content_text1 {
    margin-top: 3vh;
    border: 2px solid #38a1ff;
    font-size: 3vh;
    border-radius: 20px;
    padding: 2vw;
}

</style>
