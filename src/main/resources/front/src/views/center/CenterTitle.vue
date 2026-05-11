<script setup>
/**
 * @description 标题 公共的
 * 很多子页面都会用这个标题组件: 
 * title 标题
 */
let { title } = defineProps(['title'])
</script>

<template>
  <div class="centertitle">
    <div class="title">{{ title }}</div>
  </div>
</template>
<style>
.centertitle {
  width: 100%;
  height: 108px;
  padding: 20px calc(50% - 585px) 0;
  position: relative;
  background: url(http://codegen.caihongy.cn/20251011/822963006bd04a4c9ed748e33aacb5fa.png) no-repeat center;

  display: flex;
  justify-content: space-between;

  .title {
    font-size: 28px;
    color: #000;
    font-weight: 600;
  }

}
</style>