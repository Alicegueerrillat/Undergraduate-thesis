<script setup>
/**
 * @description 角色菜单
 */
import { inject } from 'vue'
const { roleMenus, userName, userAvatar, roleMenuEvent } = inject('header')
</script>

<template>
  <el-dropdown class="rolemenus" trigger="click" @command="roleMenuEvent">
    <div class="avatar">
      <img class="img" :src="userAvatar" />
      <span class="name">{{ userName }}</span>
      <el-icon>
      <svg t="1757405912833" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="11807" width="64" height="64"><path d="M64 320l448 448 448-448z" fill="currentColor" p-id="11808"></path></svg>
      </el-icon>
    </div>

    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item v-for="item in roleMenus" :key="item.key" :command="item.key">
          <div class="menu-item" :list-key="item.key">
            <el-icon>
              <component :is="item.icon" />
            </el-icon>
            <span>{{ item.label }}</span>
          </div>
        </el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<style>
.rolemenus {
  color: inherit;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;

  .avatar {
    display: flex;
    justify-content: center;
    align-items: center;
    background: #fff;
    border-radius: 8px;
    padding: 6px 10px;
    color: #666666;
  }

  .img {
    width: 32px;
    height: 32px;
    border: 1px solid transparent;
    margin-right: 10px;
    border-radius: 50%;
  }

  .name {
    font-size: 14px;

  }
}
</style>