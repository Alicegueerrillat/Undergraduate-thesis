<script setup>
/**
 * @description 数字输入框
 */
import { onBeforeMount } from 'vue'

defineOptions({
  inheritAttrs: false,
})
const { column, ruleForm, disabled } = defineProps({
  column: {
    type: Object,
    required: true,
  },
  ruleForm: {
    type: Object,
    required: true,
  },
  disabled: {
    type: Boolean,
    default: false,
  },
})
onBeforeMount(() => {
  // 初始值需要数值类型
  // 如果ruleForm[column.columnName]没有值，设置为0
  if (!ruleForm[column.columnName]) {
    ruleForm[column.columnName] = 0
  }
})
</script>

<template>
  <el-input-number
    v-if="column.formatValidation == '数'"
    :precision="0"
    v-model="ruleForm[column.columnName]"
    :disabled="disabled"
  />
  <el-input-number v-else v-model="ruleForm[column.columnName]" :disabled="disabled" />
</template>
