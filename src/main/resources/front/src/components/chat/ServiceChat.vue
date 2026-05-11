<script setup>
import '@/style/chat.scss'

import { onBeforeUnmount, ref } from 'vue'
import { BubbleList, Sender } from '@/components/chat/chatCom'

import avatart_ai from '@/assets/img/AI.png'
import { addAPI, getListAPI, saveAPI, getPageAPI } from '@/api/list'
import getFilePath, { getFirstFilePath } from '@/utils/getFilePath'
import base from '@/utils/base'
import { closeWebSocket, initWebSocket, websocketSend } from '@/utils/webSocket'
import dayjs from 'dayjs'
import { aiAPI } from '@/api/common'
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

// 管理员头像
let avatar_admin
getAdminAvatar()
async function getAdminAvatar() {
  let res = await getPageAPI('users', {
    page: 1,
    limit: 1,
    sort: 'id',
    order: 'asc',
  })
  avatar_admin = res.data.list[0].image
}

const tableName = 'chat'
const config_role = {
  shape: 'corner',
  variant: 'outlined', // filled, outlined
  isMarkdown: true,
  avatarSize: '36px',
}

const list = ref([])
fetchData()
async function fetchData() {
  let res = await getListAPI(tableName, {
    userid,
    sort: 'addtime,id',
    order: 'desc,desc',
    limit: 100,
  })
  if (res.data.total == 0) {
    await sendHello()
    return
  }
  res.data.list.reverse()
  let lastTime
  let lastIndex = res.data.list.length - 1
  let newList = res.data.list.map((item, index) => {
    let { id, ask, reply, uname, uimage, type } = item
    let time = handleTime(lastTime, item.addtime)
    lastTime = item.addtime
    let typing = index === lastIndex

    // user
    if (ask) {
      return {
        ...config_role,
        key: id,
        time,
        content: compileMedia(type, ask),
        avatar: getFirstFilePath(uimage),
        placement: 'end',
        variant: 'filled',
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
        placement: 'start',
        variant: 'outlined',
        typing,
        isFog: true,
      }
    }

    // admin
    return {
      ...config_role,
      key: id,
      time,
      content: compileMedia(type, reply),
      // uimage不存在，说明是管理员的招呼语
      avatar: getFirstFilePath(uimage),
      placement: 'start',
      variant: 'outlined',
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
  type == 1 && (senderValue.value = '')
  try {
    // 文本和表情
    let data = {
      userid,
      ask: content,
      uname,
      uimage,
      type,
    }

    await addAPI(tableName, data)

    if (aiType.value == 'ai') {
      // loading
      let loadingData = {
        ...config_role,
        key: 'loading',
        userid,
        content: '正在思考中...',
        type: 1,
        uname: 'ai',
        loading: true,
        placement: 'start',
        avatar: avatart_ai,
      }
      await fetchData()
      list.value.push(loadingData)

      
      let { data: reply } = await aiAPI('baidu', content)     
      let aiReplyData = {
        userid,
        reply,
        type: 1,
        uname: 'ai',
      }
      await addAPI(tableName, aiReplyData)
      await fetchData()
    } else {
      websocketSend(content)
      await fetchData()
    }
  } catch (error) {
    // 错误提示
    if (aiType.value == 'ai') {
      let loadingData = {
        ...config_role,
        key: 'error',
        userid,
        content: '很抱歉，出现错误了！',
        type: 1,
        uname: 'ai',
        placement: 'start',
        avatar: avatart_ai,  
        typing: true,
      }
      await fetchData()
      list.value.push(loadingData)
    }
  }
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

initWebSocket(adminid, userid, fetchData)
onBeforeUnmount(() => {
  closeWebSocket()
})

// ----------------------------------
// ------------ AI -----------------
// ----------------------------------
const aiType = ref('ai') // ai | admin
function switchAi() {
  aiType.value = aiType.value == 'ai' ? 'admin' : 'ai'
  sendHello()
}
async function sendHello() {
  let data
  let type = 1
  if (aiType.value == 'ai') {
    data = {
      userid,
      reply: '我是您的智能助手小搏，请问有什么可以帮您！',
      type,
      uname: 'ai',
    }
  } else {
    data = {
      userid,
      reply: '您好，在线客服很高兴为您服务！',
      type,
      uname: 'admin',
      uimage: avatar_admin,
    }
  }
  await addAPI(tableName, data)
  fetchData()
}
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
          <el-button @click="openFileDialog" icon="Link"></el-button>
          <el-button @click="switchAi">
            <el-icon v-if="aiType == 'ai'"><Service /></el-icon>
            <el-icon v-else>
              <svg
                t="1755134973697"
                class="icon"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="5898"
                width="64"
                height="64"
              >
                <path
                  d="M194.220441 516.340177c0-7.188104-6.289591-167.123411 5.391078-290.219686 6.289591-66.489959 39.53457-126.690328 93.445348-168.920437C351.460209 11.375893 426.935298-8.391392 500.613361 3.289276c81.764679 13.477694 152.747203 60.200368 191.38326 125.791815l-85.358731 49.418213c-23.361337-40.433083-69.185498-69.185498-121.29925-77.272115-46.722674-7.188104-94.343861 5.391078-131.182892 34.143493-32.346467 25.158363-52.113752 61.098881-55.707804 99.734938-10.782156 116.806685-4.492565 275.843479-4.492564 277.640505l-99.734939 3.594052z"
                  fill="currentColor"
                  p-id="5899"
                ></path>
                <path
                  d="M189.97946 514.947482l38.842715-65.771149 314.120131 185.506986-38.83373 65.762164zM934.595121 475.008581l-86.257244-48.5197c22.462824-40.433083 25.158363-94.343861 5.391077-143.762074-17.071746-44.027135-52.113752-79.069141-95.242374-96.140887-38.636057-15.27472-79.069141-14.376207-114.111146 1.797026-106.923042 49.418213-240.801474 134.776944-242.598499 135.675457l-53.012265-83.561705c5.391078-3.594052 141.066535-89.851296 253.380655-141.965048 60.200368-28.752415 128.487353-29.650928 193.180287-4.492565 69.185498 27.853902 123.994789 82.663192 150.950177 151.848691 29.650928 77.272115 25.158363 162.630846-11.680668 229.120805z"
                  fill="currentColor"
                  p-id="5900"
                ></path>
                <path
                  d="M344.999901 604.053012l1.509502-364.796262 76.373601 0.31448-1.509501 364.796262zM753.095503 859.572128v-98.836426c46.722674 0 94.343861-25.158363 127.58884-66.489959 29.650928-36.839031 43.128622-84.460218 36.839032-131.182892-5.391078-41.331596-26.056876-75.475089-58.403343-98.836426-96.140887-67.388472-236.308909-142.863561-238.105935-143.762074l46.722674-87.155757c6.289591 3.594052 147.356126 78.170628 247.989578 150.051665 54.809291 38.636057 89.851296 97.0394 98.836425 165.326385 9.883643 73.678063-10.782156 149.153152-57.504829 206.657981-52.113752 65.591446-128.487353 104.227503-203.962442 104.227503z"
                  fill="currentColor"
                  p-id="5901"
                ></path>
                <path
                  d="M348.683804 413.640146L666.793333 235.10562l37.378139 66.597781-318.109529 178.534525zM567.10332 1024c-13.477694 0-26.955389-0.898513-40.433083-2.695539-81.764679-12.579181-153.645716-59.301855-192.281774-124.893301l85.358731-50.316726c23.361337 40.433083 69.185498 69.185498 122.197763 77.272114 46.722674 7.188104 94.343861-5.391078 131.182892-34.143492 32.346467-25.158363 52.113752-61.098881 55.707804-99.734939 9.883643-116.806685 3.594052-275.843479 3.594052-277.640505l98.836425-3.594052c0 7.188104 6.289591 167.123411-4.492564 290.219687-5.391078 66.489959-38.636057 126.690328-92.546835 168.920436-48.5197 36.839031-107.821555 56.606317-167.123411 56.606317z"
                  fill="currentColor"
                  p-id="5902"
                ></path>
                <path
                  d="M473.118864 390.75502l38.618087-65.89694 314.74909 184.446741-38.618087 65.89694zM324.50482 949.423424c-30.549441 0-61.997394-6.289591-91.648322-17.970259-69.185498-26.955389-123.994789-82.663192-151.84869-151.84869C51.35688 702.33236 54.950932 616.973629 92.688476 551.382182l86.257245 48.5197c-22.462824 40.433083-24.25985 94.343861-5.391078 143.762074 17.071746 44.027135 52.113752 79.069141 96.140887 96.140887 38.636057 15.27472 79.069141 14.376207 114.111146-2.695539C489.831205 786.792578 623.709636 701.433847 625.506662 700.535334l53.012265 83.561705c-5.391078 3.594052-141.066535 89.851296-252.482142 142.863561-32.346467 14.376207-66.489959 22.462824-101.531965 22.462824z"
                  fill="currentColor"
                  p-id="5903"
                ></path>
                <path
                  d="M596.93395 783.962262l0.413316-364.796262 76.373602 0.089852-0.422301 364.796262zM362.242365 790.38663c-6.289591-2.695539-148.254639-76.373602-250.685116-145.5591C55.849445 607.089986 19.908926 548.686644 9.126771 480.399659c-11.680668-73.678063 8.086617-149.153152 54.809291-207.556494C115.1513 207.251718 190.626389 167.717148 266.999991 166.818635l1.797026 98.836426c-46.722674 0.898513-94.343861 26.056876-126.690328 68.286985-28.752415 36.839031-41.331596 85.358731-34.143492 131.182892 6.289591 40.433083 27.853902 75.475089 60.200368 97.0394C264.304452 628.654297 405.370987 701.433847 407.168013 702.33236l-44.925648 88.05427z"
                  fill="currentColor"
                  p-id="5904"
                ></path>
                <path
                  d="M318.188274 723.016128l315.108496-183.826766 38.48331 65.977806-315.108496 183.817782z"
                  fill="currentColor"
                  p-id="5905"
                ></path>
              </svg>
            </el-icon>
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
