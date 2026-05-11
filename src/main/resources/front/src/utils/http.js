/**
 * @description 封装 axios 网络请求
 */

import axios from "axios";
import router from "@/router";

let hasLoginMessageBox = false // 是否已有登录提示

const http = axios.create({
  // 如果请求时间超过 `timeout` 的值，则请求会被中断
  timeout: 1000 * 60 * 5,

  // 表示跨域请求时是否需要使用凭证
  withCredentials: true,

  // `baseURL` 将自动加在 `url` 前面，除非 `url` 是一个绝对 URL
  baseURL: "/springboot3t8axj02b",

  headers: {
    "Content-Type": "application/json; charset=utf-8",
  },
});

// 请求拦截
http.interceptors.request.use(
  (config) => {
    config.headers["Token"] = localStorage.getItem("Token"); // 请求头带上token
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 响应拦截
http.interceptors.response.use(
  (response) => {
    let data = response.data;

    // 文件类型，没有code，直接成功返回
    if (response.config.responseType === "blob") {
      return data;
    }

    switch (data.code) {
      //  0是请求成功的code
      case 0:
        return data;

      // 401需要是用户没有登录
      case 401:
        localStorage.removeItems()
        if (!hasLoginMessageBox) {
          let url = response.config.url || ''
          let isSession = /\/session$/.test(url) // session接口
          let isSort2 = /\/autoSort2$/.test(url) // 智能推荐2接口
          if (!isSession && !isSort2) {
            hasLoginMessageBox = true
            ElMessageBox.confirm('需要登录，是否前往登录界面？', '登录提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning',
            })
              .then(() => {
                hasLoginMessageBox = false
                router.push({
                  path: '/login',
                  query: {
                    redirect: window.location.hash.replace(/^#/, ''), // hash去掉#
                  },
                })
              })
              .catch(() => {
                hasLoginMessageBox = false
              })
          }
        }
        return Promise.reject(data);
        break;

      default:
        return Promise.reject(data);
        break;
    }
  },
  (error) => {
    let url = error.config.url || "";
    // 根据url判断是：获取当前登录的用户信息的api url: users/session, yonghu/session
    let isSession = /\/session$/.test(url);
    if (isSession) {
      localStorage.removeItems();
    }

    return Promise.reject(error);
  }
);
export default http;
