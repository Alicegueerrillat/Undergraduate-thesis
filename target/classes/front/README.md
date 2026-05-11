# 基于 Vue 3 + Vite + Element-plus 的框架

| 依赖         | 版本    | 功能     |
| ------------ | ------- | -------- |
| nodjes       | >22.12     |          |
| vue          | 3.5.14  | 框架     |
| vite         | 7.1.17 | 构建工具 |
| vue-router   | 4.5.1   | 路由     |
| pinia        | 3.0.2   | 状态管理 |
| element-plus | 2.9.10  | UI组件   |
| axios        | 1.9.0   | http请求 |
| echarts      | 5.5.0   | 图表     |
| animate.css  | 4.1.1   | 动画样式 |
| dayjs        | 1.11.11 | 时间处理 |
| sass | 1.93.2 | css预处理 |

##### 1. 建议使用vscode编辑器 + Vue.volar + esbenp.prettier-vscode

``````json
{
  "recommendations": [
    // Vue 3 的语言支持
    "Vue.volar",
    // 使用 Prettier 的代码格式化程序
    "esbenp.prettier-vscode"
  ]
}
``````

##### 2. 命令
``` bash
# 安装依赖
npm install

# 启动
npm run serve

# 打包
npm run build
```

---

## 目录结构

```
├── dist/                    # 构建输出目录
├── public/                  # 静态资源目录
├── src/                     # 源代码目录
│   ├── api/                 # API接口定义
│   ├── assets/              # 静态资源文件
│   ├── components/          # 公共组件
│   ├── config/              # 模块文件
│   ├── router/              # 路由配置
│   ├── store/               # 状态管理
│   ├── style/               # 样式文件
│   ├── utils/               # 工具函数
│   └── views/               # 页面视图
│       └── center/          # 个人中心
│       └── detail/          # 详情页面
│       └── home/            # 首页页面
│       └── layout/          # 布局页面
│       └── list/            # 列表页面
│       └── login/           # 登录页面
│       └── register/        # 注册页面
│       └── updatePassword   # 修改密码页面
│       └── forgetPassword   # 忘记密码页面
├── index.html               # 入口HTML文件
├── package.json             # 项目依赖配置
├── vite.config.js           # Vite配置文件
└── jsconfig.json            # JavaScript配置文件

