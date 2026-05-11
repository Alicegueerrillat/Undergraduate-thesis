
<script setup>
/**
 * @description 头部标题logo
 *
 * projectName 项目名
 * $projectImages.logo logo图片
 */
</script>
<template>
  <div class="header-title">
    <div class="logo" v-if="$projectImages.logo">
      <img :src="$projectImages.logo" draggable="false" />
    </div>

    <div class="title">{{ projectName }}</div>
  </div>
</template>
<style>
/* 标题 */
.header-title {
  flex: none;
  display: flex;
  align-items: center;
  .logo {
    width: 90px;
    height: 90px;
    img {
      width: 100%;
      height: 100%;
      object-fit: contain;
    }
  }

  .title {
    color: inherit;
    font-weight: bold;
    font-size: 24px;
  }
}
</style>
  