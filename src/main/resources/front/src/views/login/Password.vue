
<script setup>
import { inject } from 'vue'
const { ruleForm } = inject('login')
</script>
<template>
  <el-form-item label="密码" prop="password" class="password">
    <el-input v-model="ruleForm.password" placeholder="请输入密码" show-password clearable />
  </el-form-item>
</template> 
  
