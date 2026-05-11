<script setup>
defineOptions({
  inheritAttrs: false,
})
const { value, label } = defineProps(['value', 'label'])

function openUrl() {
  if (!value) {
    return
  }
  window.open(value)
}
</script>

<template>
  <div class="detail-text" @click="openUrl">
    <span class="label">{{ label }}</span>
    <span class="text url">{{ value }}</span>
  </div>
</template>

<style>
.detail-text {
  .url {
    text-decoration: underline;
    color: #409eff;
    cursor: pointer;
    &:hover {
      color: rgb(121, 187, 255);
    }
  }
}
</style>
