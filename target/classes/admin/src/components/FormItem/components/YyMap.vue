<script setup>
/**
 * @description 地图，选点
 */
import { ref, watchEffect } from 'vue'
import AMap from '@/components/AMap.vue'
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

const configData = {
  mode: 'Address',
}
const dialogVisible = ref(false)
const value = ref('')
const selectedEvent = data => {
  let { lng, lat, address } = data
  ruleForm[column.columnName] = lng + ';' + lat + ';' + address
  dialogVisible.value = false
}

watchEffect(() => {
  let formValue = ruleForm[column.columnName]
  if (!formValue) {
    value.value = ''
    return
  }

  value.value = formValue.split(';')[2]
})
</script>

<template>
  <el-input
    v-model="value"
    placeholder="请点击获取地址信息"
    readonly
    @click="dialogVisible = true"
  />
  <el-dialog
    class="yy-dialog"
    v-model="dialogVisible"
    title="地图"
    destroy-on-close
    :close-on-click-modal="false"
  >
    <AMap @getAddress="selectedEvent" :data="configData" />
  </el-dialog>
</template>
