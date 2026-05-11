<script setup>
/**
 * @description 首页
 */
import '@/style/start.scss'

import { onMounted, onUnmounted, ref, reactive, provide } from 'vue'

import AboutUs from './AboutUs.vue'
import Custom from './Custom.vue'
import FriendLink from './FriendLink.vue'
import List from './List.vue'
import OnlineMessage from './OnlineMessage.vue'
import Recommend from './Recommend.vue'
import SystemIntroduce from './SystemIntroduce.vue'
import News from './News.vue'

import { getDetailAPI, addAPI, getListAPI } from '@/api/list'
import { getFirstFilePath } from '@/utils/getFilePath'
import { toDetailPage, toListPage } from '@/utils/navigate'
import { getAutoSort1API, getAutoSort2API } from '@/api/front'
import getUiList from '@/utils/getUiList'

// ----------------------------------
// ------------ 列表 ----------------
// ----------------------------------
const listList = ref([
  {
    tableName: 'kechengxinxi',
    comments: '课程信息 列表',
    sfsh: '否',
    subtitle: 'kechengxinxi List',
    titleNames: ['kechengmingcheng'],
    titleHeads: [''],
    imgName: 'kechengfengmian',
    pubPeopleName: 'jiaoshizhanghao',
    sortName: '',
    sortOrder: 'desc',
    hasOnshelves: false,
    list: [],
  },
])
// 更新listList.list字段数据
getListList()
function getListList() {
  listList.value.forEach(async item => {
    // [1] 查询api参数
    let apiParams = {
      limit: 8,
    }
    // 参数: 排序
    if (item.sortName) {
      apiParams.sort = item.sortName
      apiParams.order = item.sortOrder
    }

    // 参数: 审核
    if (item.sfsh == '是') {
      apiParams.sfsh = '是'
    }

    if (item.hasOnshelves) {
      apiParams.onshelves = 1
    }
    
    let res = await getListAPI(item.tableName, apiParams)
    item.list = getUiList(item, res.data.list)
  })
}

// ----------------------------------
// ------------ 推荐 ----------------
// ----------------------------------
const recommendList = ref([
])
getRecommendList()
function getRecommendList() {
  recommendList.value.forEach(async item => {
    // [1] 查询api参数
    let apiParams = {
      limit: 5,
    }
    // 参数: 排序
    if (item.sortName) {
      apiParams.sort = item.sortName
      apiParams.order = item.sortOrder
    }

    // 智能推荐2 【推荐】 需要登录，如果没有登录，降级使用autoSort 接口（前台）
    let res
    if (item.isIntelRecom && localStorage.getItem('Token')) {
      try {
        res = await getAutoSort2API(item.tableName, apiParams)
      } catch (error) {
      }
    }
    if(!res || res.code !== 0){
      res = await getAutoSort1API(item.tableName, apiParams)
    }

    item.list = getUiList(item, res.data.list)
  })
}

const newsData = ref({})
let aboutUsData = ref({})

let systemIntroduceData = ref({})



onMounted(() => {
  setTimeout(() => {
    requestIdleCallback(() => {
      // 提前加载 列表页
      import('@/views/list/list.vue')
      import('@/views/detail/detail.vue')
    })
  }, 1000)
})

provide('start', {
  aboutUsData,
  systemIntroduceData,



  listList,
  recommendList,
  newsData,
  detailEvent: toDetailPage,
  moreEvent: toListPage,
})
</script>

<template>
  <div class="home-page">
    
<AboutUs :data="aboutUsData" />
<Recommend v-for="item in recommendList" :key="item.tableName" :data="item" />
<SystemIntroduce :data="systemIntroduceData" />
<List v-for="item in listList" :key="item.tableName" :data="item" />
<OnlineMessage />
<FriendLink />
<News :data="newsData" />
<Custom />
  
  </div>
</template>
