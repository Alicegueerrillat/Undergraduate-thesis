
<script setup>
/**
 * @description 选项卡
 *
 * tabs 数据列表
 * item.label 名称
 * item.name 组件名称 有值: 点击后当前页面显示的 为空: 要跳转到其它页面的
 * item.tableName 表名
 *
 * tabEvent tab点击事件
 */
import { inject, ref } from 'vue'

let { tabs, tabEvent } = inject('center')

const tabActiveName = ref(0)
</script>

<template>
  <div class="centertab">
    <div class="subtitle">我的功能</div>
    <el-tabs v-model="tabActiveName" type="border-card" @tab-click="tabEvent">
      <el-tab-pane
        v-for="(item, index) in tabs"
        :label="item.label"
        :name="index"
        :key="item.label"
      >
        <component v-if="item.name && tabActiveName === index" :is="item.name" />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<style>
.centertab {
 
 --el-color-primary: var(--btn-bg-color-);
  padding-top: 20px;
}
</style>  
  