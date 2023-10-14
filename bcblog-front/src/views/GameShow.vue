<template>
    <div class="match">
        <el-card class="box-card" shadow="hover" v-for="item in 10" :key="item">
            <template #header>
                <div class="card-header">
                    <span>Card name</span>
                    <el-button class="button" text>查看详情</el-button>
                </div>
            </template>
            <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
        </el-card>
    </div>
</template>

<style scoped>
.match {
    width: 85%;
    margin: 0 auto;
    /* 居中状态 */
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}

.box-card {
    /* width: 480px; */
    margin-top: 15px;
    margin-bottom: 15px;
}
</style>