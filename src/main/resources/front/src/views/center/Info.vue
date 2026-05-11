<script setup>
/**
 * @description 个人信息
 *
 * infoList 数据列表
 * item.label 名称
 * item.value 值
 *
 * userAvatar 头像
 */
import { inject } from 'vue'

let { infoList, userAvatar } = inject('center')
</script>

<template>
  <div class="centerinfo">
    <div class="subtitle">我的信息</div>

    <div class="centerinfo-inner">
      <!-- 头像 -->
      <div className="avatar">
        <img className="img" :src="userAvatar" draggable="false" />
      </div>

      <!-- 其它信息 -->
      <div class="info-list">
        <div v-for="(item, index) in infoList" :key="index" class="info-item">
          <span class="label">{{ item.label }}:</span>
          <span class="value">{{ item.value }}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.centerinfo-inner {
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  background: url(http://codegen.caihongy.cn/20251012/2157ea8c2a284a469a53aa33811a566d.jpg) no-repeat center / 100% 100%;

  .avatar {
    text-align: center;
    margin-bottom: 20px;

    .img {
      width: 100px;
      height: 100px;
      border-radius: 50%;
      object-fit: cover;
    }
  }

  .info-list {
    display: grid;
    gap: 10px;
    grid-template-columns: repeat(3, 1fr);
    grid-template-rows: repeat(2, 70px);

    .info-item {
      width: 100%;
      height: 70px;
      padding: 20px;

      background: url(http://codegen.caihongy.cn/20251012/bb1f2be62a8747eea6e1f5747d1eeb73.jpg) no-repeat center / 100% 100%;

      .label {
        color: #999;
      }

      .value {
        margin-left: 10px;
      }
    }
  }
}
</style>