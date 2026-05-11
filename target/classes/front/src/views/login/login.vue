<script setup>
import { reactive, ref, onMounted, provide, watch } from 'vue'
import { useRouter, useRoute } from 'vue-router'

import UserName from './UserName.vue'
import Password from './Password.vue'
import Captcha from './Captcha.vue'
import Roles from './Roles.vue'
import LoginButton from './LoginButton.vue'
import ForgetButton from './ForgetButton.vue'
import RegisterButtons from './RegisterButtons.vue'
import FaceButton from './FaceButton.vue'
import Custom from './Custom.vue'
import LoginTitle from './LoginTitle.vue'
import EmailButton from './EmailButton.vue'
import EmailDailog from './EmailDailog.vue'
import FaceMatch from '@/components/FaceMatch.vue'

import { roleList } from '@/utils/role'
import { getAvatar } from '@/utils/index'
import { clearFilePath } from '@/utils/getFilePath'
import { loginAPI, faceLoginAPI, getSessionAPI } from '@/api/login'
import { initMenus } from '@/utils/menu'
import { useUserInfo } from '@/store'
import { isAuth } from '@/utils/auth'

const router = useRouter()
const route = useRoute()
let { redirect, tableName: initTableName, username: initUsername } = route.query
const userInfoStore = useUserInfo()

// 登录角色列表
let roleOptions = roleList
  .filter(item => item.hasFrontLogin == '是')
  .map(item => ({
    value: item.tableName,
    label: item.roleName,
    accountComments: item.accountComments
  }))
// 两个角色以上，才显示角色下拉
let moreThan2 = roleOptions.length > 1

// 注册角色列表
let registerOptions = roleList
  .filter(item => item.hasFrontRegister == '是')
  .map(item => ({
    tableName: item.tableName,
    roleName: item.roleName,
  }))


const ruleFormRef = ref()
// 表单数据
const ruleForm = reactive({
  username: initUsername ? initUsername : '',
  password: '',
  tableName: initTableName ? initTableName : roleOptions[0].value,
})
// 表单验证规则
const rules = reactive({
  username: [
    {
      required: true,
      message: '请输入账号',
      trigger: 'blur'
    },
  ],
  password: [
    {
      required: true,
      message: '请输入密码',
      trigger: 'blur',
    },
  ],
})


// 登录
async function handleLogin() {
  // 表单校检逻辑
  let valid = await ruleFormRef.value.validate((valid, fields) => {
    if (!valid) {
      // 验证不通过，提示第一个错误
      let firstErrorField = Object.entries(fields)
      let firstErrorMessage = firstErrorField[0][1][0].message || '表单校验失败，请检查输入'
      ElMessage.error(firstErrorMessage)
    }
  })
  if (!valid) return


  // 登录逻辑
  const loading = ElLoading.service({
    lock: true,
    text: '登录中... ..',
    background: 'rgba(0, 0, 0, 0.7)',
  })

  try {

    // 登录
    let res = await loginAPI(ruleForm)

    // 获取用户信息
    await loginSucessEvent(res.token, ruleForm.tableName)

  } catch (error) {
    let msg = error.message || error.msg || ''
    msg = '登录失败: ' + msg
    ElMessage.error(msg)
  }

  loading.close()

}

// ----------------------------------
// ---------- 人脸登录---------------
// ----------------------------------
const hasFaceMatch = ref(false)
const faceVisible = ref(false)
function faceEvent() {
  faceVisible.value = true
}
// 切换角色时，判断有无人脸识别
watch(
  () => ruleForm.tableName,
  newTableName => {
    let flag = roleList.some(item => item.tableName === newTableName && item.faceMatch === '是')
    hasFaceMatch.value = flag
  },
  {
    immediate: true,
  }
)
const faceLogin = async fileName => {
  const loading = ElLoading.service({
    lock: true,
    text: '登录中... ..',
    background: 'rgba(0, 0, 0, 0.7)',
  })
  try {
    let tableName = ruleForm.tableName

    let res = await faceLoginAPI(tableName, fileName)

    await loginSucessEvent(res.token, tableName)
  } catch (error) {
    let msg = error.message || error.msg || ''
    msg = '人脸登录失败: ' + msg
    ElMessage.error(msg)
  }

  loading.close()
}

// ----------------------------------
// -------- 邮箱/手机登录 ------------
// ----------------------------------
const emailVisible = ref(false)
const emailType = ref('邮箱登录')
const hasEmail = ref(true)
function emailEvent() {
  emailVisible.value = true
}
watch(
  () => ruleForm.tableName,
  newTableName => {
    let role = roleList.find(item => item.tableName === newTableName)
    hasEmail.value = role.emailRegister == '是' || role.emailRegister == '短信'
    emailType.value = role.emailRegister == '短信' ? '手机登录' : '邮箱登录'
  },
  {
    immediate: true,
  }
)

// 登录成功后，做一些缓存等事件
async function loginSucessEvent(token, tableName) {
  // 缓存一些用户信息到localstorage
  // 第一步必须缓存token，因为后续api需要从localStorage.getItem('Token')取
  localStorage.setItem('Token', token)

  // 请求后端 获取登录信息
  let userInfoRes = await getSessionAPI(tableName)
  // 设置 账户名和图像
  let [avatar, account] = getAvatar(userInfoRes.data, tableName)
  userInfoStore.setUserInfo({
    avatar,
    username: account,
    isLogin: true,
  })

  // 用户表名（数据库
  localStorage.setItem('sessionTable', tableName)
  // 账户
  localStorage.setItem('username', account)
  // 缓存用户全部信息
  localStorage.setItem('userForm', JSON.stringify(userInfoRes.data))
  // 缓存用户id
  localStorage.setItem('userid', userInfoRes.data.id)
  // 用户头像
  localStorage.setItem('useravatar', clearFilePath(avatar))
  // 角色名
  localStorage.setItem('role', roleOptions.find(item => item.value == tableName).label)

  // 登录的角色对应的菜单roleMenu
  // 尝试修复menus不存在的异常
  if (!localStorage.getItem('menus')) {
    await initMenus()
  }
  // 角色菜单
  let menuItem = JSON.parse(localStorage.getItem('menus')).find(
    item => item.tableName === tableName
  )
  localStorage.setItem('roleMenu', JSON.stringify(menuItem.backMenu))

  // 登录成功，跳转到首页 / 上一个页面
  let nextPath = redirect ? redirect : '/index/home'
  router.push(nextPath)
}

onMounted(() => {
  setTimeout(() => {
    requestIdleCallback(() => {
      // 提前加载 个人中心
      import('@/views/center/center.vue')
    })
  }, 1000)
})

function registerEvent(item) {
  router.push({
    path: '/register',
    query: { tableName: item.tableName },
  })
}
function forgetEvent() {
  router.push('/forgetPassword')
}

provide('login', {
  ruleForm,
  roleOptions,
  registerOptions,
  hasFaceMatch,
  faceEvent,
  faceLogin,

  registerEvent,
  forgetEvent,

  hasEmail,
  emailType,
  emailEvent,
})

</script>
<template>
  <div
    class="login"
    :style="
      $projectImages.fLoginBackgroundImg
        ? `background-image: url(${$projectImages.fLoginBackgroundImg})`
        : ''
    "
  >
    <el-form
      class="loginform animate__animated animate__fadeInLeft"
      :model="ruleForm"
      :rules="rules"
      ref="ruleFormRef"
      @submit.prevent="handleLogin"
    >
      <div class="loginform-wrapper">
  <LoginTitle />
  <UserName />
  <Password />
  <Captcha />
  <Roles />
  <LoginButton />
  <RegisterButtons />
  <FaceButton />
  <EmailButton />
  <ForgetButton />
  <Custom />
</div>
  
    </el-form>
    <FaceMatch v-model="faceVisible" @submit="faceLogin" />
    <EmailDailog
      v-if="emailVisible"
      v-model="emailVisible"
      :emailType="emailType"
      :tableName="ruleForm.tableName"
      @loginSucessEvent="loginSucessEvent"
    />  
  </div>
</template>
