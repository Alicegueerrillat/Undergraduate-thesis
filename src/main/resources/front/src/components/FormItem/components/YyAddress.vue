<script setup>
/**
 * @description 下拉单选
 *
 */
import { ref, watch } from 'vue'

import tableConfigs from '@/utils/tableConfigs'

const options = tableConfigs.address?.options || []
defineOptions({
  inheritAttrs: false,
})

const { column, ruleForm, disabled, selectAllowClear } = defineProps({
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
  selectAllowClear: {
    type: Boolean,
    default: false,
  },
})
let { columnName } = column

const cascaderProps = { value: 'label' }
const cascaderValue = ref([])
const inputValue = ref('')

function handleChange() {
  let list = [
    cascaderValue.value[0],
    cascaderValue.value[1],
    cascaderValue.value[2],
    inputValue.value,
  ]

  ruleForm[columnName] = list.join('-')
}

watch(
  () => ruleForm[columnName],
  val => {
    // 做一层转换，字符串转数组
    if (!val) {
      cascaderValue.value = []
      inputValue.value = ''
      return
    }

    let [province, city, district, detail] = val.split('-')
    cascaderValue.value = [province, city, district]
    inputValue.value = detail
  },
  { immediate: true }
)
</script>

<template>
  <div class="address-wrapper">
    <el-cascader
      placeholder="请选择省市区"
      v-model="cascaderValue"
      :options="options"
      :props="cascaderProps"
      style="width: 240px; margin-bottom: 10px"
      @change="handleChange"
    />
    <el-input placeholder="请输入详细地址" v-model="inputValue" @change="handleChange"></el-input>
  </div>
</template>
