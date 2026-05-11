<script setup>
import '@/style/list.scss'
import '@/components/FormItem/index.js'

import { computed, shallowRef, ref, watchEffect, reactive, provide, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'

import BreadCrumb from './BreadCrumb.vue'
import Category from './Category.vue'
import ContentNormal from './Content.vue'
import ContentNews from './ContentNews.vue'
import ContentForum from './ContentForum.vue'
import Custom from './Custom.vue'
import Hot from './Hot.vue'
import Pagination from './Pagination.vue'
import Search from './Search.vue'
import Sort from './Sort.vue'

import ListEdit from './ListEdit.vue'
import ChartEntry from '@/components/Echart/index.vue'

import tableConfigs from '@/utils/tableConfigs'
import { getOptionAPI, getRemindAPI } from '@/api/common'
import { 
  getListAPI, 
  getPageAPI, 
  predictAPI, 
  predictImgAPI,
  } from '@/api/list'
import { getAutoSort1API } from '@/api/front'
import { toDetailPage, toListPage } from '@/utils/navigate'
import getUiList from '@/utils/getUiList'
import { isAuth } from '@/utils/auth'
import getFilePath from '@/utils/getFilePath'
import { utils, writeFileXLSX } from 'xlsx'
import { downloadFile } from '@/utils'
import chartData from '@/components/Echart/chartData'

let { tableName } = defineProps(['tableName'])
let tableConfig = tableConfigs[tableName]
let { table, columns, searchColumns, detailConfig, headerButtons } = tableConfig
let { hasHot, comments } = table
hasHot = hasHot == '列' || hasHot == '全'
const hasOnshelves = columns.some(column => column.columnName === 'onshelves') // 上下架

const route = useRoute()
const router = useRouter()
const query = route.query
const centerType = query.centerType // 后台权限

// 内容列表
let Content = ContentNormal
switch (tableName) {


  default:
    Content = ContentNormal
    break;
}
// ----------------------------------
// ---------- 面包屑 ----------------
// ----------------------------------
const homePath = ref('/index/home')
const second = ref({
  path: null,
  title: null,
})
const lastTitle = ref(comments)
function backEvent(){
  router.back()
}

// ----------------------------------
// ---------- 搜索配置 ---------------
// ----------------------------------
// 搜索参数
const searchData = ref({})
// 表单项
const columns_search = ref(getSearchColumns())
function getSearchColumns() {
  let list = searchColumns.slice(0)

  // 前台不显示 是否审核搜索
  if (table.sfsh == '是' && !centerType) {
    list = list.filter(column => column.columnName != 'sfsh')
  }

  // 不同角色展示不同的搜索字段
  let role = localStorage.getItem('role')
  list = list.filter(column => {
    let { queryFlagInput = ['是'] } = column
    return queryFlagInput.includes('是') || queryFlagInput.includes(role)
  })

  return list
}

// 表单数据
const ruleForm = reactive(getRuleForm())
function getRuleForm() {
  let data = {}
  columns_search.value.forEach(column => {
    let { columnName } = column
    data[columnName] = ''
  })

  return data
}

// 搜索事件
function searchEvent() {
  let data = {}

  columns_search.value.forEach(column => {
    let { columnName, form_type } = column

    // 无值直接返回
    if (ruleForm[columnName] === '' || ruleForm[columnName] === undefined) {
      return
    }

    // 区间的--> 精准搜索，不带%
    if (column.isRangeSearch) {
      data[columnName] = ruleForm[columnName]
      return
    }

    // 根据form_type的值做一次转换
    switch (form_type) {
      // 文本框 --> 模糊搜索
      case 'YyText':
        data[columnName] = '%' + ruleForm[columnName] + '%'
        break

      // 多选
      case 'YyMultilSelect':
        data[columnName] = ruleForm[columnName]
          .split(',')
          .map(value => '%' + value + '%')
          .join(',')
        break

      default:
        data[columnName] = ruleForm[columnName]
        break
    }
  })

  searchData.value = data
}

// 按钮列表
let headerButtons_auth = headerButtons.filter(button => button.isPublic || isAuth(tableName, button.name, !centerType))
let headerEventMap = {
  add,
  exportExcel,
}
function headerButtonEvent(button) {
  let { key, title, type } = button

  if (type === 'statis') {
    // 其它表的统计
    return showChart(button)
  }

  // 根据key执行对应的key事件
  if (headerEventMap[key]) {
    return headerEventMap[key](button)
  }

  ElMessage.info(`【${title}】该功能暂时未添加`)
}

function add(button) {
  dialogTitle.value = button.title
  dialogComponent.value = ListEdit
  dialogClass.value = ''
  dialogData = {
    type: 'add', // add: 新增 update: 编辑 cross: 跨表
    id: '',
    tableName,
    defaultData: {
      ...route.params,
      ...route.query,
    },    
    okText: '提交',
    cancleText: '取消',
  }
  dialogVisible.value = true
}


// ----------------------------------
// ---------- 导出功能 ---------------
// ----------------------------------
function exportExcel() {

  // 一个sheet表数据
  let wsData = []

  // 第一行表头
  let headerRow = columns.map(column => column.comments)
  wsData.push(headerRow)

  // 其余行数据
  listData.value.sourceList.forEach(item => {
    let row = columns.map(column => item[column.columnName])

    wsData.push(row)
  })

  const wb = utils.book_new()
  const ws = utils.aoa_to_sheet(wsData)
  utils.book_append_sheet(wb, ws, 'Sheet1')
  writeFileXLSX(wb, tableName + '.xlsx')

  ElMessage.success('导出成功')
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
// ------------ 分页 ----------------
// ----------------------------------
const currentPage = ref(1)
const pageSize = ref(8)
const total = ref(0)

// ----------------------------------
// ------------ 分类 ----------------
// ----------------------------------
const list_category = ref([])
const act_category = ref('')
const hasCategory = ref(false)
let categoryConfig = {
  refTable: '',
  refColumn: '',
  columnName: '',
}

initCategory()
// 初始化分类配置
function initCategory() {
  columns.forEach(column => {
    let { categoryFlag, refTable, refColumn, columnName } = column
    if (categoryFlag == '是' && refTable && refColumn) {
      hasCategory.value = true
      categoryConfig.refTable = refTable
      categoryConfig.refColumn = refColumn
      categoryConfig.columnName = columnName

      // 路由query是否有分类字段
      if (query && query[columnName]) {
        act_category.value = query[columnName]
      }
    }
  })
}
getCategoryList()
// 获取分类数据
async function getCategoryList() {
  let { refTable, refColumn } = categoryConfig
  if (!hasCategory.value) {
    return
  }
  let res = await getOptionAPI(refTable, refColumn)
  list_category.value = res.data
}
function categoryEvent(item) {
  act_category.value = item
}

// ----------------------------------
// ------------ 排序 ----------------
// ----------------------------------
const list_sort = ref(detailConfig.sortList || [])
const actItem_sort = ref({
  sort: '',
  order: '',
})
function sortEvent(item) {
  let newOrder = ''
  let { sort, order } = item
  if (actItem_sort.value.sort !== sort) {
    // 切换了排序的sort，默认从升序开始
    newOrder = 'asc'
  } else {
    // 同一sort，但order需要切换，升序asc  -> 降序desc -> 不排序
    switch (order) {
      case 'asc':
        newOrder = 'desc'
        break

      case 'desc':
        newOrder = ''
        break

      default:
        newOrder = 'asc'
        break
    }
  }

  // 更新sortData
  if (!newOrder) {
    actItem_sort.value = {}
  } else {
    actItem_sort.value = {
      sort,
      order: newOrder,
    }
  }

  // 更新sortList的order字段
  list_sort.value.forEach(item => {
    item.order = item.sort === sort ? newOrder : ''
  })
}

// 合并两个sort
let sortData = computed(() => {
  let { sortName, sortOrder } = detailConfig // 初始配置的
  let { sort, order } = actItem_sort.value // 用户点击的

  if (!sortName && !sort) {
    return {}
  }
  let newSorts = [],
    newOrder = []
  // 高权重
  if (sort) {
    newSorts.push(sort)
    newOrder.push(order)
  }
  // 低权重
  if (sortName) {
    newSorts.push(sortName)
    newOrder.push(sortOrder)
  }
  return {
    sort: newSorts.join(','),
    order: newOrder.join(','),
  }
})

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
// ---------- 图表功能 ---------------
// ----------------------------------
function showChart(button) {
  let { title } = button
  dialogData = chartData.find(item => item.tableName === tableName && item.title == title)
  dialogData = {
    ...dialogData,
    title: ''
  }

  dialogClass.value = 'list-chart-wrapper'
  dialogComponent.value = ChartEntry
  dialogTitle.value = title
  dialogVisible.value = true
}

// ----------------------------------
// ----------- 拉取数据 --------------
// ----------------------------------
const isLoading = ref(false)
const listData = ref({
  comments,
  tableName,
  subtitle: tableName,
  list: [],
  sourceList: [],
})
async function fetchData(fetchParams) {
  isLoading.value = true
  try {
    let apiParams = {
      // 搜索
      ...searchData.value,

      // 排序
      ...sortData.value,

      // 分页
      limit: pageSize.value,
      page: currentPage.value,

      ...fetchParams,
    }

    // 上下架 + 前台
    if (hasOnshelves && !centerType) {
      apiParams.onshelves = 1
    }

    // 审核 + 前台
    if (table.sfsh == '是' && !centerType) {
      apiParams.sfsh = '是'
    }

    // 分类
    if (act_category.value) {
      apiParams[categoryConfig.columnName] = act_category.value
    }

    // 用权
    let searchNames = tableConfig.timeTable?.searchNames
    if (searchNames && searchNames.length) {
      let userForm = JSON.parse(localStorage.getItem('userForm'))
      if (userForm) {
        searchNames.forEach(name => {
          userForm[name] !== undefined && (apiParams[name] = userForm[name])
        })
      }
    }
    let api = query.centerType ? getPageAPI : getListAPI


    let res = await api(tableName, apiParams)
    listData.value.list = getUiList(detailConfig, res.data.list)
    listData.value.sourceList = res.data.list

    // 修改分页
    total.value = res.data.total
  } catch (error) {}

  isLoading.value = false
}
watchEffect(() => {
  fetchData()
})

function detailEvent(tableName, id) {
  toDetailPage(tableName, id, centerType)
}

function moreEvent(tableName) {
  toListPage(tableName, centerType)
}

provide('listFront', {
  homePath,
  second,
  lastTitle,
  backEvent,

  list_category,
  act_category,
  categoryEvent,

  columns: columns_search,
  ruleForm,
  searchEvent,
  headerButtons: headerButtons_auth,
  headerButtonEvent,

  list_sort,
  actItem_sort,
  sortEvent,

  currentPage,
  pageSize,
  total,

  listData,
  isLoading,

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
})
// 确保 list能复用 首页的
provide('start', {
  detailEvent,
  moreEvent,
})
provide('detail', {
  detailEvent,
  moreEvent,
})
</script>

<template>
  <div class="list-page" :data-key="tableName">
      <div class="list-page-inner">
    <BreadCrumb />
    <Search />
    <div class="c-c-wrapper">
      <div class="c-c-left" v-if="hasCategory">
        <Category />
      </div>

      <div class="c-c-right">
        <Sort />
        <Content :data="listData" :hideHeader="true" v-loading="isLoading" />
      </div>
    </div>
    <Pagination />

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
  </div>
</template>
