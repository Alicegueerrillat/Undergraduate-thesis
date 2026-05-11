<script setup>
// 初始化菜单
import { initMenus } from './utils/menu'

// 初始化用户信息
import { useUserInfo } from '@/store'
useUserInfo()

initMenus()
</script>

<template>
  <RouterView />
</template>
