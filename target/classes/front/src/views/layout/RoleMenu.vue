
<script setup>
import { inject } from 'vue'
/**
 * @description 用户信息
 *
 * isLogin 是否已登录
 * userName 账户名
 * userAvatar 头像
 * roleMenus 菜单列表
 * item.label 菜单名称
 * item.icon 菜单图标
 * 
 * roleMenuEvent 菜单点击事件
 * loginEvent 登录事件
 */

let { isLogin, userName, userAvatar, roleMenus, roleMenuEvent, loginEvent } = inject('header')
</script>

<template>
  <div class="userinfo-view">
    <!-- 未登录 -->
    <el-button v-if="!isLogin" @click="loginEvent">请先登录</el-button>

    <!-- 已登录 -->
    <el-dropdown v-else trigger="click" @command="roleMenuEvent">
      <div class="avatar">
        <img class="img" :src="userAvatar" />
        <span class="name">{{ userName }}</span>
      </div>

      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item v-for="item in roleMenus" :key="item.key" :command="item.key">
            <div class="menu-item">
              <el-icon>
                <component :is="item.icon" />
              </el-icon>
              <span>{{ item.label }}</span>
            </div>
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<style>
.userinfo-view {
  flex: none;
  user-select: none;
  color: inherit;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;

  .avatar {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .img {
    width: 50px;
    height: 50px;
    border: 1px solid transparent;
    margin-right: 10px;
  }

  .name {
    font-size: 14px;
    color: #000;
  }
}
</style>
  