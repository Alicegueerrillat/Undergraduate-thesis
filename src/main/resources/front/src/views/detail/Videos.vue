<script setup>
/**
 * @description 视频
 *
 * videoList 视频列表
 * item.src 视频路径
 * item.canPlay 播放权限
 * item.errorMsg 权限提示
 */

import { inject } from 'vue'

let { videoList } = inject('detail')
function playEvent(event, item) {
  if (!item.canPlay) {
    ElMessage.error(item.errorMsg)
    event.target.pause()
    return
  }
}
</script>

<template>
  <div class="videos-view" v-if="videoList.length">
    <video
      v-for="item in videoList"
      :key="item.src"
      :src="item.src"
      controls
      :controlslist="item.canPlay ? '' : 'nodownload'"
      @play="playEvent($event, item)"
    ></video>
  </div>
</template>
<style>
.videos-view {
  width: 100%;
  margin-top: 20px;
  video {
    height: 400px;
    width: 100%;
  }
}
</style>
