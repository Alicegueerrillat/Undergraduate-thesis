<script setup>
import { inject } from 'vue'
let { banners, bannerEvent, showBanner } = inject('header')
</script>

<template>
  <div v-if="showBanner" class="banner-wrapper-detail">
    <el-carousel height="100%" trigger="click" :autoplay="false" :interval="5000" type="">
      <el-carousel-item v-for="item in banners" :key="item.id">
        <img class="img" :src="item.picture" @click="bannerEvent(item)" draggable="false" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<style>
.banner-wrapper-detail {
  .el-carousel {
    height: 100%;
    min-height: 300px;
  }
  .img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
}
</style>
