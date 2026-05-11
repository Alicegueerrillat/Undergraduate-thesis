<script setup>
defineOptions({
  inheritAttrs: false,
})
const { value, label } = defineProps(['value', 'label'])
</script>

<template>
  <div class="detail-price">
    <span class="label">{{ label }}</span>
    <span class="price">¥ {{ value }}</span>
  </div>
</template>
