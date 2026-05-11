<script setup>
/**
 * @description 图片列表
 */

defineOptions({
  inheritAttrs: false,
})

const { data } = defineProps(['data'])
let { imgList } = data
</script>

<template>
  <div>
    <img
      v-for="item in imgList"
      :key="item"
      :src="item"
      draggable="false"
      style="width: 100%"
    />
  </div>
</template>
