<script setup>
import { getFirstFilePath } from '@/utils/getFilePath'
import { computed } from 'vue'
import { inject } from 'vue'

defineOptions({
  inheritAttrs: false,
})
const { value, label, column, data } = defineProps(['value', 'label', 'column', 'data'])
let { centerType } = inject('detail')

let hasAuth = getAuth()
function getAuth() {
  if (column.formatValidation == '会员') {
    let userForm = JSON.parse(localStorage.getItem('userForm'))
    // 未登录
    if (!userForm) {
      return false
    }

    // 非会员 + 自己的 + 带支付
    if (userForm.vip != '是' && centerType && data.ispay == '未支付') {
      return false
    }

    // 非会员 + 不是自己的
    if (userForm.vip != '是' && !centerType) {
      return false
    }
  }

  return true
}

let src = computed(() => {
  return getFirstFilePath(value)
})

function playEvent(event) {
  if (!hasAuth) {
    ElMessage.error('请先开通会员')
    event.target.pause()
    return
  }
}
</script>

<template>
  <div class="detail-text">
    <span class="label">{{ label }}</span>
    <audio
      v-if="src"
      class="audio"
      :src="src"
      controls
      :controlslist="hasAuth ? '' : 'nodownload'"
      @play="playEvent($event)"
    ></audio>
    <el-text v-else type="info">没有音频</el-text>
  </div>
</template>
