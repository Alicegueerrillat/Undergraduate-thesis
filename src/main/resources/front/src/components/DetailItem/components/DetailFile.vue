<script setup>
import { commonTableAPI } from '@/api/common'
import { downloadFile } from '@/utils'
import { inject, computed } from 'vue'

defineOptions({
  inheritAttrs: false,
})
const { value, label, column, data } = defineProps(['value', 'label', 'column', 'data'])
let { centerType } = inject('detail')

function downloadFileEvent(file) {
  if (hasEncrytion && !isDecrypted.value) {
    ElMessage.error('请先解密文件')
    return
  }

  let hasAuth = getAuth()
  if (!hasAuth) {
    ElMessage.error('请先开通会员')
    return
  }
  downloadFile(file)
}

function getAuth() {
  if (column.formatValidation == '会员') {
    let userForm = JSON.parse(localStorage.getItem('userForm'))
    // 未登录
    if (!userForm) {
      return false
    }

    // 非会员 + 自己的 + 带支付
    if (userForm.vip != '是' && centerType && data.ispay == '未支付') {
      return false
    }

    // 非会员 + 不是自己的
    if (userForm.vip != '是' && !centerType) {
      return false
    }
  }

  return true
}

// ----------------------------------
// ---------- 文件加密 ---------------
// ----------------------------------
let { encryption, type } = column
let hasEncrytion = encryption != '' && encryption != '无' && type == '文'

async function decryptionEvent() {
  try {
    let fileName = value

    // 文件名: 1764898779751_610_encrypt.png 带_encrypt关键词是已经加密过的
    if (!/_encrypt/.test(fileName)) {
      throw new Error('文件未加密，无须解密')
    }

    let { file: unlockedFileName } = await commonTableAPI({
      url: 'file/decrypt',
      method: 'post',
      params: {
        fileName: fileName.replace('upload/', ''),
        type: encryption,
      },
    })

    data[column.columnName] = unlockedFileName
    ElMessage.success('解密成功')
  } catch (error) {
    ElMessage.error(error.message || error.msg || '出错了')
  }
}

// 是否已解密
const isDecrypted = computed(() => {
  return hasEncrytion && value && !/_encrypt/.test(value)
})
</script>

<template>
  <div class="detail-text">
    <span class="label">{{ label }}</span>

    <template v-if="value">
      <el-link type="primary" @click="downloadFileEvent(value)">下载</el-link>
      <el-button
        v-if="hasEncrytion"
        style="margin-left: 20px"
        size="small"
        icon="Unlock"
        plain
        :disabled="isDecrypted"
        @click="decryptionEvent"
      >
        {{ isDecrypted ? '未加密' : '解密' }}
      </el-button>
    </template>
    <el-text v-else type="info">没有文件</el-text>
  </div>
</template>
