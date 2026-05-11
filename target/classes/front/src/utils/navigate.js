import router from '@/router/index'
/**
 * @@description 跳转详情页
 * @param { String } tableName 表名,
 * @param { String || Number } id 详情id
 * @param { String || Undefined } centerType 是否后台操作
 */
export function toDetailPage(tableName, id, centerType) {
  let path = `/index/${tableName}Detail`

  let query = {
    id,
  }

  if (centerType) {
    query.centerType = centerType
  }

  router.push({ path, query })
}

/**
 * @description 跳转到列表页
 * @param { String } tableName 表名,
 * @param { String || Undefined } centerType 是否后台操作
 */
export function toListPage(tableName, centerType) {
  let path = `/index/${tableName}`
  let query = {}
  if (centerType) {
    query.centerType = centerType
  }
  router.push({ path, query })
}

/**
 * @description 跳转到登录页面登录
 */

export function toLoginPage() {
  // 如果当前已经是登录页面，不需要跳转
  let isLoginPage = /#\/login/.test(location.hash)
  if (isLoginPage) {
    return
  }

  router.navigate('/login', {
    state: {
      // 带一个flag：登录成功后，跳回上一个页面
      toLastPage: true,
    },
  })
}

/**
 * @description 新窗口打开阿里支付界面
 * @param {*} data
 */
export function toAlipayPage(data) {
  var newWin = window.open('_blank')
  newWin.document.querySelector('body').innerHTML = data

  const div = document.createElement('div')
  div.innerHTML = data
  newWin.document.body.appendChild(div)

  // 手动触发表单提交 --> 跳转到支付界面
  newWin.document.forms[0].submit()
}
