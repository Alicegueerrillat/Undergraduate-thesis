<script setup>
import { computed, ref, watch, inject } from 'vue'
import { getListAPI, addAPI, updateAPI, deleteAPI } from '@/api/list'

/**
 * @description 标题 + 收藏storeupnum(关注)
 * storeUp:
 * 是 收藏模式
 * 关注 关注模式
 */
defineOptions({
  inheritAttrs: false,
})

const { value, label, data } = defineProps(['data', 'value', 'label'])
const { storeUpConfig, tableName } = inject('detail')
let { storeUp, imgName, titleName, inteltypeName } = storeUpConfig

const storeUpId = ref(null)
// 获取是否已收藏/已关注
async function getIsStoreup(id) {
  let res = await getListAPI('storeup', {
    page: 1,
    limit: 1,
    // 收藏是1 ，关注是41
    type: storeUp == '关注' ? 41 : 1,
    refid: id,
    tablename: tableName,
    userid: Number(localStorage.getItem('userid')),
  })

  storeUpId.value = res.data.list.length > 0 ? res.data.list[0].id : null
}

watch(
  () => data.id,
  id => {
    if (id) {
      getIsStoreup(id)
    }
  }
)

let name = computed(() => {
  let storeupnum = data.storeupnum || 0
  if (storeUpId.value) {
    return storeUp == '是' ? `已收藏(${storeupnum})` : '已关注'
  }
  return storeUp == '是' ? `点击收藏(${storeupnum})` : '点击关注'
})

// 收藏
async function addStoreUp() {
  let params = {
    // 标题字段
    name: data[titleName],
    // 图片字段
    picture: data[imgName],
    // 智能推荐字段
    inteltype: data[inteltypeName],
    // 详情id
    refid: data.id,
    tablename: tableName,
    userid: Number(localStorage.getItem('userid')),
    type: storeUp == '是' ? 1 : 41,
  }

  // storeup表，增加一条数据
  let res = await addAPI('storeup', params)
  // 更新已收藏的id
  storeUpId.value = res.data

  let message = '已关注'
  if (storeUp == '是') {
    data.storeupnum += 1
    updateAPI(tableName, data)
    message = '已收藏'
  }

  ElMessage({
    type: 'success',
    message,
  })
}
async function removeStoreUp() {
  // storeup表，删除一条数据
  await deleteAPI('storeup', [storeUpId.value])

  storeUpId.value = null

  let message = '已取消关注'
  if (storeUp == '是') {
    data.storeupnum -= 1
    updateAPI(tableName, data)
    message = '已取消收藏'
  }
  ElMessage({
    type: 'success',
    message,
  })
}
</script>

<template>
  <div class="detail-title">
    <span class="title">{{ value }}</span>

    <span class="storeup act" v-if="storeUpId" @click="removeStoreUp">{{ name }}</span>
    <span class="storeup" v-else @click="addStoreUp">{{ name }}</span>
  </div>
</template>
