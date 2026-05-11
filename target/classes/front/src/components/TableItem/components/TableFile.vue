<script setup>
import { downloadFile } from '@/utils'
/**
 * @description 文件
 */
defineOptions({
  inheritAttrs: false,
})
const { value } = defineProps(['value'])
</script>

<template>
  <div class="table-file">
    <el-link v-if="value" type="primary" @click="downloadFile(value)">下载</el-link>

    <el-text v-else type="info">没有文件</el-text>
  </div>
</template>

<style></style>
