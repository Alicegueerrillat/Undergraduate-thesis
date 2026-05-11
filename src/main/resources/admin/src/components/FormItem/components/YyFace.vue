<script setup>
/**
 * @description 人脸
 */
import { ref } from 'vue'
import FaceMatch from '@/components/FaceMatch.vue'
import { getFirstFilePath } from '@/utils/getFilePath'
const { formItem } = ElementPlus.useFormItem()
defineOptions({
  inheritAttrs: false,
})
const { column, ruleForm, disabled, ruleFormRef } = defineProps({
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

const { columnName } = column

let onSubmit = fileName => {
  ruleForm[columnName] = 'upload/' + fileName

  // 触发trigger包含change的校验规则
  formItem?.validate?.('change')
}
const dialogVisible = ref(false)
let buttonName = column.faceVerification ? '人脸校检' : '人脸识别'
</script>

<template>
  <div class="face-form-item">
    <img :src="getFirstFilePath(ruleForm[columnName])" />
    <el-button class="face-btn" @click="dialogVisible = true">{{ buttonName }}</el-button>
    <FaceMatch v-model="dialogVisible" :buttonName="buttonName" @submit="onSubmit" />
  </div>
</template>
