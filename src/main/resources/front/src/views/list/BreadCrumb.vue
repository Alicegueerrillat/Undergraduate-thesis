<script setup>
/**
 * @description 面包屑
 * ps： 列表和详情页，共享一个
 * 如需区分css样式，加前缀 .list-page 或 .detail-page
 *
 * separator 分隔符号
 * homePath 首页路径
 *
 * second 二级(可能没有)
 * second.path 路径
 * second.title 名称
 *
 * lastTitle 最后一级名称
 *
 * backEvent 返回上一页事件
 */
import { inject } from 'vue'
const { homePath, second, lastTitle, backEvent } = inject('header')
</script>
<template>
  <div class="bread-wrapper">
    <el-breadcrumb separator="/">
      <!-- 一级 -->
      <el-breadcrumb-item :to="{ path: homePath }">
        <el-icon>
          <HomeFilled />
        </el-icon>
        <span>首页</span>
      </el-breadcrumb-item>

      <!-- 二级,可能有 -->
      <el-breadcrumb-item v-if="second.path" :to="second.path">
        {{ second.title }}
      </el-breadcrumb-item>

      <!-- 最后一级 -->
      <el-breadcrumb-item v-if="lastTitle">{{ lastTitle }}</el-breadcrumb-item>
    </el-breadcrumb>

    <el-button circle icon="Back" size="small" title="上一页" @click="backEvent"></el-button>
  </div>
</template>
<style>
.bread-wrapper {
  background-color: #848C74;
  border-radius: 4px;
  padding: 0 10px;
  color: #fff;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  height: 40px;
  gap: 30px;
}
/* 面包屑 */
.el-breadcrumb {
  /* 普通 */
  .el-breadcrumb__item .el-breadcrumb__inner {
    color: inherit;
    display: flex;
    align-items: center;
    gap: 4px;

    &:hover {
      color: inherit;
    }
  }

  /* 普通+可点击跳转,比如第一个(会覆盖普通) */
  .el-breadcrumb__inner.is-link {
    color: inherit;

    &:hover {
      color: var(--btn2-bg-color-);
    }
  }
}
</style>
