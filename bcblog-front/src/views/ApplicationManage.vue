<template>
    <div style="width: 40%; float: left; height: 100%" class="table">
        <div style="height: 55%">
            <div style="font-size: smaller; height: 40px; line-height: 40px">
                待面试人名单
            </div>
            <div>
                <el-table :data="noInterview" height="600px" style="width: 100%">
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
        </div>
        <div style="height: 45%; margin-top: 0px">
            <div style="font-size: smaller; height: 42px; line-height: 40px">
                已面试人名单
            </div>
            <div>
                <el-table :data="Interviewed" height="490px" style="width: 100%">
                    <el-table-column prop="userName" label="Name" />
                    <el-table-column prop="userGrade" label="grade" />
                    <el-table-column prop="userInterest" label="type" />
                    <el-table-column fixed="right" label="Operations">
                        <template #default="scope">
                            <el-button link type="primary" size="small"
                                @click="handleClick(scope.row.userName)">重新评价</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
    </div>
    <div style="width: 60%; float: left; height: 100%" class="judge">
        <div class="applicationDetail">
            <el-descriptions :data="applicationDetail" :props="DefaultProps" title="面试人详情" :column="2" border
                style="font-size: smaller; height: 0px; line-height: 40px; margin: 0;">
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
                    <!-- TODO:内容过长时, 蹦出来影响布局 -->
                </el-descriptions-item>
            </el-descriptions>
        </div>
        <div class="adminJudge" style="display, width: 50%; padding-top: 550px">
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
</template>


<script setup>
import axios from "axios";
import { DefaultProps } from "element-plus";
import { ref } from "vue";

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
                applicationDetail.value = response.data.result
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
            if (response.data.code == 2000) {
                alert("面试评价成功.")
                console.log(response.data.msg)
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
            if (response.data.code == 2002) {
                Interviewed.value = response.data.result;
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
}, 1000*60*10);

</script>


<style scoped>
.applicationDetail :deep() .el-descriptions__body .el-descriptions__table .el-descriptions__cell {
    text-align: center;
}

.applicationDetail :deep() .el-descriptions__header {
    display: block;
    margin-bottom: 0px;
}

/* :deep(.introduction :deep().el-descriptions-el-descriptions__cell .el-descriptions__content  )  { 
    max-width: 295px;  
    word-break: break-all; 
    word-wrap: break-word;
 }
*/
/* td.el-descriptions__cell.el-descriptions__content{
    max-width: 295px;  
    word-break: break-all; 
    word-wrap: break-word;
} */
</style>