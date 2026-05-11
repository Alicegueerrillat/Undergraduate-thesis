
<script setup>
/**
 * @description 排序
 * @param { Array } list_sort 列表数据
 * item.sort 
 * item.order desc | asc
 * item.label 点赞 | 查看 | 价格 等等文本
 * 
 * @param { Object } actItem_sort 激活的项
 * actItem_sort.sort 对应 item.sort
 * actItem_sort.order 对应 item.order
 * 
 * @param { Function } sortEvent 点击排序事件
 * 

 */
import { inject } from 'vue'
let { list_sort, actItem_sort, sortEvent } = inject('listFront')
</script>

<template>
  <div class="sort-view">
    <div
      class="item"
      :class="{ act: actItem_sort.sort === item.sort }"
      v-for="item in list_sort"
      :key="item.sort"
      @click="sortEvent(item)"
    >
      <span>{{ item.label }}</span>
      <span v-if="item.order == 'desc'">↓</span>
      <span v-else-if="item.order == 'asc'">↑</span>
    </div>
  </div>
</template>

<style>
.sort-view {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  padding: 10px;
  background: url(http://codegen.caihongy.cn/20251012/b33c1931a3e84ad085276ff354d5ac14.jpg) no-repeat center / 100% 100%;
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
  }
}
</style>
  