<script setup>
defineOptions({
  inheritAttrs: false,
})
const { config, data } = defineProps(['config', 'data'])

let { columnName } = config
</script>

<template>
  <div class="quill-tab">
    <div class="ql-snow ql-editor" v-html="data[columnName]"></div>
  </div>
</template>
