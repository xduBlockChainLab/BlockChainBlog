<template>
    <div class="shell">
        <div class="BCLogo" style="padding-top: 5%;">
            <img style="width: 100px" src="../assets/index/BClogo.png" />
            <div style="
            font-size: 60px;
            color: #fefefe;
            text-shadow: 0 0 0.5em #00ffff, 0 0 0.2em #5c5c5c;
        " class="BCName">
                <router-link to="/" class="toIndex">BlockChain Studio 208</router-link>
            </div>
        </div>
        <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="150px" class="applicationForm" status-icon
            label-position="left">
            <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name" />
            </el-form-item>
            <el-form-item label="年级:" prop="grade">
                <el-select v-model="ruleForm.grade" style="width:auto;" placeholder="请选择你的年级">
                    <el-option label="20" value="20" />
                    <el-option label="21" value="21" />
                    <el-option label="22" value="22" />
                    <el-option label="23" value="23" />
                </el-select>
            </el-form-item>
            <el-form-item label="邮箱:" prop="email">
                <el-input v-model="ruleForm.email" />
            </el-form-item>
            <el-form-item label="QQ:" prop="qq">
                <el-input v-model="ruleForm.qq" />
            </el-form-item>
            <el-form-item label="兴趣方向" prop="type">
                <el-radio-group v-model="ruleForm.type">
                    <el-radio :label="1">前端开发</el-radio>
                    <el-radio :label="2">后端开发</el-radio>
                    <el-radio :label="3">区块链开发</el-radio>
                    <el-radio :label="4">文案PPT设计</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="个人简介" prop="desc">
                <el-input v-model="ruleForm.desc" type="textarea" :autosize="{ minRows: 3, maxRows: 8 }" />
            </el-form-item>
            <el-form-item class="submitButton">
                <el-button type="primary" @click="submitForm(ruleFormRef)">
                    申请加入
                </el-button>
                <el-button type="primary" @click="resetForm(ruleFormRef)">重置内容</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import axios from 'axios';

import useRouter from '../router/index'
const router = useRouter

import { ElMessage } from 'element-plus'
import { ca } from 'element-plus/es/locale';

interface RuleForm {
    name: string
    grade: string
    email: string
    qq: string
    type: Number
    desc: string
}

const formSize = ref('default')
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<RuleForm>({
    name: '',
    grade: '',
    email: '',
    qq: '',
    type: 0,
    desc: '',
})

const rules = reactive<FormRules<RuleForm>>({
    name: [
        { required: true, message: '请输入名字', trigger: 'blur' },
        { min: 2, max: 10, message: '名字异常', trigger: 'blur' },
    ],
    grade: [
        {
            required: true,
            message: '请选择你的年级',
            trigger: 'blur',
        },
    ],
    email: [
        {
            required: true,
            message: '请输入邮箱',
            trigger: 'blur',
        },
    ],
    qq: [
        {
            required: true,
            message: '请输入QQ',
            trigger: 'blur',
        },
    ],
    type: [
        {
            type: 'number',
            required: true,
            message: '选择你感兴趣的方向',
            trigger: 'blur',
        },
    ],
    desc: [
        { required: true, message: '请进行自我介绍', trigger: 'blur' },
    ],
})

let userInterest : string

const submitForm = async (formEl: FormInstance | undefined) => {  
    if(!formEl) return console.error("错误");
    await formEl.validate((valid, fields) => {
        if (valid) {
            switch (ruleForm.type) {
                case 1:
                    userInterest = "FrontEnd"
                    break;
                case 2:
                    userInterest = "BackEnd"
                    break;
                case 3:
                    userInterest = "BlockChain"
                    break;
                case 4:
                    userInterest = "Beauty/PPT"
                    break;
            }
            axios.post('application/submit', {
                "userName": ruleForm.name,
                "userGrade": ruleForm.grade,
                "userEmail": ruleForm.email,
                "userQq": ruleForm.qq,
                "userInterest": userInterest,
                "userDescription": ruleForm.desc
            })
                .then(function (response) {
                    if (response.data.code == 2001) {
                        console.log(response.data.msg)
                        ElMessage({
                            message: '申请提交成功, 正在跳转到首页.',
                            type: 'success',
                        })
                        setTimeout(()=>{
                            router.push('/');
                        },1000)
                    } else {
                        formEl.resetFields()
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        } else {
        console.log('error submit!', fields)
        }
    })
}

const resetForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.resetFields()
}


</script>

<style scoped>
* {
    padding: 0;
    margin: 0;
}
.shell {
    background-image: url("../assets/index/path.png");
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

.el-form {
    margin-top: 40px;
    width: 60%;
    margin-left: 20%;
}
/* 
.el-form-item :deep() .label {
    font-size: large;
} */

.el-form-item{
    padding: 15px;
}

.applicationForm :deep() .el-form-item__label{
    font-size: large;
}

.el-radio{
    color: white;
    margin-left: 30px;
}

.applicationForm :deep() .el-form-item__content{
    margin-left: 20px;
}

.applicationForm :deep() .el-radio__label{
    font-size: medium;
}

.submitButton .el-button{
    margin-left: 28%;
    font-size: large;
    background-color: black;
}
.toIndex {
    text-decoration: none;
    color: #00ffff;
}
</style>
