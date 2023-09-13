<template>
    <div class="shell">
        <div class="BCLogo" style="padding-top: 5%;">
            <img style="width: 100px" src="/src/assets/index/BClogo.png" />
            <div style="
            font-size: 60px;
            color: #fefefe;
            text-shadow: 0 0 0.5em #00ffff, 0 0 0.2em #5c5c5c;
        " class="BCName" >
                <router-link to="/" class="toIndex">BlockChain Studio 208</router-link>
            </div>
        </div>
        <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="150px" class="applicationForm" status-icon
            label-position="left">
            <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name" />
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="ruleForm.email" />
            </el-form-item>
            <el-form-item label="密码:" prop="password">
                <el-input v-model="ruleForm.password" />
            </el-form-item>
            <el-form-item class="submitButton">
                <el-button type="primary" @click="Register(ruleFormRef)">
                    注册
                </el-button>
                <el-button type="primary" @click="resetForm(ruleFormRef)">重置内容</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage } from 'element-plus'
import axios from 'axios';
import useRouter from '../router/index'

interface RuleForm {
    name: string
    email: string
    password: string
}

const formSize = ref('default')
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<RuleForm>({
    name: '',
    email: '',
    password: '',
})

const rules = reactive<FormRules<RuleForm>>({
    name: [
        {
            required: true,
            message: '请输入名字',
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
    password: [
        { 
            required: true, 
            message: '请输入密码', 
            trigger: 'blur' },
    ],
})


const Register = async (formEl: FormInstance | undefined) => {
    if(!formEl) return console.error("错误");
    await formEl.validate((valid, fields) => {
        if (valid) {
            axios.post('bc208/register', {
                "username": ruleForm.name,
                    "email": ruleForm.email,
                    "password": ruleForm.password
            })
                .then(function (response) {
                    if (response.data.code == 2005) {
                        ElMessage({
                            message: '注册成功, 等待管理员确认, 即将回到初始页面.',
                            type: 'success',
                        })
                        setTimeout(()=>{
                            router.push('/');
                        },4000)
                    } else {
                        ElMessage({
                            message: '注册失败, 请重新尝试.',
                            type: 'success',
                        })
                        setTimeout(()=>{
                            router.push('/');
                        },4000)
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

const router = useRouter

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
    margin-top: 80px;
    width: 40%;
    margin-left: 30%;
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

.submitButton .el-button{
    margin-left: 20%;
    font-size: large;
    background-color: black;
}

.toIndex {
    text-decoration: none;
    color: #00ffff;
}

</style>