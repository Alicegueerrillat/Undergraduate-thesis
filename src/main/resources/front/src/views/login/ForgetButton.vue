
<script setup>
import { inject } from 'vue'
const { forgetEvent } = inject('login')
</script>
<template>
  <el-button class="forget-btn" @click="forgetEvent" text >忘记密码,点击找回?</el-button>
</template>

