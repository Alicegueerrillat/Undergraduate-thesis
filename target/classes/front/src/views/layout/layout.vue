<script setup>
import '@/style/top.scss'

import { ref, shallowRef, watch, watchEffect, defineAsyncComponent, provide, computed, onMounted, onBeforeUnmount } from 'vue'
import { useRouter, useRoute } from 'vue-router'

import Custom from './Custom.vue'
import HeaderTitle from './HeaderTitle.vue'
import LayoutContent from './LayoutContent.vue'
import LayoutBanner from './LayoutBanner.vue'
import LayoutFooter from './LayoutFooter.vue'
import LayoutMenu from './LayoutMenu.vue'
import Notice from './Notice.vue'
import RoleMenu from './RoleMenu.vue'
import Weather from './Weather.vue'

import projectConfig from '@/utils/project'
import base from '@/utils/base'
import axios from 'axios'
import { getOptionAPI } from '@/api/common'
import { getDetailAPI, updateAPI, getPageAPI } from '@/api/list'
import { getBannerAPI } from '@/api/front'
import { getFirstFilePath } from '@/utils/getFilePath'
import { isAuth } from '@/utils/auth'
import { useUserInfo } from '@/store'

const router = useRouter()
const route = useRoute()

// ----------------------------------
// ---------- 角色菜单 ---------------
// ----------------------------------
const roleMenus = ref([])
const userInfoStore = useUserInfo()
let userName = computed(() => userInfoStore.userInfo.username)
let userAvatar = computed(() => userInfoStore.userInfo.avatar)
let isLogin = computed(() => userInfoStore.userInfo.isLogin)
function getUserMenus(isLogin) {
  if (!isLogin) {
    roleMenus.value = []
    return
  }

  let menus = [
    {
      key: 'chat',
      icon: 'ChatLineSquare',
      label: 'AI助手',
      action: () =>{
        dialogTitle.value = 'AI助手'
        dialogComponent.value = ServiceChat
        dialogVisible.value = true
      }      
    },
    {
      key: 'center',
      url: '/index/center',
      icon: 'UserFilled',
      label: '个人中心',
      action: () =>{
        router.push('/index/center')
      }
    },
    {
      key: 'logout',
      icon: 'Back',
      label: '退出登录',
      action: () =>{
        // 前端登出
        userInfoStore.logOut()
        localStorage.removeItems()
        router.push('/index/home')
      }
    },
  ]
  roleMenus.value = menus
}
// 角色菜单点击事件
let roleMenuEvent = key => {
  let menu = roleMenus.value.find(menu => menu.key == key)
  menu.action()
}
// 登录
function loginEvent() {
  router.push('/login')
}

// ----------------------------------
// --------- 页面菜单 ----------------
// ----------------------------------
const menus = ref([])
// 更新菜单 + 提取二级菜单
async function getSubMenus() {
  let list = [
    {
      label: '首页',
      url: '/index/home',
    },
    {
      label: '学院',
      url: '/index/xueyuan',
    },
    {
      label: '课程信息',
      url: '/index/kechengxinxi',
    },
  ]
  // 判断是否有分类 --> 添加二级菜单
  try {
    for (let index = 0; index < list.length; index++) {
      const item = list[index]

      let { refTable, refColumn, columnName } = item
      if (refTable && refColumn) {
        let res = await getOptionAPI(refTable, refColumn)

        // 添加children字段
        item.children = res.data.map(i => {
          return {
            // 拼接query参数
            url: item.url + '?' + columnName + '=' + i,
            label: i,
          }
        })
      }
    }
  } catch (error) {} 
  menus.value = list
}

function clickEvent(item) {
  router.push(item.url)
}

watch(
  () => userInfoStore.userInfo.isLogin,
  isLogin => {
    getUserMenus(isLogin)
    getSubMenus()
  },
  { immediate: true }
)



// ----------------------------------
// ---------- 轮播图 ----------------
// ----------------------------------
const banners = ref([])
const showBanner = computed(() => {
  let path = route.path
  let hasPicture = banners.value.length

  return hasPicture && (path == '/index/home' || false)
})
getBanners()
async function getBanners() {
  let res = await getBannerAPI()
  res.data.list.forEach(item => {
    item.picture = getFirstFilePath(item.value)
  })
  banners.value = res.data.list
}
// 轮播图点击跳转
function bannerEvent(item) {

  let { url } = item
  if (!url) {
    return
  }

  if (url.startsWith('/')) {
    // 本项目内链
    router.push(url)
  } else {
    // 外链
    // 是否有http , https域名
    !/^http:\/\/|https:\/\//.test(url) && (url = 'https://' + url)

    window.open(url, '_blank')
  }
}

// ----------------------------------
// ---------- 弹框 ---------------
// ----------------------------------
const dialogVisible = ref(false)
const dialogTitle = ref('弹框标题')
const dialogComponent = shallowRef(null)
let dialogData = {}

// ----------------------------------
// ---------- 客服 ----------------
// ----------------------------------
// 懒加载一个子组件
const ServiceChat = defineAsyncComponent(() => import('@/components/chat/ServiceChat.vue'))

// ----------------------------------
// ------------ 底部 ----------------
// ----------------------------------
const footerContent = ref('')


provide('header', {


  isLogin,
  roleMenus,
  userName,
  userAvatar,
  roleMenuEvent,
  loginEvent,

  banners,
  showBanner,
  bannerEvent,

  menus,
  clickEvent,

  footerContent,
})
</script>

<template>
  <div class="layout-page">
    
<div class="header-wrapper">
  <HeaderTitle />
  <LayoutMenu />
  <RoleMenu />
</div>
<div class="header-wrapper2">
  <Notice />
  <Weather />
</div>
<LayoutBanner v-if="showBanner" />
<LayoutContent />
<LayoutFooter />
<Custom />
      

    <!-- 客服聊天 -->
    <el-dialog
      class="yy-dialog"
      v-model="dialogVisible"
      :title="dialogTitle"
      destroy-on-close
      :close-on-click-modal="false"
    >
      <component v-model="dialogVisible" :is="dialogComponent" :data="dialogData" />
    </el-dialog>

    <!-- 滚动到顶部 -->
    <el-backtop :right="100" :bottom="100" />

  </div>
</template>
