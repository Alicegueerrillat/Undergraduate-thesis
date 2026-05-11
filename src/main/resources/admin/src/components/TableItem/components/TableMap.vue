<script setup>
/**
 * @description 地图位置
 * 格式: lng;lat;address
 */
import { ref, watchEffect } from 'vue'

defineOptions({
  inheritAttrs: false,
})
const { value } = defineProps(['value'])
let text = ref('')
watchEffect(() => {
  if (!value) {
    text.value = ''
    return
  }

  text.value = value.split(';')[2]
})
</script>

<template>
  <span>{{ text }}</span>
</template>
