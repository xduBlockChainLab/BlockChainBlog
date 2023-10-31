<template>
    <div class="box0">
        <div class="box1">
            <div class="box1_1">
                <div style="font-size: small; height: 5vh">
                    待面试人名单
                </div>
                <el-table :data="noInterview" style="max-height: 36vh; width: 100% ; overflow: auto; " class="table1"
                    :fixed-header=true>
                    <el-table-column prop="userName" label="名字"></el-table-column>
                    <el-table-column prop="userGrade" label="年级"></el-table-column>
                    <el-table-column prop="userInterest" label="方向"></el-table-column>
                    <el-table-column fixed="right" label="操作">
                        <template #default="scope">
                            <el-button link type="primary" size="small"
                                @click="loadDetail(scope.row.userName)">评价</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="box1_2">
                <el-scrollbar>
                    <div style="font-size: small; height: 5vh">
                        已面试人名单
                    </div>
                    <el-table :data="Interviewed" style="max-height: 31.4vh; width: 100% ; overflow: auto; " class="table1"
                        :fixed-header=true>
                        <el-table-column prop="userName" label="名字"></el-table-column>
                        <el-table-column prop="userGrade" label="年级"></el-table-column>
                        <el-table-column prop="userInterest" label="方向"></el-table-column>
                        <el-table-column fixed="right" label="操作">
                            <template #default="scope">
                                <el-button link type="primary" size="small"
                                    @click="loadDetail(scope.row.userName)">评价</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-scrollbar>
            </div>
        </div>
        <div class="box2">
            <div class="applicationDetail">
                <el-descriptions :data="applicationDetail" :props="DefaultProps" title="面试人详情" :column="2" border>
                    <el-descriptions-item width="100px">
                        <template #label>
                            <div class="cell-item">
                                名字
                            </div>
                        </template>
                        {{ applicationDetail.userName }}
                    </el-descriptions-item>
                    <el-descriptions-item width="100px">
                        <template #label>
                            <div class="cell-item">
                                年级
                            </div>
                        </template>
                        {{ applicationDetail.userGrade }}
                    </el-descriptions-item>
                    <el-descriptions-item width="100px">
                        <template #label>
                            <div class="cell-item">
                                邮箱
                            </div>
                        </template>
                        {{ applicationDetail.userEmail }}
                    </el-descriptions-item>
                    <el-descriptions-item width="100px">
                        <template #label>
                            <div class="cell-item">
                                方向
                            </div>
                        </template>
                        {{ applicationDetail.userInterest }}
                    </el-descriptions-item>
                    <el-descriptions-item width="100px">
                        <template #label>
                            <div class="cell-item">
                                简介
                            </div>
                        </template>
                        {{ applicationDetail.userDescription }}
                    </el-descriptions-item>
                </el-descriptions>
                <el-button type="primary" size="large" style="width: 5vw; margin-top: 5vh; margin-left: 18vw;"
                    @click="drawerChange = true, pdfUrl = getPdfUrl(applicationDetail.userName)">
                    查看简历
                </el-button>
            </div>

            <el-drawer class="drawer" v-model="drawerChange" direction=ltr size=55vw>
                <iframe style="width: 100%; height: 99%;" :src="pdfUrl"></iframe>
            </el-drawer>

            <div class="adminJudge">
                <el-form :data="applicationDetail" label-width="120px">
                    <el-form-item label="面试评价" prop="evaluate" style="width: 90%;">
                        <el-input v-model="judgeForm.evaluate" type="textarea" :autosize="{ minRows: 3, maxRows: 8 }" />
                    </el-form-item>
                    <el-form-item label="面试结果" prop="type">
                        <el-radio-group v-model="judgeForm.type">
                            <el-radio :label="1">通过</el-radio>
                            <el-radio :label="0">不通过</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitJudge(applicationDetail.userName, judgeForm)">提交</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>


<script setup>
import axios from "axios";
import { DefaultProps } from "element-plus";
import { ref } from "vue";

let noInterview = ref();

const loadNoInterview = () => {
    axios.get("application/applications", {
        headers: {
            token: localStorage.getItem("token"),
        },
    })
        .then((response) => {
            if (response.data.success == true) {
                noInterview.value = response.data.data;
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
    axios.get("application/loadDetail", {
        params: {
            userName: userName
        },
        headers: {
            token: localStorage.getItem("token"),
        },
    })
        .then((response) => {
            if (response.data.success == true) {
                applicationDetail.value = response.data.data
            } else {
                alert("获取面试人信息失败");
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}


let judgeForm = ref({
    name: '',
    evaluate: '',
    type: 0,
})

const submitJudge = (userName, judgeForm) => {
    alert("正在提交面试结果...")
    axios.post("admin/judge", {
        "userName": userName,
        "comments": judgeForm.evaluate,
        "score": judgeForm.type,
    }, {
        headers: {
            token: localStorage.getItem("token"),
        }
    })
        .then((response) => {
            if (response.data.success == true) {
                alert("面试评价成功.")
                remakeForm();
                console.log(response.data.data)
            } else {
                alert("面试评价失败.")
            }
            remakeForm();
            loadNoInterview();
            loadInterviewed();
        })
        .catch(function (error) {
            console.log(error);
        });
}

const remakeForm = () => {
    applicationDetail = '',
        judgeForm = ''
}

let Interviewed = ref();

const loadInterviewed = () => {
    axios.get("application/applicationsJudged", {
        headers: {
            token: localStorage.getItem("token"),
        },
    })
        .then((response) => {
            if (response.data.success == true) {
                Interviewed.value = response.data.data;
            } else {
                alert("获取面试人信息失败");
            }
        })
        .catch(function (error) {
            console.log(error);
        });
};
loadInterviewed();

const handleClick = (userName) => {
    loadDetail(userName);
}

const timer = setInterval(() => {
    loadNoInterview();
    loadInterviewed();
}, 1000 * 60 * 10);


const drawerChange = ref(false)

function cancelClick() {
    drawerChange.value = false
}

const pdfUrl = ref();

const getPdfUrl = (userName) => {
    return "http://39.101.74.9/pdfs/"+userName+".pdf"
}

</script>


<style scoped>
* {
    margin: 0;
    padding: 0;
}

.box0 {
    height: 100%;
    /* background-color: aqua; */
    display: flex;
    /* 使用 Flex 布局 */
    justify-content: space-between;
    /* 子元素均匀分布，等宽排列 */
    align-items: center;
    /* 垂直居中对齐子元素 */
}

.box1,
.box2 {
    height: 100%;
    flex: 1;
    /* 子元素等宽，自动填充可用空间 */
    /* 可根据需要设置边框样式 */
}

.box1 {
    margin: 1vw;
    /* background-color: beige; */
    flex-direction: column;
    /* 子元素垂直排列 */
    align-items: center;
    /* 水平居中对齐子元素 */
}

.box1_1,
.box1_2 {
    /* 可根据需要设置内边距 */
    /* border: 1px solid #000; */
    border-radius: 20px;
    /* 可根据需要设置边框样式 */
    background-color: rgb(220, 232, 245);
    padding: 1vh;
}

.box1_1 {
    height: 50%;
}

.box1_2 {
    margin-top: 2vh;
    height: 47%;
}

.box2 {
    margin: 0.3vw;
    height: 98%;
    border-radius: 20px;
    background-color: rgb(220, 232, 245);
}

.applicationDetail {
    margin: 2vh;
}

.adminJudge {
    margin-top: 25vh;
}

.adminJudge :deep(.el-radio) {
    margin: 0 10px;
}

.adminJudge :deep(.el-button) {
    width: 10vh;
    font-size: 2.5vh;
}

.table1 :deep(.el-table__cell) {
    padding-top: 0.2vh;
    font-size: small;
}

:deep(.el-drawer.ltr){
    background: transparent;
}

:deep(.el-drawer__header){
    margin: 0;
    padding: 0;
}
</style>