
<script setup>
import { inject } from 'vue'

/**
 * @description 列表的搜索
 * @param { Array } columns 表单项列表
 * @param { Object } ruleForm 表单数据
 * @param { Function } searchEvent 搜索事件
 *
 * @param { Array } headerButtons 按钮列表
 * @param { Function } headerButtonEvent 按钮事件
 */
let { columns, ruleForm, searchEvent, headerButtons, headerButtonEvent } = inject('listFront')
</script>

<template>
  <div class="search-wrapper">
    <el-form class="searchform" :inline="true" :model="ruleForm" @submit.prevent="searchEvent">
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
        <el-button v-for="item in headerButtons" :key="item.key" @click="headerButtonEvent(item)">
          {{ item.title }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

  