
<script setup>
import { inject } from 'vue'
const { ruleForm } = inject('login')
</script>
<template>
  <el-form-item label="账号" prop="username" class="username">
    <el-input v-model="ruleForm.username" placeholder="请输入账号" clearable />
  </el-form-item>
</template>  
  
