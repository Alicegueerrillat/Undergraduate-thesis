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
import { inject, onUnmounted, ref } from 'vue'
import { useIntersectionObserver } from '@vueuse/core'
import dayjs from 'dayjs'

let { data, hideHeader } = defineProps(['data', 'hideHeader'])
let { detailEvent, moreEvent } = inject('start')

const boxRef = ref(null)
const animateClass = ref('')
// 元素可见时，增加动画
let { stop: aboutusStop } = useIntersectionObserver(
  boxRef,
  ([{ isIntersecting }]) => {
    if (isIntersecting) {
      // 这里修改 动画，https://animate.style/
      animateClass.value = 'animate__animated animate__fadeInRight'

    } else {
      animateClass.value = ''
    }
  },
  // 0.01 目标元素与视口的交叉比例达到 ‌1%‌ 时触发回调函数
  { threshold: 0 }
)
onUnmounted(() => {
  aboutusStop()
})
</script>

<template>
  <div class="goods-6" ref="boxRef">
    <div class="header common-header" v-if="!hideHeader">
      <div class="title">{{ data.comments }}</div>
      <el-button class="more" color="#848C74" @click="moreEvent(data.tableName)">查看更多</el-button>
    </div>

    <div class="content" :class="animateClass">
      <div class="item" v-for="item in data.list" :key="item.id" @click="detailEvent(data.tableName, item.id)">
        <div class="img-box">
          <img :src="item.img" />
        </div>
        <div class="info-box">
          <div v-for="title in item.titles" class="text">{{ title }}</div>
          <div v-if="item.price !== undefined" class="price">￥ {{ item.price }}</div>
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
.goods-6 {
  width: 100%;
  padding-top: 20px;
  padding-bottom: 30px;
  background: url(http://codegen.caihongy.cn/20251011/b3b60ca8a9154b0682eaf3734bf11ef5.jpg) no-repeat top center;

  .content {
    width: 100%;
    max-width: 1250px;
    height: auto;
    padding: 20px 0;
    margin: 0 auto;
    display: grid;
    display: grid;
    grid-template-rows: 280px 280px;
    grid-template-columns: 450px 290px 160px 290px;
    gap: 30px;

    .item {

      height: 100%;
      width: 100%;
      border: 1px solid #eee;
      position: relative;
      cursor: pointer;
      /* transition: boxShadow 0.3s linear, transform 0.3s linear; */

      &:hover {
        .info-box {
          display: flex;
        }

        img {
          transform: scale(1.08);
        }


      }
    }

    .item:nth-child(1) {
      grid-row: 1 / 3;
      grid-column: 1 / 2;
    }

    .item:nth-child(2) {
      grid-row: 1 / 2;
      grid-column: 2 / 4;
    }

    .item:nth-child(3) {
      grid-row: 1 / 2;
      grid-column: 4 / 5;
    }

    .item:nth-child(4) {
      grid-row: 2 / 3;
      grid-column: 2 / 3;
    }

    .item:nth-child(5) {
      grid-row: 2 / 3;
      grid-column: 3 / 5;
    }
  }

  .img-box {
    width: 100%;
    height: 100%;
    overflow: hidden;


    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      transition: transform 0.3s linear;
    }
  }

  .info-box {
    display: none;
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background: rgba(40, 44, 32, 0.5);
    color: #fff;

    flex-direction: column;
    justify-content: center;
    align-items: center;

    .text {

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