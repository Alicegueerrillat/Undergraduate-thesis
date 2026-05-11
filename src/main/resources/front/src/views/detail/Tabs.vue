
<script setup>
/**
 * @description 商品详情
 *
 * tabs 标签页列表
 * tabActiveName 当前激活标签页
 */
import { inject } from 'vue'

let { tabs, tabActiveName, data } = inject('detail')
</script>

<template>
  <div class="detailtab-view" v-if="tabs.length">
    <el-tabs v-model="tabActiveName" class="detail-tabs">
      <el-tab-pane
        v-for="(item, index) in tabs"
        :label="item.label"
        :name="index"
        :key="item.label"
      >
        <component v-if="tabActiveName === index" :is="item.name" :config="item" :data="data" />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<style>
.detailtab-view {
  width: 100%;
  --el-color-primary: var(--btn-bg-color-);
}
</style>

  