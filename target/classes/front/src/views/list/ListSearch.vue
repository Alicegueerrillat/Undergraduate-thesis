<script setup>
/**
 * @description 列表的搜索
 */

import { reactive, ref } from 'vue'
// 注册表单组件
import '@/components/FormItem/index.js'
const { searchColumns, headerButtons_auth } = defineProps(['searchColumns', 'headerButtons_auth'])

const emits = defineEmits(['search', 'btnEvent'])

// 列字段
let columns = reactive(getRoleSearchColumns())
function getRoleSearchColumns() {
  let role = localStorage.getItem('role')
  return searchColumns.filter(column => {
    let { queryFlagInput = ['是'] } = column
    return queryFlagInput.includes('是') || queryFlagInput.includes(role)
  })
}

// 表单数据
let initRuleForm = {}
columns.forEach(column => {
  let { columnName } = column
  initRuleForm[columnName] = ''
})
let ruleForm = reactive(initRuleForm)

let toSearch = () => {
  let data = {}

  columns.forEach(column => {
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
  emits('search', data)
}
</script>

<template>
  <div class="search-wrapper">
    <el-form class="searchform" :inline="true" :model="ruleForm" @submit.prevent="toSearch">
      <el-form-item
        v-for="column in columns"
        :key="column.columnName"
        :prop="column.columnName"
        :label="column.comments"
      >
        <component
          :is="column.form_type"
          :column="column"
          :columns="columns"
          :ruleForm="ruleForm"
          :selectAllowClear="true"
        />
      </el-form-item>

      <div class="el-form-item searchbtn">
        <el-button native-type="submit" v-if="columns.length">查询</el-button>
        <el-button
          v-for="item in headerButtons_auth"
          :key="item.key"
          @click="emits('btnEvent', item)"
        >
          {{ item.title }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>
