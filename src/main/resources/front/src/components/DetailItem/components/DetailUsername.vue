<script setup>
defineOptions({
  inheritAttrs: false,
})
const { value, label, data } = defineProps(['value', 'label', 'data'])
</script>

<template>
  <div class="detail-text">
    <span class="label">{{ label }}</span>
    <span class="text">{{ data.isanon == 1 ? '匿名' : value }}</span>
  </div>
</template>
