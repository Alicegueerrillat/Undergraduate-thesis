/**
 * @description 权限
 */

/**
 * @description 判断 是否有 某个按钮权限
 * @param { string } tableName 菜单下的表名
 * @param { string } name 按钮名字
 * @param { boolean } isFront 是否前台,默认后台
 * @returns
 */
function isAuth(tableName, name, isFront) {
  let buttons = isFront ? getAuthButtons_front(tableName) : getAuthButtons(tableName)
  return buttons.includes(name)
}

/**
 * @description 获取某个表的按钮权限列表buttons
 * @param {*} tableName
 * @returns
 */
function getAuthButtons(tableName) {
  let buttons = []

  let roleMenu = localStorage.getItem('roleMenu')
  if (!roleMenu) {
    return buttons
  }
  roleMenu = JSON.parse(roleMenu)

  loop(roleMenu, tableName, item => {
    buttons = item.buttons
  })

  return buttons
}
/**
 * @description 前台获取某个表的按钮权限列表buttons
 * @param {*} tableName
 * @returns
 */
function getAuthButtons_front(tableName) {
  let buttons = []

  let menus = localStorage.getItem('menus')
  if (!menus) {
    return buttons
  }
  // 所有角色共享一个前台菜单frontMenu
  menus = JSON.parse(menus)
  let frontMenus = menus[0].frontMenu
  loop(frontMenus, tableName, item => {
    buttons = item.buttons
  })

  return buttons
}

/**
 * @description 递归,根据tableName找到对应的按钮权限列表
 * @param { Array } roleMenu 角色菜单列表
 * @param { String } tableName 表名
 * @param { Function } callback 回调函数
 * @returns
 */
function loop(roleMenu, tableName, callback) {
  for (let i = 0; i < roleMenu.length; i++) {
    if (roleMenu[i].tableName === tableName) {
      return callback(roleMenu[i], i, roleMenu)
    }

    // 继续子级递归
    if (roleMenu[i].child) {
      loop(roleMenu[i].child, tableName, callback)
    }
  }
}

export { isAuth, loop, getAuthButtons, getAuthButtons_front }
