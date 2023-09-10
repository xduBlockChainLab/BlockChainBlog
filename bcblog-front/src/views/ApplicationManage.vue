<template>
    <div style="width: 40%; float: left; height: 100%; " class="table">
        <div style="height: 55%;">
            <div style="font-size: smaller; height: 40px; line-height: 40px;">待面试人名单</div>
            <el-table :data="tableData" height="340" style="width: 100%">
                <el-table-column prop="name" label="Name" width="100" />
                <el-table-column prop="grade" label="grade" width="100" />
                <el-table-column prop="type" label="type" width="100" />
                <el-table-column fixed="right" label="Operations" width="120">
                    <template #default>
                        <el-button link type="primary" size="small" @click="handleClick">评价</el-button>
                        <el-button link type="primary" size="small">Edit</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div style="height: 45%; margin-top: 0px;">
            <div style="font-size: smaller; height: 40px; line-height: 40px;">已面试人名单</div>
            <el-table :data="tableData" height="270" style="width: 100%">
                <el-table-column prop="name" label="Name" width="100" />
                <el-table-column prop="grade" label="grade" width="100" />
                <el-table-column prop="type" label="type" width="100" />
                <el-table-column fixed="right" label="Operations" width="120">
                    <template #default>
                        <el-button link type="primary" size="small" @click="handleClick">重新评价</el-button>
                        <el-button link type="primary" size="small">Edit</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
    <div style="width: 60%; float: left; height: 100%;" class="judge">
        面试评价
    </div>
</template>


<script lang="ts" setup>
import { ref } from 'vue'

const currentPage4 = ref(4)
const pageSize4 = ref(10)
const small = ref(true)
const background = ref(false)


const handleClick = () => {
    console.log('click')
}

const tableData = [
    {
        date: '2016-05-03',
        name: 'Tom',
        state: 'California',
        city: 'Los Angeles',
        zip: 'CA 90036',
        tag: 'Home',
    },
    {
        date: '2016-05-02',
        name: 'Tom',
        state: 'California',
        city: 'Los Angeles',
        zip: 'CA 90036',
        tag: 'Office',
    },
    {
        date: '2016-05-04',
        name: 'Tom',
        state: 'California',
        city: 'Los Angeles',
        zip: 'CA 90036',
        tag: 'Home',
    },


]
</script>

<style scoped>
* {
    padding: 0;
    margin: 0;
}

.demo-pagination-block+.demo-pagination-block {
    margin-top: 10px;
}

.demo-pagination-block .demonstration {
    margin-bottom: 16px;
}
</style>