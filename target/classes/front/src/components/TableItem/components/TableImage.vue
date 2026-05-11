<script setup>
import { getFilePaths } from '@/utils/getFilePath'
import { computed } from 'vue'

/**
 * @description 图片
 */
defineOptions({
  inheritAttrs: false,
})
const { value } = defineProps(['value'])

let srcList = computed(() => {
  return getFilePaths(value)
})
</script>

<template>
  <el-image
    class="table-image"
    preview-teleported
    :src="srcList[0]"
    :preview-src-list="srcList"
    show-progress
    fit="cover"
  />
</template>