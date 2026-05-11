
<script setup>
import { inject } from 'vue'
/**
 * @description 轮播图
 * 配置项：https://element-plus.org/zh-CN/component/carousel
 *
 * banners 轮播图列表
 * item.pictrue 图片
 *
 * bannerEvent 轮播图点击事件
 * showBanner 当前页面是否显示轮播图
 */

let { banners, bannerEvent, showBanner } = inject('header')
</script>

<template>
  <div v-if="showBanner" class="banner-wrapper">
    <el-carousel height="360px" trigger="hover" :autoplay="true" :interval="5000" type="card">
      <el-carousel-item v-for="item in banners" :key="item.id">
        <img class="img" :src="item.picture" @click="bannerEvent(item)" draggable="false" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<style>
.banner-wrapper {
  background: url(http://codegen.caihongy.cn/20251011/02db3b7d40f04e05adba9937a41b58d4.jpg);
  .img {
    width: 100%;
    height: 100%;
  }
  .el-carousel{
    --el-carousel-indicator-height: 6px;
  }
  .el-carousel__button{
    background: var(--btn-bg-color-);
  }
}
</style>
  
