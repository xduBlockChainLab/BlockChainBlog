<template>
    <div class="shell">
        <div class="image img1">
        </div>
        <div style="transform: translateY(-75vh)" class="BCLogo">
            <img style="width: 50px;" src="../assets/index/BClogo.png">
            <div style="font-size: 30px;      
                color: black;
                text-shadow: 0 0 0.5em #38a1ff, 0 0 0.2em #5c5c5c;
                " class="BCName">BlockChain Studio 208</div>
        </div>
        <div style="transform: translateY(17vh);
                position: absolute;
                top: 50%;
                left: 30%;
                " class="someButton">
            <el-button style="position: absolute;
                    background-color:#38a1ff;
                    color:white;
                    width: 130px;
                    height: 30px;
                    border-radius: 30px;
                    font-size: small;
                    " round @click="Application">
                加入我们吧
            </el-button>
            <el-button style="position: absolute;
                    background-color:#38a1ff;
                    color:white;
                    width: 130px;
                    height: 30px;
                    border-radius: 30px;
                    font-size: small;
                    margin-left: 320px;
                    " round @click="Home">
                    探索新世界
            </el-button>
        </div>
        <div class="text">
            <h1>一. 协会简介</h1>
        </div>
        <div class="image img2"></div>
        <div class="heading">
            &sect; &nbsp;西安电子科技大学区块链协会社团是在学校党委领导，团委指导下，
            由网络与信息安全学院具体指导，西安电子科技大学区块链技术应用与评测研究中心提供专业指导，
            教育部区块链技术应用与评测工程研究中心、陕西省区块链与安全计算实验室、
            微众银行、蚂蚁链等单位提供合作支持的学术科技类社团。
            社团期望建立一个西电开源社区，主要的活动方向为区块链、科技金融等领域，
            聚焦涉及金融、计算机、密码学与信息安全等各个方面。
            <br>            <br>
            &sect; &nbsp;协会的区块链爱好者们，共同研发创新创业项目，在人才培养、平台测试、应用研发和底层技术研究
            等方面进行深入探索，争取在视野、思维、技术上将区块链及其应用达到行业前沿水准。
        </div>
        <div class="text">
            <h1>二. 区块链开发者计划</h1>
        </div>
        <div class="image img3"></div>
        <div class="heading">
            &sect; &nbsp;协会旨在对有志于从事区块链技术开发的同学进行初步掌握区块链全栈技术的培养。
            <br>
            &sect; &nbsp;我们可为参与此活动表现优异成员提供合作企业的实习资格，提高社团核心成员的荣誉感，提供合理助力，为社团成员提供经验积累。
            <br>            <br>
            &sect; &nbsp;掌握区块链开发流程
            <br>
            &nbsp; &nbsp; &sect; 基于FISCO-BCOS的web3sdk的区块链应用开发
            <br>
            &nbsp; &nbsp; &sect; 基于hyperledgerfabric的sdk的区块链应用开发
            <br>
            &nbsp; &nbsp; &sect; 智能合约开发：solidity&go
            <br>
            &sect; &nbsp;完善web开发技术
            <br>
            &nbsp; &nbsp; &sect; 后端编程语言Go、Java、Nodejs等和相应的web框架
            <br>
            &nbsp; &nbsp; &sect; 并发编程、网络编程、Linux下的开发与调试
            <br>
            &nbsp; &nbsp; &sect; 数据库MySQL、LevelDB等
            <br>
            &nbsp; &nbsp; &sect; 前端html+javascript+css+ajax混合编程 & Ionic Vue等前端框架
            <br>
            &sect; &nbsp;软件领域的先进技术学习及创新
            <br>
            &nbsp; &nbsp; &sect; 学习微服务、消息队列、缓存、docker等技术
            <br>
            &nbsp; &nbsp; &sect; 高并发系统的开发和设计
            <br>
            &nbsp; &nbsp; &sect; 探索新的技术方向，开发新的源码包
        </div>
        <div class="text">
            <h1>三. 荣誉回顾</h1>
        </div>
        <div class="image img4"></div>
        <div class="heading">
            &sect; &nbsp;第八届中国国际“互联网+”大学生创新创业大赛国际铜奖
            <br>
            &sect; &nbsp;2023年全国大学生节能减排社会实践与科技竞赛国三
            <br>
            &sect; &nbsp;国家级大学生创新创业训练计划优秀结题
            <br>
            &sect; &nbsp;第13届全国大学生电子商务三创赛陕西省一等奖
            <br>
            &sect; &nbsp;第九届中国国际“互联网+”大学生创新创业大赛陕西省银
            <br>
            &sect; &nbsp;2023年陕西省工业工程改善创意竞赛陕西省三等奖
            <br>
            &sect; &nbsp;第21届微软Imagine Cup复赛赛区优秀奖
            <br>
            &sect; &nbsp;2022年西安市大学生创新创业成长计划优秀创新创业项目
            <br>
            &sect; &nbsp;首届蚂蚁链Web3.0与产业创新应用孵化营
            <br>
            &sect; &nbsp;微众银行2022 FinTechaton金融科技高校大赛
            <br>
            &sect; &nbsp;第18届“花旗杯”金融创新应用大赛
        </div>
        <div class="text">
            <h1>加入我们吧!</h1>
        </div>
        <div class="image img5"></div>
        <div class="heading">
            &sect; &nbsp;技术部:
            <br>
            &nbsp; &nbsp; &sect; 开发组: 前端, 后端开发; 区块链开发; 信息安全与加密算法.
            <br>
            &nbsp; &nbsp; &sect; 应用组: 产品经历, 行业研究.
            <br>
            &sect; &nbsp;宣传部:
            <br>
            &nbsp; &nbsp; &sect; 平面设计, 新媒体运营, 推文写作, 活动策划, 对外交流.
            <br>
            &sect; &nbsp;活动部:
            <br>
            &nbsp; &nbsp; &sect; 活动策划: 社会活动方案的指定.
            <br>
            &nbsp; &nbsp; &sect; 对外交流: 与其他高校, 企业的区块链社团交流与联系.
        </div>
        <div style="transform: translateY(-60vh);" class="someButton">
            <el-button style="position: absolute;
                    background-color:#38a1ff;
                    color:white;
                    width: 300px;
                    height: 47px;
                    border:0;
                    font-size: 16px;			
                    border-radius: 30px;
                    font-size: large;
                    " round @click="Application">
                加入我们吧
            </el-button>
        </div>
    </div>
</template>

<script>
import { ElMessage } from 'element-plus'
export default {
    name: 'index',
    methods: {
        Application() {
            ElMessage({
                message: '正在跳转...',
                type: 'success',
            })
            setTimeout(() => {
                this.$router.push('/application')
            }, 1000)
        },
        // Login() {
        //     ElMessage({
        //         message: '正在跳转...',
        //         type: 'success',
        //     })
        //     setTimeout(() => {
        //         this.$router.push('/adminlogin')
        //     }, 1000)
        // },
        Home() {
            ElMessage({
                message: '正在跳转...',
                type: 'success',
            })
            setTimeout(() => {
                this.$router.push('/waiting')
            }, 1000)
        }
    }
}
</script>

<style scoped>
* .shell {
    height: 100vh;
    overflow-x: hidden;
    perspective: 5px;
    overflow: auto;
    overflow-x: hidden;
}

.shell::-webkit-scrollbar {
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
    /* font-style: 30px; */
    font-family: 'Times New Roman', Times, serif;
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
    background-image: url('../assets/index/and_img1.png');
    height: 100%;
    width: 100%;
}

.image.img2 {
    background-image: url('../assets/index/and_img2.png');
    height: 100%;
    width: 100%;
}

.image.img3 {
    background-image: url('../assets/index/and_img3.png');
    height: 100%;
    width: 100%;
}

.image.img4 {
    background-image: url('../assets/index/and_img4.png');
    height: 100%;
    width: 100%;
}


.image.img5 {
    background-image: url('../assets/index/and_img5.png');
    height: 100%;
    width: 100%;
}
.text {
    height: 35vh;
    font-size: medium;
    background-color: #38a1ff;
}

.heading {
    /* z-index: -1; */
    transform: translateY(-65vh);
    /* transform: translateZ(0px); */
    color: black;
    font-size: smaller;
    padding: 15px;
    font-family: 'Times New Roman', Times, serif;
}
</style>