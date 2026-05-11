
<script setup>
/**
 * @description 分页
 */
import { inject } from 'vue'

let { currentPage, pageSize, total } = inject('listFront')
</script>
<template>
  <div class="pagination">
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :total="total"
      :page-sizes="[10, 20, 30, 50]"
      :background="true"
      layout="total, sizes, prev, pager, next, jumper"
      :hide-on-single-page="false"
    />
  </div>
</template>
   
  