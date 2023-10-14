<template>
    <div style="width: 50%; float: left; " class="table">
        <div style="font-size: smaller; height: 40px; line-height: 40px">
            成员列表
        </div>
        <el-table :data="members" style="width: 100%">
            <el-table-column prop="userName" label="名字"></el-table-column>
            <el-table-column prop="userGrade" label="年级"></el-table-column>
            <el-table-column prop="userInterest" label="方向"></el-table-column>
            <el-table-column prop="userAuth" label="身份"></el-table-column>
            <el-table-column fixed="right" label="操作">
                <template #default="scope">
                    <!-- <el-button link type="primary" size="small" @click="changeAuth(scope.row.userName)">修权</el-button> -->
                    <el-button link type="primary" size="small" @click="drawerChange = true, userName = scope.row.userName">改权</el-button>
                    <el-button link type="primary" size="small" @click="deleteMember(scope.row.userName)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>

    <el-drawer class="drawerChange" v-model="drawerChange" direction=ltr>
        <template #header>
            <h1>修改用户权限</h1>
        </template>
        <template #default>
            <div>
                <el-radio v-model="userAuth" label="0" size="large">外部人员</el-radio>
                <el-radio v-model="userAuth" label="1" size="large">协会人员</el-radio>
                <el-radio v-model="userAuth" label="2" size="large">工作室人员</el-radio>
            </div>
        </template>
        <template #footer>
            <div style="flex: auto">
                <el-button @click="cancelClick()">取消</el-button>
                <el-button type="primary" @click="changeAuth(userName)">确定</el-button>
            </div>
        </template>
    </el-drawer>

    <div style="width: 50%; padding-left: 10px; float: left; height: 100%" class="judge">
        <div style="font-size: smaller; height: 40px; line-height: 40px">
            成员申请
        </div>
        <el-table :data="applications" style="width: 100%">
            <el-table-column prop="userName" label="名字"></el-table-column>
            <el-table-column prop="userGrade" label="年级"></el-table-column>
            <el-table-column prop="userInterest" label="方向"></el-table-column>
            <el-table-column prop="userAuth" label="身份"></el-table-column>
            <el-table-column fixed="right" label="操作">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="pass(scope.row.userName)">通过</el-button>
                    <el-button link type="primary" size="small" @click="deleteMember(scope.row.userName)">拒绝</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>


<script lang="ts" setup>
import axios from "axios";
import { ref } from "vue";
import { ElMessageBox } from 'element-plus'

import { ElMessage } from 'element-plus'

let applications = ref();
const loadApplications = () => {
    axios.get("admin/applications", {
        headers: {
            token: localStorage.getItem("token"),
        },
    })
        .then((response) => {
            if (response.data.success == true) {
                applications.value = response.data.data;
            } else {
                alert("获取面试人信息失败");
            }
        })
        .catch(function (error) {
            console.log(error);
        });
};
loadApplications();

let members = ref();
const loadMember = () => {
    axios.get("admin/members", {
        headers: {
            token: localStorage.getItem("token"),
        },
    })
        .then((response) => {
            if (response.data.success == true) {
                members.value = response.data.data;
            } else {
                alert("获取面试人信息失败");
            }
        })
        .catch(function (error) {
            console.log(error);
        });
};
loadMember();

const timer = setInterval(() => {
    loadApplications();
    loadMember();
}, 1000 * 20);

// const changeAuth = (userName) => {
//     axios.get("application/loadDetail", {
//         params: {
//             userName: userName
//         },
//         headers: {
//             token: localStorage.getItem("token"),
//         },
//     })
//         .then((response) => {
//             if (response.data.success == true) {
//                 applicationDetail.value = response.data.data
//             } else {
//                 alert("获取面试人信息失败");
//             }
//         })
//         .catch(function (error) {
//             console.log(error);
//         });
// }

const deleteMember = (userName: string) => {
    axios.get("admin/deleteMember", {
            params: {
                userName: userName
            },
            headers: {
                token: localStorage.getItem("token"),
            },
        })
        .then((response) => {
            if (response.data.success == true) {
                ElMessage({
                    message: '删除用户成功',
                    type: 'success',
                })
            } else {
                ElMessage({
                    message: '删除用户失败',
                    type: 'error',
                })
            }
            loadMember();
        })
        .catch(function (error) {
            console.log(error);
        });
}


const pass = (userName: string) => {
    axios.get("admin/passApplication", {
            params: {
                userName: userName
            },
            headers: {
                token: localStorage.getItem("token"),
            },
        })
        .then((response) => {
            if (response.data.success == true) {
                ElMessage({
                    message: '删除用户成功',
                    type: 'success',
                })
            } else {
                ElMessage({
                    message: '删除用户失败',
                    type: 'error',
                })
            }
            loadMember();
            loadApplications();
        })
        .catch(function (error) {
            console.log(error);
        });
}

const drawerChange = ref(false)
const userAuth = ref('userAuth')
const userName = ref()

function cancelClick() {
    drawerChange.value = false
}

function changeAuth(userName: string) {
    ElMessageBox.confirm(`确定修改该用户权限为: ${userAuth.value} ?`)
    axios.get("admin/changeAuth", {
        params: {
            userName: userName,
            userAuth: userAuth.value,
        },
        headers: {
            token: localStorage.getItem("token"),
        },
    })
        .then((response) => {
            if (response.data.success == true) {
                ElMessage({
                    message: '修改用户权限成功',
                    type: 'success',
                })
            } else {
                ElMessage({
                    message: '修改用户权限失败',
                    type: 'error',
                })
            }    
            setTimeout(() => {
                drawerChange.value = false
            }, 3000)
            loadMember();
        })
        .catch(function (error) {
            console.log(error);
        });
}

</script>


<style scoped>


</style>