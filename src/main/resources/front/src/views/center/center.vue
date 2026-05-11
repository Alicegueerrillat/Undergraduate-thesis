<script setup>
/**
 * @description 个人中心
 */
import '@/style/center.scss'

// 注册表单组件
import '@/components/FormItem/index.js'
import { reactive, ref, computed, provide } from 'vue'

import CenterTitle from './CenterTitle.vue'
import Custom from './Custom.vue'
import Info from './Info.vue'
import Menus from './Menus.vue'
import Tabs from './Tabs.vue'
import CenterEdit from './CenterEdit.vue'
import UpdatePassword from './updatePassword.vue'


import tableConfigs from '@/utils/tableConfigs'
import { useUserInfo } from '@/store'
import { useRouter } from 'vue-router'
import { toListPage } from '@/utils/navigate'
import { isAuth } from '@/utils/auth'

let router = useRouter()
// 检查用户是否登录
const userInfoStore = useUserInfo()
// if (!userInfoStore.userInfo.isLogin) {
//   router.push('/login')
// }

let tableName = localStorage.getItem('sessionTable')
let tableConfig = tableConfigs[tableName]
let { table, columns } = tableConfig

const title = '个人中心'
const userAvatar = computed(() => userInfoStore.userInfo.avatar)
// ----------------------------------
// ---------- 个人信息 ----------------
// ----------------------------------
const infoList = ref([])
infoEvent()
function infoEvent() {
  let excludeColumnTypes = ['图', '音', '视', '文', '编'] // 媒体类型不显示
  let excludeColumnNames = [
    'passwordwrongnum',
    'mima',
    'password',
    'status',
    'money',
    'pquestion',
    'panswer',
    'sfsh',
    'shhf',
    'clicktime',
    'browseduration',
    'thumbsupnum',
    'crazilynum',
    'clicknum',
    'storeupnum',
    'discussnum',
  ] // 排除的字段名
  let centerColumns = columns
    .filter(
      item =>
        !excludeColumnTypes.includes(item.type) && !excludeColumnNames.includes(item.columnName)
    )
    .slice(0, 6) // 只显示前6个字段
  // 更新个人信息
  let userForm = JSON.parse(localStorage.getItem('userForm'))

  let list = []
  centerColumns.forEach(column => {
    list.push({
      label: column.comments,
      value: userForm[column.columnName] || '',
    })
  })
  infoList.value = list
}

// ----------------------------------
// ---------- 标签页 ----------------
// ----------------------------------
const tabs = getTabs()
function getTabs() {
  let list = [
    {
      label: '个人中心',
      name: CenterEdit,
    },
    {
      label: '修改密码',
      name: UpdatePassword,
    },
  ]


  return list
}

function tabEvent(tab, event) {}

// ----------------------------------
// ---------- 菜单 ----------------
// ----------------------------------
const menuList = ref(getMenuList())
function getMenuList() {
  let list = [
    {
      label: '我的收藏',
      path: '/index/storeup',
      query: { type: 1 }
    },
  ]


  const filterTableNames = [
    'storeup',
    'systemintro',
    'aboutus',
    'menu',
  ]
  let roleMenu = JSON.parse(localStorage.getItem('roleMenu'))
  roleMenu.forEach(item => {
    item.child &&
      item.child.forEach(child => {
        let { menu, tableName } = child
        if (filterTableNames.includes(tableName)) {
          return
        }

        // 确定路径
        let path = '/index/' + tableName

        list.push({
          label: menu,
          tableName: tableName,
          path,
          query: { centerType: 1 }
        })
      })
  })

  return list
}

function menuEvent(item) {
  router.push({ path: item.path, query: item.query })
}


provide('center', {
  infoList,
  userAvatar,

  tabs,
  tabEvent,

  menuList,
  menuEvent,

  infoEvent,
})
</script>

<template>
  <div class="center-page">
      <CenterTitle :title="title" />
  <div class="center-page-inner">
    <Info />
    <Tabs />
    <Menus />
    <Custom />
  </div>
  </div>
</template>
