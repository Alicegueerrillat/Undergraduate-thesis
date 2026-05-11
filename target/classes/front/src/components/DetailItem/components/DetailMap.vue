<script setup>
/**
 * @description 地图位置
 * 格式: lng;lat;address
 */
import { ref, watchEffect } from 'vue'
defineOptions({
  inheritAttrs: false,
})
const { value, label } = defineProps(['value', 'label'])
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
  <div class="detail-text">
    <span class="label">{{ label }}</span>
    <span class="text">{{ text }}</span>
  </div>
</template>
