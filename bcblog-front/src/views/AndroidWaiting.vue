<template>
<div class="shell">
    <div class="BCLogo" style="padding-top: 10%;">
            <img style="width: 60px;" src="../assets/index/BClogo.png">
            <div style="font-size: 28px;      
                /* color: #fefefe; */
                text-shadow: 0 0 0.5em #38a1ff, 0 0 0.2em #5c5c5c;
                " class="BCName">
                <router-link to="/" class="toIndex">BlockChain Studio 208</router-link>
            </div>
        </div>
        
</div>
</template>

<style scoped>
* {
    padding: 0;
    margin: 0;
}

.shell {
    background-image: url("../assets/index/and_waiting.png");
    background-size: cover;
    height: 100vh;
    height: calc(100vh -60px);
    /* overflow-x: hidden; */
    /* 设置当内容溢出块级元素的左右两侧时显示的内容, 或不显示, hidden为不显示溢出的*/
    perspective: 3px;
    /* 观察者与z=0屏幕的距离, 具有三维位置变换的元素产生透视效果 */
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
    font-family: "Times New Roman", Times, serif;
    letter-spacing: 2px;
}

.toIndex {
    text-decoration: none;
    color: black;
}
</style>