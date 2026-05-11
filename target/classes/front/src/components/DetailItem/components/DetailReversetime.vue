<script setup>
import { ref, watchEffect } from 'vue'
import dayjs from 'dayjs'
defineOptions({
  inheritAttrs: false,
})
const { value, label, data } = defineProps(['value', 'label', 'data'])

const endText = ref('已结束')
const isEnd = ref(false)

const endTime = ref(dayjs(value))
watchEffect(() => {
  if (value) {
    endTime.value = dayjs(value)


    // 普通的倒计时
    isEnd.value = dayjs().isAfter(endTime.value)
    endText.value = isEnd.value ? '已结束' : ''
  }
})

function finishEvent() {
  isEnd.value = true


  endText.value = '已结束'
}
</script>

<template>
  <div class="detail-reversetime">
    <span class="label">{{ label }}</span>
    <span v-if="isEnd" class="text">{{ endText }}</span>
    <el-countdown v-else :title="endText" :value="endTime" @finish="finishEvent" />
  </div>
</template>
