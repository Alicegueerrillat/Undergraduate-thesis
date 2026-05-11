
<script setup>
/**
 * @description 标题logo
 *
 * projectName 项目名
 * $projectImages.logo logo图片
 */
</script>
<template>
  <div class="login-title">
    <div class="logo" v-if="$projectImages.logo">
      <img :src="$projectImages.logo" draggable="false" />
    </div>

    <div class="title">{{ projectName }}</div>
  </div>
</template>
<style>
.login-title {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 20px;
  .logo {
    width: 90px;
    height: 90px;
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
  }

  .title {
    color: var(--btn-bg-color-);
    font-weight: bold;
    font-size: 28px;
  }
}
</style>
  
