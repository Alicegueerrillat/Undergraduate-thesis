<script setup>
/**
 * @description 总数统计组件
 */
import { ref } from 'vue'

import { getCountAPI } from '@/api/list'

const props = defineProps(['data'])
const count = ref(0)

getCountAPI(props.data.tableName).then(res => {
  count.value = res.data
})
</script>

<template>
  <div class="statis-box-common">
    <!-- 图标 -->
    <div className="icon-box">
      <el-icon class="icon">
        <component :is="props.data.icon" />
      </el-icon>
    </div>

    <!-- 文字 -->
    <div className="info-box">
      <div className="count">{{ props.data.comments }}</div>
      <div className="label">{{ count }}</div>
    </div>
  </div>
</template>
