
<script setup>
import { inject } from 'vue'

/**
 * @description 底部
 * 
 * footerContent 底部html内容
 *
 */
let { footerContent } = inject('header')
</script>
<template>
  <div class="layout-footer">
    <div class="ql-snow ql-editor" v-html="footerContent"></div>
  </div>
</template>
<style>
.layout-footer {
      background: url(http://codegen.caihongy.cn/20251011/37c75534cfda4c35a895e5b691d9046a.png) no-repeat center / 100% 100%;

}
</style>  
  