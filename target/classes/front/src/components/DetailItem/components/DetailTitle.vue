<script setup>
/**
 * @description 标题
 */
defineOptions({
  inheritAttrs: false,
})
const { value } = defineProps(['value'])
</script>

<template>
  <div class="detail-title">
    <span class="title">{{ value }}</span>
  </div>
</template>
