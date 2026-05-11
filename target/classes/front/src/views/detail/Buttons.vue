
<script setup>
/**
 * @description 按钮
 *
 * payButtons 购物按钮列表
 * hasBuyInput 是否有数量输入框
 * buynumber 数量
 * payLoading 购物按钮加载中
 * shopEvent 购物按钮事件
 *
 * actionButtons 操作按钮列表
 * actionEvent 操作按钮事件
 */
import { inject } from 'vue'

let { payButtons, payLoading, hasBuyInput, buynumber, shopEvent, actionButtons, actionEvent } =
  inject('detail')
</script>
<template>
  <div class="detailbutton-wrapper">
    <!-- 购物按钮 -->
    <div class="btn-list" v-loading="payLoading">
      <el-input-number v-if="hasBuyInput" v-model="buynumber" :min="1" :step="1" :precision="0" />
      <el-button
        v-for="item in payButtons"
        :key="item.key"
        :data-key="item.key"
        :icon="item.iconName"
        @click="shopEvent(item.key)"
      >
        {{ item.title }}
      </el-button>
    </div>

    <!-- 操作按钮 -->
    <div class="btn-list" v-if="actionButtons.length">
      <el-button
        v-for="item in actionButtons"
        :key="item.key"
        :data-key="item.key"
        :icon="item.iconName"
        @click="actionEvent(item)"
      >
        {{ item.title }}
      </el-button>
    </div>
  </div>
</template>
<style>
.detailbutton-wrapper {
  .btn-list {
    display: flex;
    flex-wrap: wrap;
    padding-top: 10px;
    gap: 8px;
  }
  .el-button + .el-button {
    margin-left: 0;
  }
}
</style>
  
