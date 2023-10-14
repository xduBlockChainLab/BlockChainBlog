<template>
    <div class="shell">
        <div class="page0">
            <div class="BCLogo">
                <img style="width: 18vw" src="../assets/index/BClogo.png" />
                <div style="font-size: 10vw; color: black; text-shadow: 0 0 0.5em #38a1ff" class="BCName">
                    西电区块链协会
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.BCLogo {
    display: flex;
    /* 使用 Flexbox 布局 */
    align-items: center;
    /* 在交叉轴上居中对齐（垂直居中） */
    justify-content: center;
    /* 在主轴上居中对齐（水平居中） */
    padding-top: 3vh;
}

.page0 {
    height: 100vh;
    width: 100%;
    background-image: url("../assets/index/and_waiting.png");
    background-size: cover;
    background-position: center;
    /* 让背景图片水平和垂直居中 */
}



</style>
