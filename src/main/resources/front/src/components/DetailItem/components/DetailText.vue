<script setup>
defineOptions({
  inheritAttrs: false,
})
const { value, label } = defineProps(['value', 'label'])
</script>

<template>
  <div class="detail-text">
    <span class="label">{{ label }}</span>
    <span class="text">{{ value }}</span>
  </div>
</template>
