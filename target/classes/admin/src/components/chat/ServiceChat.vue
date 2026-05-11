<script setup>
import '@/style/chat.scss'
import { onBeforeUnmount, ref } from 'vue'
import { BubbleList, Sender } from '@/components/chat/chatCom'

import avatart_ai from '@/assets/img/AI.png'
import avatart_admin from '@/assets/img/avatar.jpeg'
import { addAPI, getListAPI, saveAPI, getPageAPI } from '@/api/list'
import getFilePath, { getFirstFilePath } from '@/utils/getFilePath'
import base from '@/utils/base'
import { closeWebSocket, initWebSocket, websocketSend } from '@/utils/webSocket'
import dayjs from 'dayjs'

const baseUrl = base.get().url
const action = baseUrl + 'file/upload'
const headers = {
  Token: localStorage.getItem('Token'),
}

let { data } = defineProps(['data'])
let userid = data?.userid || Number(localStorage.getItem('userid'))
let adminid = 1
let uimage = localStorage.getItem('useravatar')
let avatar = getFirstFilePath(uimage)
let uname = localStorage.getItem('username')

const tableName = 'chat'
const config_role = {
  shape: 'corner',
  variant: 'outlined', // filled, outlined
  isMarkdown: true,
  avatarSize: '36px',
}

const config_AI = {
  role: 'ai',
  placement: 'start',
  shape: 'corner',
  variant: 'filled',
  isMarkdown: true,
  isFog: true,
  typing: false,
  avatar: avatart_ai,
  avatarSize: '36px',
}
const config_admin = {
  role: 'admin',
  placement: 'start',
  shape: 'corner',
  variant: 'filled',
  isMarkdown: true,
  typing: false,
  avatar: avatart_admin,
  avatarSize: '36px',
}
const config_user = {
  role: 'user',
  placement: 'end',
  shape: 'corner',
  variant: 'outlined',
  isMarkdown: true,
  typing: false,
  avatar,
  avatarSize: '36px',
}

const list = ref([])
fetchData()
async function fetchData() {
  let res = await getPageAPI(tableName, {
    userid,
    sort: 'addtime',
    order: 'desc',
    limit: 100,
  })
  res.data.list.reverse()
  let lastTime
  let newList = res.data.list.map(item => {
    let { id, ask, reply, uname, uimage, type } = item
    let time = handleTime(lastTime, item.addtime)
    lastTime = item.addtime
    // user
    if (ask) {
      return {
        ...config_role,
        key: id,
        time,
        content: compileMedia(type, ask),
        avatar: getFirstFilePath(uimage),
        placement: 'start',
        variant: 'outlined',
      }
    }
    // ai
    if (uname === 'ai') {
      return {
        ...config_role,
        key: id,
        time,
        content: compileMedia(type, reply),
        avatar: avatart_ai,
        placement: 'end',
        variant: 'filled',
      }
    }

    // admin
    return {
      ...config_role,
      key: id,
      time,
      content: compileMedia(type, reply),
      // uimage不存在，说明是管理员的招呼语
      avatar: getFirstFilePath(uimage) || avatart_admin,
      placement: 'end',
      variant: 'filled',
    }
  })
  list.value = newList
}
function handleTime(lastTime, currentTime) {
  let currentTime_dayjs = dayjs(currentTime)
  let lastTime_dayjs = lastTime ? dayjs(lastTime) : null
  let time = ''
  const isToday = currentTime_dayjs.isSame(dayjs(), 'day')

  time = isToday
    ? currentTime_dayjs.format('HH:mm')
    : currentTime_dayjs.format('YYYY年MM月DD日 HH:mm')

  if (!lastTime_dayjs) {
    return time
  }

  // 距离上一条5分钟内的、连续的，不显示
  // 计算时间差（分钟），取绝对值
  const diffInMinutes = Math.abs(lastTime_dayjs.diff(currentTime_dayjs, 'minute'))
  // 判断是否在 5 分钟以内
  if (diffInMinutes <= 5) {
    time = null
  }
  return time
}

// ----------------------------------
// ---------- 输入框 ---------------
// ----------------------------------
const senderRef = ref()
const senderValue = ref('')
const senderLoading = ref(false)
const uploadRef = ref(null)
// 发送
async function sendMsg(type = 1, content) {
  senderLoading.value = true
  try {
    // 文本和表情
    let data = {
      userid,
      reply: content,
      uname,
      uimage,
      type,
    }

    await saveAPI(tableName, data)

    websocketSend(content)
    // [2] 更新聊天记录
    fetchData()

    type = 1 && (senderValue.value = '')
  } catch (error) {}
  senderLoading.value = false
}

// 上传文件
function openFileDialog() {
  uploadRef.value.click()
}
function fileUploadSuccess(res, file, fileList) {
  let type = 2,
    fileName = file.raw.name,
    fileUrl = 'upload/' + res.file,
    content = fileUrl + '|' + fileName,
    fileType = file.raw?.type || ''

  switch (true) {
    case fileType.startsWith('image'):
      // 图片
      type = 2
      break
    case fileType.startsWith('video'):
      // 视频
      type = 3
      break
    case fileType.startsWith('audio'):
      // 音频
      type = 6
      break

    default:
      // 文件
      type = 4
      break
  }

  sendMsg(type, content)
}
// 解析视频/文件/图片 格式的内容
function compileMedia(type, content) {
  let newContent, fileUrl, fileName
  if (type != 1) {
    let list = content.split('|')
    fileName = list[1]
    fileUrl = getFilePath(list[0])
  }
  switch (type) {
    case 1:
      newContent = content
      break

    case 2:
      newContent = `<img width="200px" src="${fileUrl}" alt="${fileName}"/>`
      break

    case 3:
      newContent = `<video controls width="200px"><source src="${fileUrl}" ></video>`
      break

    case 4:
      newContent = `[${fileName}](${fileUrl})`
      break

    case 6:
      newContent = `<audio controls width="200px" src="${fileUrl}"></audio>`
      break

    default:
      newContent = content
      break
  }

  return newContent
}

// ----------------------------------
// ---------- websocket -------------
// ----------------------------------

initWebSocket(userid, adminid, fetchData)
onBeforeUnmount(() => {
  closeWebSocket()
})
</script>
<template>
  <div class="chat-wrapper">
    <BubbleList :list="list">
      <template #header="{ item }">
        <div class="header">
          <span class="time" v-if="item.time">
            {{ item.time }}
          </span>
        </div>
      </template>
    </BubbleList>
    <Sender
      ref="senderRef"
      v-model="senderValue"
      :loading="senderLoading"
      @submit="sendMsg(1, senderValue)"
    >
      <template #prefix>
        <div class="prefix-self-wrap">
          <el-button @click="openFileDialog">
            <el-icon><Link /></el-icon>
          </el-button>
        </div>
      </template>
    </Sender>

    <el-upload
      style="display: none"
      :action="action"
      :headers="headers"
      :on-success="fileUploadSuccess"
      :show-file-list="false"
    >
      <span ref="uploadRef"></span>
    </el-upload>
  </div>
</template>