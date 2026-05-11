<script setup>
/**
 * @description 列表
 *
 * data.title 标题
 * data.subtitle 副标题
 * data.tableName 表名
 * data.list 单个列表的数据
 *
 * item.picture 图片
 * item.titles 标题列表,可能多个标题
 * item.img 价格,可能没有 undefined判断
 * item.user 发布人,可能没有 undefined判断
 * item.addtime 发布时间
 * item.thumbsupnum 点赞,可能没有 undefined判断
 * item.storeupnum 收藏,可能没有 undefined判断
 * item.clicknum 查看量,可能没有 undefined判断
 * item.browseduration 浏览时长,可能没有 undefined判断
 *
 * hideHeader 隐藏头部
 * detailEvent 点击详情事件
 * moreEvent 更多事件
 */
import { inject } from 'vue'
import dayjs from 'dayjs'

let { data, hideHeader } = defineProps(['data', 'hideHeader'])
let { detailEvent, moreEvent } = inject('start')
</script>

<template>
  <div class="goods-7-2">
    <div class="header common-header" v-if="!hideHeader">
      <div class="title">{{ data.comments }}</div>
      <el-button class="more" color="#848C74" @click="moreEvent(data.tableName)">查看更多</el-button>
    </div>

    <div class="content">
      <div class="item" v-for="item in data.list" :key="item.id" @click="detailEvent(data.tableName, item.id)">
        <div v-if="item.img" class="img-box">
          <img :src="item.img" />
        </div>
        <div class="info-box">
          <div v-for="title in item.titles" class="text">{{ title }}</div>
          <div v-if="item.price !== undefined" class="price">￥ {{ item.price }}</div>
          <div v-if="item.introduction !== undefined" class="introduction">{{ item.introduction }}</div>
          <div v-if="item.user !== undefined" class="user">
            <span>发布人:</span>
            <span>{{ item.user }}</span>
          </div>
          <div class="other">
            <span>发布时间:</span>
            <span>{{ dayjs(item.addtime).format('YYYY-MM-DD') }}</span>
          </div>
          <div v-if="item.thumbsupnum !== undefined" class="other">
            <span>点赞:</span>
            <span>{{ item.thumbsupnum }}</span>
          </div>
          <div v-if="item.storeupnum !== undefined" class="other">
            <span>收藏:</span>
            <span>{{ item.storeupnum }}</span>
          </div>
          <div v-if="item.clicknum !== undefined" class="other">
            <span>查看:</span>
            <span>{{ item.clicknum }}</span>
          </div>
          <div v-if="item.browseduration !== undefined" class="other">
            <span>时长:</span>
            <span>{{ item.browseduration }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.goods-7-2 {
  width: 100%;
  .content {
    width: 100%;
    max-width: 1250px;
    height: auto;
    padding: 20px 0;
    margin: 0 auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    align-items: flex-start;
    gap: 20px;

    .item {
      flex: 0 0 auto;
      width: calc(25% - 15px);
      height: auto;
      padding: 10px;
      border: 1px solid #eee;
      position: relative;
      cursor: pointer;
      transition: transform 0.3s linear;

      &::before {
        position: absolute;
        content: '';
        z-index: -1;
        right: 0;
        top: 0;
        width: 0;
        height: 100%;
        background-color: var(--btn-bg-color-);
        border-radius: 10px;
        transition: 0.5s;
      }

      &:hover {
        transform: translateY(-10px);

        &::before {
          width: 100%;
          left: 0;
        }

        .info-box {
          color: #fff;
        }
      }
    }
  }

  .img-box {
    width: 100%;
    height: 160px;
    border-radius: 10px;
    overflow: hidden;

    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
  }

  .info-box {
    width: 100%;
    height: auto;
    margin: 0;
    padding: 0;
    color: #787878;

    .text {
      color: #000000;
      font-size: 18px;
      font-weight: 600;
      margin-bottom: 6px;
    }

    .price {
      color: #e72c59;
      font-size: 16px;
      font-weight: 600;
      margin-bottom: 6px;
    }
    .introduction {
      font-size: 14px;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 2;
      overflow: hidden;
      text-overflow: ellipsis;
      margin-bottom: 6px;
    }

    .user,
    .other {
      font-size: 12px;
      margin: 0 0 6px;
      display: flex;
      gap: 6px;
    }
  }
}
</style>
