/**
 * @description 前台独有
 */

import http from '@/utils/http.js'

/**
 * 轮播图1
 * @returns
 */
export function getBannerAPI() {
  return http({
    url: 'config/list',
    method: 'get',
    params: {
      page: 1,
      limit: 99,
      type: '1',
    },
  })
}

/**
 * @description 智能推荐1 【热门】 接口（前台）
 * @param { string } tableName 表名
 */
export function getAutoSort1API(tableName, params = {}) {
  return http({
    url: tableName + '/autoSort',
    method: 'get',
    params: {
      page: 1,
      limit: 6,
      ...params,
    },
  })
}

/**
 * @description 智能推荐2 【推荐】 需要登录，如果没有登录，降级使用autoSort 接口（前台）
 * @param { string } tableName 表名
 */
export function getAutoSort2API(tableName, params = {}) {
  return http({
    url: tableName + '/autoSort2',
    method: 'get',
    params: {
      page: 1,
      limit: 6,
      ...params,
    },
  })
}


/**
 * @description orders的列表
 */
export function getOrdersListAPI(params = {}) {
  return http({
    url: 'orders/mch/list',
    method: 'get',
    params: {
      page: 1,
      limit: 1000,
      sort: 'addtime',
      order: 'desc',
      ...params,
    },
  })
}