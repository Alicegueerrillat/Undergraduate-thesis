
<script setup>
import { inject } from 'vue'
const { faceEvent, hasFaceMatch } = inject('login')
</script>
<template>
  <el-button v-show="hasFaceMatch" class="face-btn" @click="faceEvent" text >人脸登录</el-button>
</template>

