<script setup>
let { list, hasScore } = defineProps(['list', 'hasScore'])
const emits = defineEmits(['thumbsup', 'crazily', 'remove', 'reply', 'removeChild'])
</script>

<template>
  <div class="discuss-view">
    <div class="item" v-for="item in list" :key="item.id">
      <div class="item-left">
        <img class="img" :src="item.img" />
      </div>
      <div class="item-right">
        <div class="nickname">{{ item.nickname }}</div>

        <div class="content">
          <div class="ql-snow ql-editor" v-html="item.content"></div>
        </div>

        <div class="action">
          <div class="time">
            <span>{{ item.addtime }}</span>
            <el-rate
              v-if="hasScore"
              v-model="item.score"
              :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
              disabled
            />
          </div>
          <div class="btn">
            <el-button v-if="item.hasThumbsup" plain size="small" @click="emits('thumbsup', item)">
              取消赞 {{ item.thumbsupnum }}
            </el-button>
            <el-button
              v-if="!item.hasThumbsup && !item.hasCrazily"
              plain
              size="small"
              @click="emits('thumbsup', item)"
            >
              赞 {{ item.thumbsupnum }}
            </el-button>

            <el-button v-if="item.hasCrazily" plain size="small" @click="emits('crazily', item)">
              取消踩 {{ item.crazilynum }}
            </el-button>
            <el-button
              v-if="!item.hasThumbsup && !item.hasCrazily"
              plain
              size="small"
              @click="emits('crazily', item)"
            >
              踩 {{ item.crazilynum }}
            </el-button>
            <!-- <el-button size="small" @click="emits('reply', item)">回复</el-button> -->
            <el-button
              v-if="item.hasRemove"
              type="danger"
              size="small"
              @click="emits('remove', item)"
            >
              删除
            </el-button>
          </div>
        </div>
      </div>
      <div class="reply-box" v-if="item.replyList">
        <div class="item" v-for="i in item.replyList" :key="i.id">
          <div class="item-left">
            <img v-if="i.img" class="img" :src="i.img" />
          </div>
          <div class="item-right">
            <div class="nickname">{{ i.nickname }}</div>

            <div class="content">
              <div class="ql-snow ql-editor" v-html="i.content"></div>
            </div>

            <div class="action">
              <div class="time">
                <span>{{ i.addtime }}</span>
              </div>

              <div class="btn">
                <el-button
                  v-if="i.hasRemove"
                  type="danger"
                  size="small"
                  @click="emits('removeChild', item.id, i.id)"
                >
                  删除
                </el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
.discuss-view {
  .item {
    display: flex;
    flex-wrap: wrap;
    padding-top: 28px;
    font-size: 14px;
    color: #222;
    padding-bottom: 10px;
    border-bottom: 1px dashed #eee;
    &:last-of-type {
      border-bottom: none;
    }
    & > .item-left {
      flex: none;
      width: 64px;
    }
    & > .item-right {
      flex: 1 1 auto;
      width: 0;
    }
    & > .reply-box {
      width: 100%;
      padding-left: 64px;
    }
  }

  .img {
    width: 48px;
    height: 48px;
    border-radius: 50%;
  }
  .nickname {
    font-size: 13px;
    color: #222;
    font-weight: 600;
  }

  .action {
    display: flex;
    justify-content: space-between;
  }
  .time {
    font-size: 13px;
    color: #9195a3;
  }
}
</style>
