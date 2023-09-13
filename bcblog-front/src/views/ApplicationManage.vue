<template>
    <div style="width: 40%; float: left; height: 100%" class="table">
        <div style="height: 55%">
            <div style="font-size: smaller; height: 40px; line-height: 40px">
                待面试人名单
            </div>
            <el-table :data="noInterview" height="340" style="width: 100%">
                <el-table-column prop="userName" label="名字" width="100"></el-table-column>
                <el-table-column prop="userGrade" label="年级" width="100"></el-table-column>
                <el-table-column prop="userInterest" label="方向" width="100"></el-table-column>
                <el-table-column fixed="right" label="操作" width="100">
                    <template #default="scope">
                        <el-button link type="primary" size="small" @click="loadDetail(scope.row.userName)">评价</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div style="height: 45%; margin-top: 0px">
            <div style="font-size: smaller; height: 40px; line-height: 40px">
                已面试人名单
            </div>
            <el-table :data="tableData" height="270" style="width: 100%">
                <el-table-column prop="name" label="Name" width="100" />
                <el-table-column prop="grade" label="grade" width="100" />
                <el-table-column prop="type" label="type" width="100" />
                <el-table-column fixed="right" label="Operations" width="120">
                    <template #default>
                        <el-button link type="primary" size="small" @click="handleClick">重新评价</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
    <div style="width: 60%; float: left; height: 100%" class="judge">
        <div class="applicationDetail" style="display, width: 100%;">
            <el-descriptions :data="applicationDetail" :props="DefaultProps" class="margin-top" title="面试人详情" :column="3"
                border style="font-size: smaller; height: 40px; line-height: 40px">
                <el-descriptions-item>
                    <template #label>
                        <div class="cell-item">
                            <el-icon :style="iconStyle">
                                <user />
                            </el-icon>
                            姓名
                        </div>
                    </template>
                    {{ applicationDetail.userName }}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template #label>
                        <div class="cell-item">
                            <el-icon :style="iconStyle">
                                <iphone />
                            </el-icon>
                            年级
                        </div>
                    </template>
                    {{ applicationDetail.userGrade }}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template #label>
                        <div class="cell-item">
                            <el-icon :style="iconStyle">
                                <location />
                            </el-icon>
                            邮箱
                        </div>
                    </template>
                    {{ applicationDetail.userEmail }}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template #label>
                        <div class="cell-item">
                            <el-icon :style="iconStyle">
                                <tickets />
                            </el-icon>
                            方向
                        </div>
                    </template>
                    {{ applicationDetail.userInterest }}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template #label>
                        <div class="cell-item">
                            <el-icon :style="iconStyle">
                                <office-building />
                            </el-icon>
                            简介
                        </div>
                    </template>
                    {{ applicationDetail.userDescription }}
                </el-descriptions-item>
            </el-descriptions>
        </div>
        <div class="adminJudge" style="display, width: 100%; margin-top: 200px">
            <el-form :model="form" label-width="120px">
                <el-form-item label="面试评价">
                    <el-input v-model="form.desc" type="textarea" />
                </el-form-item>
                <el-form-item label="兴趣方向" prop="type">
                <el-radio-group >
                    <el-radio :label="1">前端开发</el-radio>
                    <el-radio :label="2">后端开发</el-radio>
                    <el-radio :label="3">区块链开发</el-radio>
                    <el-radio :label="4">文案PPT设计</el-radio>
                </el-radio-group>
            </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">Create</el-button>
                    <el-button>Cancel</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>


<script setup>
import axios from "axios";
import { DefaultProps } from "element-plus";
import { ref } from "vue";

const currentPage4 = ref(4);
const pageSize4 = ref(10);
const small = ref(true);
const background = ref(false);

const form = ref({
    name: '',
    region: '',
    date1: '',
    date2: '',
    delivery: false,
    type: [],
    resource: '',
    desc: '',
})

const onSubmit = () => {
    console.log('submit!')
}

// let noInterview: application[];

let noInterview = ref();

const loadNoInterview = () => {
    axios
        .get("application/applications", {
            headers: {
                token: localStorage.getItem("token"),
            },
        })
        .then((response) => {
            if (response.data.code == 2002) {
                console.log(response.data.result);
                noInterview.value = response.data.result;
            } else {
                alert("获取面试人信息失败");
            }
        })
        .catch(function (error) {
            console.log(error);
        });
};
loadNoInterview();

let applicationDetail = ref([
    '',
    '',
    '',
    '',
    '',
]);

const loadDetail = (userName) => {
    alert(userName)
    axios.get("application/loadDetail", {
        params: {
            userName: userName
        },
        headers: {
            token: localStorage.getItem("token"),
        },
    })
        .then((response) => {
            if (response.data.code == 2002) {
                console.log(response.data.result);
                applicationDetail.value = response.data.result
            } else {
                alert("获取面试人信息失败");
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

const tableData = [];
</script>

<style scoped>
* {
    padding: 0;
    margin: 0;
}

.cell-item {
    display: flex;
    align-items: center;
}

</style>