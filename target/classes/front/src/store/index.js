/**
 * @description pinia状态管理
 *  ref() 就是 state 属性
 *  computed() 就是 getters
 *  function() 就是 actions
 *  要让 pinia 正确识别 state，你必须在 setup store 中返回 state 的所有属性
 */

import { ref } from 'vue'
import { defineStore } from 'pinia'

import defaultAvatar from '@/assets/img/avatar.jpeg'
import { getAvatar } from '@/utils'
import { getSessionAPI } from '@/api/login'
import { clearFilePath } from '@/utils/getFilePath'
import { roleList } from '@/utils/role'

export const useUserInfo = defineStore('userinfo', () => {
  // 是否已经登录
  const userInfo = ref({
    avatar: defaultAvatar,
    username: '游客',
    isLogin: false,
  })

  // 设置登录状态
  function setUserInfo(newUserInfo) {
    userInfo.value = newUserInfo
  }

  // 退出登录
  function logOut() {
    userInfo.value = {
      avatar: defaultAvatar,
      username: '游客',
      isLogin: false,
    }
  }

  initUserInfo()
  // 初始化
  async function initUserInfo() {
    let sessionTable = localStorage.getItem('sessionTable')

    if (!sessionTable) {
      return
    }

    let res = await getSessionAPI(sessionTable)

    // 获取图像路径
    let [avatar, username] = getAvatar(res.data, sessionTable)

    userInfo.value = {
      avatar,
      username,
      isLogin: true,
    }

    // 缓存用户全部信息
    localStorage.setItem('userForm', JSON.stringify(res.data))
    // 缓存用户id
    localStorage.setItem('userid', res.data.id)
    // 用户头像
    localStorage.setItem('useravatar', clearFilePath(avatar))
    // 用户名
    localStorage.setItem('username', username)
    // 角色名
    localStorage.setItem('role', roleList.find(item => item.tableName == sessionTable).roleName)    
  }
  return {
    userInfo,
    setUserInfo,
    logOut,
  }
})
