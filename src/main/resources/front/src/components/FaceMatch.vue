<script setup>
/**
 * @description 人脸识别
 */
import { ref, useTemplateRef, watch } from 'vue'

import { uploadFileAPI } from '@/api/common'
import base from '@/utils/base'
const baseUrl = base.get().url
const action = baseUrl + 'file/upload'
const VIDEO_WIDTH = 300
const VIDEO_HEIGHT = 300
const headers = {
  Token: localStorage.getItem('Token'),
}

const dialogVisible = defineModel()
const emits = defineEmits(['submit'])
const { buttonName } = defineProps({
  buttonName: {
    type: String,
    default: '人脸登录',
  },
})

const videoRef = useTemplateRef('videoRef') // 视频ref
const canvasRef = useTemplateRef('canvasRef') //画布ref
const uploadRef = useTemplateRef('uploadRef') //文件上传ref
let canvasContext = null // 画布上下文
const fileName = ref(null) // 图像文件名
const isOnCamera = ref(false) // 是否摄像中

let hideModal = () => {
  stopCamera()
  dialogVisible.value = false
}

// 调用摄像头权限
let startCamera = () => {
  !canvasContext && (canvasContext = canvasRef.value.getContext('2d'))

  // 标记已开启摄像头
  isOnCamera.value = true

  // [1] 兼容不同浏览器的getUserMedia
  if (navigator.mediaDevices === undefined) {
    navigator.mediaDevices = {}
  }
  if (navigator.mediaDevices.getUserMedia === undefined) {
    navigator.mediaDevices.getUserMedia = function (constraints) {
      let getUserMedia =
        navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia

      if (!getUserMedia) {
        return Promise.reject(new Error('当前浏览器不支持摄像头功能'))
      }

      return new Promise(function (resolve, reject) {
        getUserMedia.call(navigator, constraints, resolve, reject)
      })
    }
  }

  const constraints = {
    audio: false,
    video: {
      width: VIDEO_WIDTH,
      height: VIDEO_HEIGHT,
      transform: 'scaleX(-1)',
    },
  }

  // 调用
  navigator.mediaDevices
    .getUserMedia(constraints)
    .then(stream => {
      // 旧的浏览器可能没有srcObject

      if ('srcObject' in videoRef.value) {
        videoRef.value.srcObject = stream
      } else {
        // 避免在新的浏览器中使用它，因为它正在被弃用。
        videoRef.value.src = window.URL.createObjectURL(stream)
      }

      videoRef.value.onloadedmetadata = function (e) {
        videoRef.value.play()
      }
    })
    .catch(err => {
      ElMessage.error('没有开启摄像头权限 或浏览器版本不兼容 或http协议下默认禁止调用')
      console.log(err)
      // 标记未开启启摄像头
      isOnCamera.value = false
    })
}

// 停止调用摄像头
let stopCamera = () => {
  if (isOnCamera.value && videoRef.value) {
    const stream = videoRef.value.srcObject
    if (stream) {
      const tracks = stream.getTracks()
      tracks.forEach(track => track.stop())
    }
  }
  isOnCamera.value = false
}

// 清空画布
let clearCanvas = () => {
  !canvasContext && (canvasContext = canvasRef.value.getContext('2d'))
  canvasContext.clearRect(0, 0, VIDEO_WIDTH, VIDEO_HEIGHT)

  fileName.value = null
}

// 上传图片
function handleSuccess(res, file, fileList) {
  let fileUrl = baseUrl + 'upload/' + res.file

  // 显示到canvas
  const img = new Image()
  img.src = fileUrl
  img.setAttribute('crossOrigin', 'Anonymous')
  img.onload = function () {
    clearCanvas()
    // 将图像绘制到 canvas 上
    canvasContext.drawImage(img, 0, 0, VIDEO_WIDTH, VIDEO_HEIGHT)

    fileName.value = res.file
  }
}

// 拍照
function takePhotos() {
  // 截图到画布canvas
  canvasContext.drawImage(videoRef.value, 0, 0, VIDEO_WIDTH, VIDEO_HEIGHT)

  // 上传图片到后台
  // 画布转base64 -> base64转Blob文件 -> 上传到后端
  let base64Data = canvasRef.value.toDataURL('image/png')
  let param = new FormData()
  param.append('file', base64toFile(base64Data))
  uploadFileAPI(param).then(res => {
    fileName.value = res.file
  })
}

// 完成
function finishEvent() {
  // 交给父组件处理
  emits('submit', fileName.value)

  hideModal()
}

// base64转文件
function base64toFile(dataurl, filename = 'file') {
  let arr = dataurl.split(',')
  let mime = arr[0].match(/:(.*?);/)[1]
  let suffix = mime.split('/')[1]
  let bstr = atob(arr[1])
  let n = bstr.length
  let u8arr = new Uint8Array(n)
  while (n--) {
    u8arr[n] = bstr.charCodeAt(n)
  }
  let file = new File([u8arr], filename + '.' + suffix, {
    type: mime,
  })
  return file
}

watch(dialogVisible, () => {
  if (!dialogVisible.value) {
    canvasContext = null
    stopCamera()
  }
})
</script>

<template>
  <el-dialog
    class="yy-dialog"
    v-model="dialogVisible"
    :title="buttonName"
    destroy-on-close
    :close-on-click-modal="false"
    append-to-body
  >
    <div className="facematch-wrapper">
      <div class="face-item">
        <div>摄像区</div>
        <video
          ref="videoRef"
          id="videoCamera"
          autoPlay
          :width="VIDEO_WIDTH"
          :height="VIDEO_HEIGHT"
        ></video>
      </div>
      <div class="face-item">
        <div>已选的人脸</div>
        <canvas ref="canvasRef" :width="VIDEO_WIDTH" :height="VIDEO_HEIGHT"></canvas>
      </div>
    </div>

    <div class="facematch-footer">
      <el-button @click="uploadRef.click()">上传图片</el-button>
      <el-button @click="takePhotos" :disabled="!isOnCamera">拍照</el-button>
      <el-button @click="startCamera" v-show="!isOnCamera">打开摄像头</el-button>
      <el-button @click="stopCamera" v-show="isOnCamera">关闭摄像头</el-button>
      <el-button @click="clearCanvas">重置</el-button>
      <el-button type="primary" @click="finishEvent" :disabled="!fileName">完成</el-button>
    </div>

    <el-upload
      style="display: none"
      :action="action"
      :headers="headers"
      :on-success="handleSuccess"
      :show-file-list="false"
    >
      <span ref="uploadRef"></span>
    </el-upload>
  </el-dialog>
</template>
<style lang="scss">
.facematch-wrapper {
  display: flex;
  justify-content: center;
  gap: 20px;

  .face-item {
    border: 1px dashed;
    display: flex;
    flex-direction: column;

    div {
      text-align: center;
    }
  }
}

.facematch-footer {
  padding-top: 10px;
  width: 100%;
  display: flex;
  justify-content: flex-end;
}
</style>
