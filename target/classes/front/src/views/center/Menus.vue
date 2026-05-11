<script setup>
/**
 * @description 选项卡
 *
 * menuList 数据列表
 * item.label 名称
 * item.path 路径
 * item.query 查询参数
 *
 * menuEvent 菜单列表的点击事件
 */
import { inject } from 'vue'

let { menuList, menuEvent } = inject('center')
// 采用自带icon
const iconList = [
  'Notification',
  'Connection',
  'ChatDotRound',
  'PieChart',
  'Message',
  'Notebook',
  'Postcard',
  'DataLine',
  'OfficeBuilding',
  'School',
  'Coin',
  'Calendar',
  'CreditCard',
  'Refrigerator',
  'Paperclip',
  'TakeawayBox',
  'Apple',
  'Orange',
  'Coffee',
  'MapLocation',
  'Film',
  'Monitor',
  'Iphone',
  'DocumentAdd',
  'Document',
  'Tickets',
  'Memo',
]
</script>

<template>
  <div class="centermenu">
    <div class="subtitle">我的菜单</div>
    <div class="list">
      <div v-for="(item, index) in menuList" :key="index" class="item" @click="menuEvent(item)">
        <el-icon>
          <component :is="iconList[index]" />
        </el-icon>

        <span>{{ item.label }}</span>
      </div>
    </div>
  </div>
</template>

<style>
.centermenu {
  padding-top: 30px;
  .header {
    font-size: 30px;
  }
  .list {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;

    .item {
      cursor: pointer;
      width: 200px;
      height: 100px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      gap: 10px;
      background: #eee;
      border-radius: 6px;
      color: var(--btn-font-color-);
      background: var(--btn-bg-color-);
      transition: transform 0.3s linear;

      &:hover {
        color: var(--btn-font-hover-color-);
        background: var(--btn-bg-hover-color-);
        transform: translateY(10px);
      }
    }
    .el-icon {
      font-size: 30px;
    }
  }
}
</style>
