#评分功能
<script setup>
// 注册表单组件
import '@/components/FormItem/index.js'

import DiscussView from '@/views/detail/DiscussView.vue'

import { reactive, ref, watchEffect, inject } from 'vue'
import { addAPI, getListAPI, updateAPI, deleteAPI, getListsAPI, getDetailAPI } from '@/api/list'
import { getFirstFilePath } from '@/utils/getFilePath'
import { getNanoId } from '@/utils'
import dayjs from 'dayjs'

defineOptions({
  inheritAttrs: false,
})

const { data } = defineProps(['data'])
let { tableName, table, id } = inject('detail')
let { virtualPay, discuss } = table

let hasScore = discuss == '评分' || discuss == '评分审'
let hasSfsh = discuss == '评审' || discuss == '评分审'
const tableName_discuss = 'discuss' + tableName
let userid = Number(localStorage.getItem('userid'))
const isLoading = ref(false)
const columns = [
  {
    columnName: 'content',
    comments: '评论',
    form_type: 'YyQuill',
  },
]
const ruleForm = ref({
  content: '',
})
const rules = reactive({
  content: [
    {
      required: true,
      message: '请输入评论',
    },
  ],
})
// 表单实例
const ruleFormRef = ref()


async function okEvent() {
  isLoading.value = true

  // 表单校检逻辑
  let valid = await ruleFormRef.value.validate((valid, fields) => {
    if (!valid) {
      // 验证不通过，提示第一个错误
      let firstErrorField = Object.entries(fields)
      let firstErrorMessage = firstErrorField[0][1][0].message || '表单校验失败，请检查输入'
      ElMessage.error(firstErrorMessage)
    }
  })
  if (!valid) {
    isLoading.value = false
    return
  }

  try {



    let content = ruleForm.value.content
    let discussData = {
      content,
      refid: id,
      userid,
      avatarurl: localStorage.getItem('useravatar'),
      nickname: localStorage.getItem('username'),
    }


    await addAPI(tableName_discuss, discussData)
    updateDetail()
    getDiscussList()

    ElMessage.success(hasSfsh ? '评论成功,等待审核。' : '评论成功')
    ruleForm.value.content = ''
  } catch (error) {
    let msg = error.message || error.msg || ''
    ElMessage.error(msg)    
  }

  isLoading.value = false
}


// 更新详情数据
async function updateDetail() {
  let res = await getListAPI(tableName_discuss, {
    refid: id,
    page: 1,
    limit: 99999,
  })

  let len = res.data.list.length || 0
  // 评论数
  data.discussnum = len


  await updateAPI(tableName, data)
}

// ----------------------------------
// ------------ 分页 ----------------
// ----------------------------------
const hideOnSinglepage = false
const pageSizes = [8, 20, 50, 100]
const layout = 'total, sizes, prev, pager, next, jumper'
const currentPage = ref(1)
const pageSize = ref(pageSizes[0])
const total = ref(0)

// ----------------------------------
// ------------ 评论列表 -------------
// ----------------------------------
const discussList = ref([])
const discussLoading = ref(false)
// 拉取评论列表
async function getDiscussList() {
  discussLoading.value = true
  try {
    let params = {
      // 分页
      limit: pageSize.value,
      page: currentPage.value,
      refid: id,
      sort: 'istop',
			order: 'desc',
    }
    if(hasSfsh) {
      params.sfsh = '是'
    }
    let res = await getListAPI(tableName_discuss, params)

    res.data.list.forEach(item => {
      item.img = getFirstFilePath(item.avatarurl)

      item.tuserids = item.tuserids ? item.tuserids.split(',').map(i => Number(i)) : []
      item.cuserids = item.cuserids ? item.cuserids.split(',').map(i => Number(i)) : []

      item.hasRemove = item.userid === userid
      item.hasThumbsup = item.tuserids.includes(userid)
      item.hasCrazily = item.cuserids.includes(userid)

      // 子评论
      let replyList = []
      if (item.reply) {
        replyList.push({
          id: 'reply',
          content: item.reply,
          hasRemove: false,
        })
      }
      // try {
      //   replyList = JSON.parse(item.reply) || []
      // } catch (error) {}
      // replyList.forEach(i => {
      //   i.img = getFirstFilePath(i.avatarurl)
      //   i.hasRemove = i.userid === userid
      // })

      item.replyList = replyList

    })
    discussList.value = res.data.list
    total.value = res.data.total
  } catch (error) {}
  discussLoading.value = false
}
watchEffect(() => {
  getDiscussList()
})
// 赞
function handleThumbsup(item) {
  if (item.hasThumbsup) {
    // 取消赞
    item.thumbsupnum -= 1
    item.hasThumbsup = false
    item.tuserids = item.tuserids.filter(userid => userid !== userid)
  } else {
    // 点赞
    item.thumbsupnum += 1
    item.hasThumbsup = true
    item.tuserids.push(userid)
  }

  let copyItem = JSON.parse(JSON.stringify(item))
  copyItem.tuserids = copyItem.tuserids.join(',')
  copyItem.cuserids = copyItem.cuserids.join(',')
  delete copyItem.img
  delete copyItem.hasRemove
  delete copyItem.hasThumbsup
  delete copyItem.hasCrazily
  delete copyItem.replyList

  updateAPI(tableName_discuss, copyItem)
}
// 踩
function handleCrazily(item) {
  if (item.hasCrazily) {
    // 取消踩
    item.crazilynum -= 1
    item.hasCrazily = false
    item.cuserids = item.cuserids.filter(userid => userid !== userid)
  } else {
    // 踩
    item.crazilynum += 1
    item.hasCrazily = true
    item.cuserids.push(userid)
  }

  let copyItem = JSON.parse(JSON.stringify(item))
  copyItem.tuserids = copyItem.tuserids.join(',')
  copyItem.cuserids = copyItem.cuserids.join(',')
  delete copyItem.img
  delete copyItem.hasRemove
  delete copyItem.hasThumbsup
  delete copyItem.hasCrazily
  delete copyItem.replyList

  updateAPI(tableName_discuss, copyItem)
}
async function handlerRemove(item) {
  ElMessageBox.confirm('确认删除?', '提示', {
    confirmButtonText: '删除',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      let ids = [item.id]
      await deleteAPI(tableName_discuss, ids)
      ElMessage.success('删除成功')
      updateDetail()
      getDiscussList()
    })
    .catch(() => {})
}

async function showReplyDialog(item) {


  ElMessageBox.prompt('请输入回复内容', '回复: ' + item.nickname, {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    inputPattern: /.+/,
    inputErrorMessage: '请输入回复内容',
    inputType: 'textarea',
  })
    .then(({ value }) => {
      replyEvent(item.id, value)
    })
    .catch(() => {})
}

async function replyEvent(id, content) {
  let { data } = await getDetailAPI(tableName_discuss, id)
  let { reply } = data
  let replyData = {
    id: getNanoId(),
    userid,
    avatarurl: localStorage.getItem('useravatar'),
    nickname: localStorage.getItem('username'),
    content,
    addtime: dayjs().format('YYYY-MM-DD HH:mm:ss'),
  }

  // reply是JSON字符串，解析为对象
  let replyList = []
  try {
    replyList = JSON.parse(reply) || []
  } catch (error) {}

  replyList.push(replyData)
  data.reply = JSON.stringify(replyList)

  await updateAPI(tableName_discuss, data)
  getDiscussList()
}

// 删除评论的回复
async function removeChildEvent(id, childId) {
  ElMessageBox.confirm('确认删除?', '提示', {
    confirmButtonText: '删除',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      let { data } = await getDetailAPI(tableName_discuss, id)
      let { reply } = data
      let replyList = []
      try {
        replyList = JSON.parse(reply) || []
      } catch (error) {}
      let index = replyList.findIndex(i => i.id == childId)
      if (index != -1) {
        replyList.splice(index, 1)
      }
      data.reply = JSON.stringify(replyList)
      await updateAPI(tableName_discuss, data)
      getDiscussList()
    })
    .catch(() => {})
}

</script>

<template>
  <div class="discuss-tab">
    <!-- 输入表单 -->
    <div class="form-wrapper">
      <el-form
        class="editform discussform"
        :model="ruleForm"
        :rules="rules"
        ref="ruleFormRef"
        @submit.prevent
      >
        <el-form-item v-for="column in columns" :key="column.columnName" :prop="column.columnName">
          <component
            :is="column.form_type"
            :columns="columns"
            :column="column"
            :ruleForm="ruleForm"
            :tableName="tableName"
          />
        </el-form-item>


        <div class="btn-wrapper">
          <div class="submit-box">
            <el-button :loading="isLoading" class="submit-btn" @click="okEvent">提交</el-button>
          </div>
          <div class="cancel-box">
            <el-button class="cancel-btn" @click="ruleForm.content = ''">重置</el-button>
          </div>
        </div>
      </el-form>
    </div>

    <!-- 评论列表 -->
    <div class="comments-wrapper" v-loading="discussLoading">
      <DiscussView
        :list="discussList"
        :hasScore="hasScore"
        @thumbsup="handleThumbsup"
        @crazily="handleCrazily"
        @remove="handlerRemove"
        @reply="showReplyDialog"
        @removeChild="removeChildEvent"        
      />
    </div>

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

<style lang="scss">
.discussform {
  .ql-container {
    height: 300px;
  }
}
</style>
