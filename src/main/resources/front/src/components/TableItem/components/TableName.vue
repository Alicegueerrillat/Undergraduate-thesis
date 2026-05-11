<script setup>
/**
 * @description 需要修改显示的名称的
 */


defineOptions({
  inheritAttrs: false,
})

const value_name_map = {
  bLoginBackgroundImg: '后台登录页',
  bRegisterBackgroundImg: '后台注册页',
  bIndexBackgroundImg: '后台首页',
  bTopLogo: '后台LOGO',
  // bHomeLogo: '后台首页LOGO',
  fLoginBackgroundImg: '前台登录页',
  fRegisterBackgroudImg: '前台注册页',
  fTopLogo: '前台LOGO',
}

const { value } = defineProps(['value'])
</script>

<template>
  <span>{{ value_name_map[value] ? value_name_map[value] : value }}</span>
</template>
