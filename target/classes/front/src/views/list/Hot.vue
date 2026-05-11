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
  <div class="goods-5">
    <div class="header common-header" v-if="!hideHeader">
      <div class="title">{{ data.comments }}</div>
    </div>

    <div class="content">
      <div class="item" v-for="item in data.list" :key="item.id" @click="detailEvent(data.tableName, item.id)">
        <div v-for="title in item.titles" class="goodtitle">{{ title }}</div>

        <div class="item-inner">
          <div class="info-box">
            <div class="info-box-inner">
              <div v-if="item.price !== undefined" class="price">￥ {{ item.price }}</div>
              <div v-if="item.user !== undefined" class="user">
                <span>发布人:</span>
                <span>{{ item.user }}</span>
              </div>
              <div class="other">
                <span>发布时间:</span>
                <span>{{ dayjs(item.addtime).format('YYYY-MM-DD') }}</span>
              </div>

            </div>
          </div>

          <div class="img-box" v-if="item.img">
            <img :src="item.img" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.goods-5 {
  width: 100%;
  padding-top: 30px;
  background: url(http://codegen.caihongy.cn/20251011/02db3b7d40f04e05adba9937a41b58d4.jpg);

  /* 通用标题 */
  .common-header {
    width: 100%;
    height: 108px;
    padding: 20px calc(50% - 585px) 0;
    position: relative;
    background: url(http://codegen.caihongy.cn/20251011/822963006bd04a4c9ed748e33aacb5fa.png) no-repeat center;

    display: flex;
    justify-content: space-between;


    .title {
      font-size: 28px;
      color: #000;
      font-weight: 600;
    }

  }

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
  }

  .item {
    flex: 0 0 auto;
    width: calc(50% - 10px);
    height: auto;
    padding: 10px;
    border: 1px solid #eee;
    position: relative;
    cursor: pointer;
    box-shadow: inset 0 0 0 5px #e7ebf3;
    background: #fff;

    transition: boxShadow 0.3s linear, transform 0.3s linear;

    &::before {
      box-sizing: inherit;
      content: '';
      position: absolute;
      width: 100%;
      height: 100%;
      top: 0;
      left: 0;

      border-top: 5px solid var(--btn-bg-color-);
      border-bottom: 5px solid var(--btn-bg-color-);
      transform: scale3d(0, 1, 1);
      transform-origin: left;
    }

    &::after {
      box-sizing: inherit;
      content: '';
      position: absolute;
      width: 100%;
      height: 100%;
      top: 0;
      left: 0;

      border-left: 5px solid var(--btn-bg-color-);
      border-right: 5px solid var(--btn-bg-color-);
      transform: scale3d(1, 0, 1);
      transform-origin: bottom;
    }

    &:hover::before,
    &:hover::after {
      transform: scale3d(1, 1, 1);
      transition: transform 0.4s;
    }

    &:hover .goodtitle {
      color: var(--btn-bg-color-);
    }

    &:hover img {
      transform: scale(1.08);
      transition: transform 0.3s linear;
    }

    .goodtitle {
      color: #000000;
      font-size: 16px;
      line-height: 32px;
    }
  }

  .item-inner {
    display: flex;
    gap: 10px;
  }

  .img-box {
    flex: auto;
    width: 50%;
    height: 140px;
    overflow: hidden;
    border-radius: 10px;

    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
  }

  .info-box {
    flex: auto;
    width: 50%;

    .price {
      color: #e72c59;
      font-size: 16px;
      font-weight: 600;
    }

    .user,
    .other {
      color: #787878;
      font-size: 12px;
      display: flex;
      gap: 6px;
    }
  }

  .info-box-inner {
    display: flex;
    flex-direction: column;
    gap: 6px;
  }
}
</style>
