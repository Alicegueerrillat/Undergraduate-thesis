<script setup>
import { ref, watchEffect } from 'vue'
import { useRoute } from 'vue-router'

import CenterTitle from '@/views/center/CenterTitle.vue'
import ListSearch from '@/views/list/ListSearch.vue'

import { deleteAPI, getDetailAPI, getListAPI, updateAPI } from '@/api/list'
import { getFirstFilePath } from '@/utils/getFilePath'
import { toDetailPage } from '@/utils/navigate'

const route = useRoute()
let query = route.query

// ----------------------------------
// ------------ 标题 ----------------
// ----------------------------------
let title = getTitle(query.type)
function getTitle(type) {
  let titleMap = {
    1: '我的收藏',
  }
  return titleMap[type] || ''
}
const tableName = 'storeup'

const searchColumns = [
  {
    columnName: 'name',
    comments: '名称',
    placeholder: '',
    form_type: 'YyText',
    options: [],
  },
]
const isLoading = ref(false)
// ----------------------------------
// ------------ 分页 ----------------
// ----------------------------------
const hideOnSinglepage = false
const pageSizes = [8, 20, 50, 100]
const layout = 'total, sizes, prev, pager, next, jumper'
const currentPage = ref(1)
const pageSize = ref(pageSizes[0])
const total = ref(8)

// ----------------------------------
// ---------- 搜索配置 ---------------
// ----------------------------------
const searchData = ref({})
const searchEvent = data => {
  searchData.value = data
}

// ----------------------------------
// ----------- 拉取数据 --------------
// ----------------------------------
const list = ref([])
async function fetchData() {
  isLoading.value = true
  try {
    let params = {
      userid: Number(localStorage.getItem('userid')),
      // 搜索
      ...searchData.value,
      type: query.type,
      // 分页
      limit: pageSize.value,
      page: currentPage.value,
    }
    let res = await getListAPI(tableName, params)
    res.data.list.forEach(item => {
      item.picture = getFirstFilePath(item.picture)
    })
    list.value = res.data.list || []
    total.value = res.data.total
  } catch (error) {}
  isLoading.value = false
}
watchEffect(() => {
  fetchData()
})

function removeEvent(item) {
  ElMessageBox.confirm('确认删除?', '提示', {
    confirmButtonText: '删除',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      let ids = [item.id]
      await deleteAPI(tableName, ids)
      if (query.type == 1) {
        getDetailAPI(item.tablename, item.refid).then(res => {
          let data = res.data
          data.storeupnum = Math.max(data.storeupnum - 1, 0)
          updateAPI(item.tablename, data)
        })
      }
      ElMessage.success('删除成功')

      fetchData()
    })
    .catch(() => {})
}
</script>

<template>
  <div class="storeup-page">
    <!-- 标题 -->
    <div class="storeupheader-wrapper">
      <CenterTitle :title="title" />
    </div>

    <!-- 搜索表单 -->
    <ListSearch :searchColumns="searchColumns" :hasAddBtn="false" @search="searchEvent" />

    <div class="storeup-view" v-loading="isLoading">
      <el-card
        class="item"
        v-for="item in list"
        :key="item.id"
        @click="toDetailPage(item.tablename, item.refid)"
      >
        <template #header>
          <div class="card-header">
            <span>{{ item.name }}</span>
          </div>
        </template>
        <img v-if="item.picture" :src="item.picture" class="img" />
        <template #footer>
          <el-button size="small" @click.stop="removeEvent(item)">删除</el-button>
        </template>
      </el-card>
    </div>

    <!-- 分页 -->
    <div class="pagination-wrapper">
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="pageSizes"
        :total="total"
        :background="true"
        :layout="layout"
        :hide-on-single-page="hideOnSinglepage"
      />
    </div>
  </div>
</template>
<style>
.storeup-page {
  max-width: 1080px;
  margin: 0 auto;
}
.storeup-view {
  width: 100%;
  height: auto;
  padding: 0 0 20px 0;
  margin: 0;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: flex-start;
  gap: 20px;
  .item {
    flex: 0 0 auto;
    width: calc(25% - 15px);
    cursor: pointer;
    .img {
      width: 100%;
      height: 200px;
    }
  }
}
</style>