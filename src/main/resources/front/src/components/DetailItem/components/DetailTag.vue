<script setup>
import { ref, watchEffect } from 'vue'
defineOptions({
  inheritAttrs: false,
})

const { column, value, label } = defineProps(['column', 'value', 'label'])
const actItem = ref()
watchEffect(() => {
  actItem.value = column.options.find(item => item.value == value)
})
</script>

<template>
  <div class="detail-text">
    <span class="label">{{ label }}</span>
    <el-tag v-if="actItem" :type="actItem.type" size="small">{{ actItem.label }}</el-tag>
    <template v-else>{{ value }}</template>
  </div>
</template>
