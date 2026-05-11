
<script setup>
/**
 * @description 分类
 * @param { Array } list_category 列表
 * @param { String } act_category 激活
 * @param { Function } categoryEvent 点击事件
 */
import { inject } from 'vue'
let { list_category, act_category, categoryEvent } = inject('listFront')
</script>

<template>
  <div class="category-view">
    <div class="item" :class="{ act: act_category == '' }" @click="categoryEvent('')">全部</div>
    <div
      v-for="item in list_category"
      :key="item"
      class="item"
      :class="{ act: act_category === item }"
      @click="categoryEvent(item)"
    >
      {{ item }}
    </div>
  </div>
</template>

<style>
.category-view {
  display: flex;
  flex-direction: column;

  gap: 10px;
  padding: 10px 25px;
  .item {
    cursor: pointer;
    border-radius: 6px;
    padding: 4px 10px;
    &:hover {
      color: #fff;
      background-color: #62d3a6;
    }
  }
  .act {
    color: #fff;
    background-color: #37a679;
    background: url(http://codegen.caihongy.cn/20251012/f9ca098dbc424351acf2949a051b72b9.jpg) no-repeat center / 100% 100%;
  }
}
</style>

  