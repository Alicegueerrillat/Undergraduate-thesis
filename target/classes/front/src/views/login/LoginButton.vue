<script setup></script>
<template>
  <el-button class="login-btn" native-type="submit" type="primary">登录</el-button>
</template>
<style>
.login-btn {
  border-radius: 45px;
  padding: 4px 15px;
  margin-bottom: 20px;
  width: 100%;
  font-size: 24px;
  height: 45px;

  background: var(--btn-bg-color-);
  box-shadow: 0 4px 15px rgb(from var(--btn-bg-color-) r g b / 0.4),
    0 0 0 0 rgb(from var(--btn-bg-color-) r g b / 0.7);
  border: 2px solid transparent;

  &:hover {
    border-color: transparent;
    background: var(--btn-bg-hover-color-);
    transform: translateY(-4px);
    box-shadow: 0 8px 25px rgb(from var(--btn-bg-color-) r g b / 0.6),
      0 0 0 4px rgb(from var(--btn-bg-color-) r g b / 0.2);
  }

  &:active {
    transform: translateY(-2px);
    box-shadow: 0 4px 15px rgb(from var(--btn-bg-color-) r g b / 0.4);
  }
}
</style>

