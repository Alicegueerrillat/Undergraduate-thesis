<script setup>
import '@/style/detail.scss'

import { ref, shallowRef, provide, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

import '@/components/DetailItem/index.js'
import '@/components/TabItem/index.js'

// 复用 列表list.vue的面包屑、热门
import BreadCrumb from '../list/BreadCrumb.vue'
import Hot from '../list/Hot.vue'

import Buttons from './Buttons.vue'
import Custom from './Custom.vue'
import DetailList from './DetailList.vue'
import Pictures from './Pictures.vue'
import Tabs from './Tabs.vue'
import ThumbsUp from './ThumbsUp.vue'
import Videos from './Videos.vue'
import ListEdit from '../list/ListEdit.vue'
import SeatReservation from '../list/components/SeatReservation.vue'
import Review from '../list/components/Review.vue'

import tableConfigs from '@/utils/tableConfigs'
import {
  addAPI,
  deleteAPI,
  getDetailAPI,
  getListAPI,
  getPageAPI,
  saveAPI,
  updateAPI,
} from '@/api/list'
import {
  getGroupAPI
} from '@/api/common'

import { getAutoSort1API } from '@/api/front'
import getUiList from '@/utils/getUiList'
import { toDetailPage, toListPage } from '@/utils/navigate'
import getFilePath, { getFilePaths } from '@/utils/getFilePath'
import { isAuth } from '@/utils/auth'
import dayjs from 'dayjs'
import { getAvatar } from '@/utils'
import { csuEvent } from '@/utils/feature'

let { tableName } = defineProps(['tableName'])
let tableConfig = tableConfigs[tableName]
let { table, columns, detailConfig } = tableConfig
let {
  imgName,
  imgNames,
  titleName,
  inteltypeName,
  videoNames,
  merchantName,
  actionBtns,
  tabs,
  hasIsanon,
} = detailConfig
let { hasHot, comments, storeUp, thumbsUp, vip, virtualPay, discuss, hasChapter } = table
hasHot = hasHot == '详' || hasHot == '全'
let storeUpConfig = {
  storeUp,
  imgName,
  titleName,
  inteltypeName,
}
const route = useRoute()
const router = useRouter()
const { id, centerType } = route.query

// ----------------------------------
// ---------- 面包屑 ----------------
// ----------------------------------
const homePath = ref('/index/home')
const second = ref({
  path: centerType
    ? '/index/' + tableName +'?centerType=' + centerType
    : '/index/' + tableName,
  title: comments,
})
const lastTitle = computed(() => {
  return data.value[titleName] || '详情'
})
function backEvent(){
  router.back()
}
// ----------------------------------
// ------------ 列表 ----------------
// ----------------------------------
let detailColumns = ref(getDetailColumns())
function getDetailColumns() {
  let list = columns.filter(column => column.detail_type)

  // 前台访问，过滤审核字段
  if (!centerType) {
    let columnNameList_sfsh = ['sfsh', 'shhf']
    list = list.filter(column => !columnNameList_sfsh.includes(column.columnName))
  }


  list.sort((a, b) => b.detail_order - a.detail_order)
  return list
}

const hasThumbsUp = false

// ----------------------------------
// ---------- 标签页 ----------------
// ----------------------------------
const tabActiveName = ref(0)

// ----------------------------------
// ----------- 热门信息 --------------
// ----------------------------------
const hotData = ref({
  comments: '热门信息',
  tableName,
  subtitle: 'Hot',
  list: [],
})
getHotList()
async function getHotList() {
  if (!hasHot) {
    return
  }
  let apiParams = {
    limit: 4,
  }
  let res = await getAutoSort1API(tableName, apiParams)
  hotData.value.list = getUiList(detailConfig, res.data.list)
}

// ----------------------------------
// ---------- 弹框 ---------------
// ----------------------------------
const dialogVisible = ref(false)
const dialogTitle = ref('弹框标题')
const dialogComponent = shallowRef(null)
const dialogClass = ref('')
let dialogData = {}
function openDialog(data) {
  dialogTitle.value = data.dialogTitle
  dialogComponent.value = data.dialogComponent
  dialogData = data.dialogData
  dialogClass.value = data.dialogClass

  dialogVisible.value = true
}

// ----------------------------------
// ---------- 操作按钮 ---------------
// ----------------------------------
// 鉴权
let actionButtons = ref(actionBtns.filter(item => isAuth(tableName, item.name, !centerType)))
const actionEventMap = {
  remove,
  edit,
  sfsh,
}
const actionEvent = button => {
  let { key, type, title } = button

  // [1] 优先判断是否 跨表功能的按钮
  if (type === 'crossTable') {
    return crossTableHander(button, data.value)
  }

  // [2] 根据key执行对应方法
  if (actionEventMap[key]) {
    return actionEventMap[key](button, data.value)
  }

  ElMessage.info(`【${title}】该功能暂时未添加`)
}

// ----------------------------------
// ------------ 删除 ----------------
// ----------------------------------
// 删除单个
function remove(button, row) {
  ElMessageBox.confirm('确认删除?', '提示', {
    confirmButtonText: '删除',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      let ids = [row.id]
      await deleteAPI(tableName, ids)
      ElMessage.success('删除成功')
      toListPage(tableName, centerType)
    })
    .catch(() => {})
}

// ----------------------------------
// ---------- 编辑 ---------------
// ----------------------------------
function edit(button, row) {
  dialogTitle.value = button.title
  dialogClass.value = ''
  dialogComponent.value = ListEdit
  dialogData = {
    type: 'update',
    id: row.id,
    tableName,
    row,
    okText: '提交',
    cancleText: '取消',
  }
  dialogVisible.value = true
}

// ----------------------------------
// ---------- 跨表功能 ---------------
// ----------------------------------
async function crossTableHander(button, row) {
  let { name, crossType } = button
  let tableConfig = tableConfigs[tableName]
  let { table, columns } = tableConfig
  let { sfsh, hasPay, isReverse, virtualPay } = table
  let index = table.crossOptButton.findIndex(buttonName => buttonName === name)
  // 跨表的关联数据
  // 审核权限
  let crossOptAudit = table.crossOptAudit[index]
  // 支付权限
  let crossOptPay = table.crossOptPay[index]
  // 已经存在的提示
  let tips = table.crossOptButtonTips[index]
  // 关联columnName列名：比如reservationstate。
  let statusColumnName = table.crossOptButtonStatusColumns[index]
  // 库表的新表
  let newTableName = table.crossOptTableName[index]

  // [1] 退出条件判断
  if (row.reservationstate == '已取消') {
    ElMessage.error('该预约已取消，不能再操作')
    return
  }  

  // 1.1 已开启审核功能，且未审核状态下
  if (sfsh == '是' && crossOptAudit === '是' && row.sfsh != '是') {
    ElMessage.info('请审核通过后再操作')
    return
  }


  // 1.3 倒计时
  if (isReverse == '是' && virtualPay != '是') {
    if (dayjs().isAfter(dayjs(row.reversetime))) {
      ElMessage.info('倒计时已结束')
      return
    }
  }


  // 1.5 statusColumnName 次数/状态限制 字段，
  // 1.5.1 状态限制 reservationstate
  // 1.5.2 次数限制 [1]
  let statusColumnValue
  let isLimit = false
  if (statusColumnName) {
    if (statusColumnName.startsWith('[')) {
      isLimit = true
      // 限制次数   从 [1] 提取 次数 1
      let limitNum = statusColumnName.replace(/\[|\]/g, '')
      limitNum = Number(limitNum)

      // 查询当前次数
      let params = {
        crossrefid: row.id,
        crossuserid: Number(localStorage.getItem('userid')),
      }
      let res = await getPageAPI(newTableName, params)

      if (res.data.total >= limitNum) {
        ElMessage.error(tips)
        return
      }
    } else {
      // 状态限制
      let { customize } = columns.find(column => column.columnName === statusColumnName)
      // 关联的字段，约定是单选类型、且取的是选项一的值 customize: '已取消,已预约'-> statusColumnValue: '已取消'
      statusColumnValue = customize.split(',')[0]
      if (row[statusColumnName] === statusColumnValue) {
        ElMessage.success(tips)
        return
      }
    }
  }

  switch (crossType) {
    case 1:
      // 弹出编辑框
      dialogTitle.value = button.title
      dialogClass.value = ''
      dialogComponent.value = ListEdit
      dialogData = {
        type: 'cross',
        id: row.id,
        tableName: newTableName,
        crossData: {
          crossType,
          isLimit,
          statusColumnName,
          statusColumnValue,
          oldRow: row,
          oldTableName: tableName,
          newTableName,
          okText: '提交',
          cancleText: '取消',
        },
      }

      dialogVisible.value = true
      break


    case 3:
      // 弹出座位和日期选择
      dialogTitle.value = '预约'
      dialogClass.value = null
      dialogComponent.value = SeatReservation
      dialogData = {
        type: 'cross',
        id: row.id,
        tableName: newTableName,
        button,
        crossData: {
          crossType,
          statusColumnName,
          statusColumnValue,
          oldRow: row,
          oldTableName: tableName,
          newTableName,
        },
      }

      dialogVisible.value = true

      break
   }
}


// ----------------------------------
// ---------- 审核功能 ---------------
// ----------------------------------
// 审核
function sfsh(button, row) {
  if (row.sfsh == '是' || row.sfsh == '否') {
    ElMessage.error('已审核了')
    return
  }

  dialogClass.value = ''
  dialogComponent.value = Review
  dialogTitle.value = '审核'
  dialogData = {
    datas: [row],
    isSHMode: true,
    columns,
    comments,
  }
  dialogVisible.value = true
}





// ----------------------------------
// ----------- 图片 --------------
// ----------------------------------
const banners = ref([])
const showBanner = ref(false)
function getPicture() {
  if (!imgName) return
  let list = []
  imgNames.forEach(columnName => {
    let urls = getFilePaths(data.value[columnName])
    urls.forEach(picture => {
      list.push({
        id: picture,
        picture,
        columnName
      })
    })
  })
  banners.value = list
  showBanner.value = true
}
let previewSrc = []
const showPreview = ref(false)
let initialIndex = 0
function bannerEvent(item) {
  initialIndex = banners.value.findIndex(banner => banner.id == item.id)
  previewSrc = banners.value.map(banner => banner.picture)
  showPreview.value = true
}

// ----------------------------------
// ----------- 视频 --------------
// ----------------------------------
const videoList = ref([])
function getVideo() {
  let list = []
  let errorMsg = '请先开通会员'
  let userForm = JSON.parse(localStorage.getItem('userForm'))

  let videoColumns = columns.filter(column => column.type == '视')
  videoColumns.forEach(column => {
    let { columnName, formatValidation } = column

    let canPlay = true
    if (formatValidation == '会员') {

      // 未登录
      if (!userForm) {
        canPlay = false
      }

      // 非会员 + 自己的 + 带支付
      if (canPlay && userForm.vip != '是' && centerType && data.value.ispay == '未支付') {
        canPlay = false
      }

      // 非会员 + 不是自己的
      if (canPlay && userForm.vip != '是' && !centerType) {
        canPlay = false
      }

    }
    let srcList = getFilePaths(data.value[columnName])
    let list2 = srcList.map(src => {
      return {
        src,
        canPlay,
        errorMsg,
      }
    })
    list = list.concat(list2)
  })

  videoList.value = list
}



// ----------------------------------
// ----------- 数据 -----------------
// ----------------------------------
const data = ref({})
fetchData()
async function fetchData() {
  let res = await getDetailAPI(tableName, id)
  data.value = res.data

  // 图片
  getPicture()

  // 视频
  getVideo()



}

function detailEvent(tableName, id) {
  toDetailPage(tableName, id, centerType)
}

function moreEvent(tableName) {
  toListPage(tableName, centerType)
}

provide('detail', {
  id,
  
  homePath,
  second,
  lastTitle,
  backEvent,

  banners,
  showBanner,
  bannerEvent,

  data,
  detailColumns,
  tableName,
  storeUpConfig,
  merchantName,

  payButtons: ref([]),
  payLoading: ref(false),
  hasBuyInput: ref(false),
  buynumber: ref(1),
  shopEvent: ()=>{},  
  actionButtons,
  actionEvent,

  hasThumbsUp,  

  videoList,

  tabs,
  tabActiveName,
  centerType,
  table,

  hasHot,
  hotData,
  detailEvent,
  moreEvent,
})

// 确保 BreadCrumb能复用 后台的
provide('header', {
  homePath,
  second,
  lastTitle,
  backEvent,

  banners,
  showBanner,
  bannerEvent,
})
// 确保 hot能复用 首页的
provide('start', {
  detailEvent,
  moreEvent,
})
</script>

<template>
  <div class="detail-page" :data-key="tableName">
      <div class="detail-page-inner">
    <BreadCrumb />
    <div class="p-d-wrapper">
      <Pictures class="pictures-box" v-if="showBanner" />
      <div class="detail-box">
        <DetailList />
        <Buttons />
      </div>
    </div>
    <ThumbsUp v-if="hasThumbsUp" />
    <Videos />
    <Tabs />

    <Custom />
  </div>

  <Hot v-if="hasHot" :data="hotData" />
    
    <!-- 弹框公用 -->
    <el-dialog
      class="yy-dialog"
      v-model="dialogVisible"
      :title="dialogTitle"
      destroy-on-close
      :close-on-click-modal="false"
    >
      <component
        v-model="dialogVisible"
        :is="dialogComponent"
        :tableName="tableName"
        :data="dialogData"
        :class="dialogClass"
        @fetchData="fetchData"
        @openDialog="openDialog"
      />
    </el-dialog>

    <!-- 图片预览 -->
    <el-image-viewer
      v-if="showPreview"
      :url-list="previewSrc"
      show-progress
      :initial-index="initialIndex"
      @close="showPreview = false"
      :teleported="true"
    />


  </div>
</template>
