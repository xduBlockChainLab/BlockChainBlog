<template>
    <div class="shell">
        <div class="page0">
            <div class="BCLogo">
                <img style="width: 18vw" src="../assets/index/BClogo.png" />
                <div style="font-size: 9vw;  text-shadow: 0 0 0.5em #38a1ff" class="BCName">
                    <router-link to="/" class="toIndex">西电区块链协会</router-link>
                </div>
            </div>
            <div class="submitForm">
                <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="25vw" class="applicationForm"
                    status-icon label-position="left">
                    <el-form-item class="label" label="姓名" prop="name">
                        <el-input v-model="ruleForm.name" placeholder="请输入名字" />
                    </el-form-item>
                    <el-form-item label="年级:" prop="grade">
                        <el-select v-model="ruleForm.grade" placeholder="请选择你的年级">
                            <el-option label="21" value="21" />
                            <el-option label="22" value="22" />
                            <el-option label="23" value="23" />
                            <el-option label="24" value="24" />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="邮箱:" prop="email">
                        <el-input v-model="ruleForm.email" placeholder="请输入邮箱" />
                    </el-form-item>
                    <el-form-item label="QQ:" prop="qq">
                        <el-input v-model="ruleForm.qq" placeholder="请输入QQ" />
                    </el-form-item>
                    <el-form-item label="兴趣方向" prop="type">
                        <el-radio-group v-model="ruleForm.type">
                            <el-radio :label="1">技术部-开发组</el-radio>
                            <el-radio :label="2">技术部-应用组</el-radio>
                            <el-radio :label="3">宣传部</el-radio>
                            <el-radio :label="4">活动部</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="个人简介" prop="desc">
                        <el-input v-model="ruleForm.desc" type="textarea" :autosize="{ minRows: 3, maxRows: 8 }"
                            placeholder="请输入个人简介" />
                    </el-form-item>
                    <el-form-item label="验证码:" prop="captcha">
                        <el-input v-model="ruleForm.captcha">
                            <template #suffix>
                                <el-button type="primary" @click="getCaptcha(ruleFormRef)" :disabled="buttonDisabled">
                                    <!-- 获取验证码 -->
                                    {{ buttonDisabled ? ` ${countdownSeconds} 秒后可用` : '获取验证码' }}
                                </el-button>
                            </template>
                        </el-input>
                    </el-form-item>
                    <el-upload class="upload-demo" 
                        drag
                        accept=".pdf"
                        :action="uploadAction"
                        :data = "uploadData"
                        :before-upload="beforeUpload"
                        :on-success="handleUploadSuccess"
                        ref="upload"
                        :file-list="fileList"
                        :limit="1"
                        multiple>
                        <el-icon class="el-icon--upload">
                            <upload-filled />
                        </el-icon>
                        <div class="el-upload__text">
                            Drop file here or <em>click to upload</em>
                        </div>
                        <template #tip>
                            <div class="el-upload__tip">
                                只能上传pdf文件. 非必须, 但如果有, 我们很愿意接收您的简历:) 
                            </div>
                        </template>
                    </el-upload>
                    <el-form-item class="submitButton">
                        <el-button type="primary" @click="submitForm(ruleFormRef)">
                            申请加入
                        </el-button>
                        <el-button type="primary" @click="resetForm(ruleFormRef)">
                            重置内容
                        </el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import axios from 'axios';

import useRouter from '../router/index'
const router = useRouter

import { ElMessage } from 'element-plus'
import { UploadFilled } from '@element-plus/icons-vue'

interface RuleForm {
    name: string
    grade: string
    email: string
    qq: string
    type: Number
    desc: string
    captcha: string
}

const ruleFormRef = ref<FormInstance>()
const ruleForm = ref<RuleForm>({
    name: '',
    grade: '',
    email: '',
    qq: '',
    type: 0,
    desc: '',
    captcha: '',
})


const checkEmail = (rule: any, value: any, callback: any) => {
    const regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
    if (regEmail.test(value)) {
        // True 合法的邮箱
        return callback()
    }
    callback(new Error('请输入合法的邮箱'))
}

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
        {
            validator: checkEmail,
            trigger: 'blur',
        }
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

let userInterest: string

const submitForm = async (formEl: FormInstance | undefined) => {
    if (!formEl) return console.error("错误");
    await formEl.validate((valid, fields) => {
        if (valid) {
            ElMessage({
                message: '正在提交申请 :)',
                type: 'success',
            })
            switch (ruleForm.value.type) {
                case 1:
                    userInterest = "develop"
                    break;
                case 2:
                    userInterest = "apply"
                    break;
                case 3:
                    userInterest = "publicity"
                    break;
                case 4:
                    userInterest = "activity"
                    break;
            }
            axios.post('application/submit', {
                "userName": ruleForm.value.name,
                "userGrade": ruleForm.value.grade,
                "userEmail": ruleForm.value.email,
                "userQq": ruleForm.value.qq,
                "userInterest": userInterest,
                "userDescription": ruleForm.value.desc,
                "captcha": ruleForm.value.captcha
            })
                .then(function (response) {
                    if (response.data.success == true) {
                        ElMessage({
                            message: '申请提交成功, 请查看您的邮箱以确定是否提交, 正在跳转到首页. 如果5秒网页未跳转, 请手动刷新一下 :)',
                            type: 'success',
                        })
                        setTimeout(() => {
                            router.push('/');
                        }, 5000)
                    } else {
                        ElMessage({
                            message: '申请提交失败, 请稍后重试 :(',
                            type: 'error',
                        })
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

const buttonDisabled = ref(false)
const countdownSeconds = ref(60)

let countdownTimer: any = null; // 定时器

const startCountdown = () => {
    if (!buttonDisabled.value) {
        buttonDisabled.value = true;
        countdownTimer = setInterval(() => {
            countdownSeconds.value -= 1;
            if (countdownSeconds.value <= 0) {
                clearInterval(countdownTimer);
                buttonDisabled.value = false;
                countdownSeconds.value = 10; // 重新设置倒计时时间
            }
        }, 1000); // 每秒更新倒计时
    }
}

const getCaptcha = async (formEl: FormInstance | undefined) => {
    if (!formEl) return console.error("错误");
    await formEl.validate((valid, fields) => {
        if (valid) {
            ElMessage({
                message: '正在获取验证码 :)',
                type: 'success',
            })
            axios.get('application/captcha', {
                params: {
                    email: ruleForm.value.email
                }
            })
                .then(function (response) {
                    if (response.data.success == true) {
                        ElMessage({
                            message: '获取验证码成功, 请邮箱查看, 有效时间2分钟 :)',
                            type: 'success',
                        })
                        startCountdown();
                    } else {
                        ElMessage({
                            message: '获取验证码失败, 请稍后重新尝试 :(',
                            type: 'error',
                        })
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

const uploadAction = axios.defaults.baseURL + 'application/submit/upload'

const uploadData = ref({
    email: '',
    name: '',
    fileType: ["pdf"]
});

const beforeUpload = (file: File) => {
    if(file.type.substring(file.type.length-3, file.type.length) !== "pdf"){
        ElMessage({
            message: '文件类型错误',
            type: 'error',
        })
        return false
    }
    if(ruleForm.value.name.length === 0){
        ElMessage({
            message: '请先填写表单内容',
            type: 'error',
        })
        return false
    }
    uploadData.value.name = ruleForm.value.name
    ElMessage({
        message: '正在上传简历, 如果未提示"上传成功", 请重试 :)',
        type: 'success',
    })
};

const handleUploadSuccess = (file : any) => {
    if (file.success == true) {
        ElMessage({
            message: '文件上传成功, 请点击申请加入!',
            type: 'success',
        })
    } else {
        ElMessage({
            message: '文件上传失败, 请再次尝试',
            type: 'error',
        })
    }
    fileList.value = [];
}

const fileList = ref([]); // 用于存储文件列表

</script>

<style scoped>
.BCLogo {
    display: flex;
    /* 使用 Flexbox 布局 */
    align-items: center;
    /* 在交叉轴上居中对齐（垂直居中） */
    justify-content: center;
    /* 在主轴上居中对齐（水平居中） */
    padding-top: 1vh;
}

.page0 {
    height: 100vh;
    width: 100%;
    /* background-image: url("../assets/index/5.png"); */
    background-size: cover;
    background-position: center;
    /* 让背景图片水平和垂直居中 */
}

.page0_button {
    display: flex;
    /* 使用 Flexbox 布局 */
    flex-direction: column;
    /* 垂直方向排列 */
    align-items: center;
    /* 在垂直方向上居中对齐 */
}

.page0 .submitButton .el-button {
    background-color: #38a1ff;
    color: white;
    width: 15vh;
    height: 4vh;
    border-radius: 30px;
    font-size: 3vh;
    margin: 2vh;
}

.submitForm {
    margin-top: 3vh;
    display: flex;
    /* 使用 Flexbox 布局 */
    justify-content: center;
    /* 在水平方向上居中对齐 */
    height: 100vh;
    /* 设置容器的高度，可以根据需要调整 */
}

.submitForm .el-form .el-form-item {
    width: 80vw;
}

.submitForm .el-form--label-left .el-form-item__label {
    justify-content: flex-end !important;
    /* 添加样式属性 */
}

.toIndex {
    text-decoration: none;
    color: black;
}
</style>
