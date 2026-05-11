/**
 * @description: 路由页面列表
 */

const ListPage = () => import("@/views/list/list.vue");

const routes = [
  {
    path: '/',
    component: () => import('@/views/layout/layout.vue'),
    redirect: '/login',
    children: [
      {
        path: '/home',
        component: () => import('@/views/home/home.vue'),
      },
      {
        path: '/center',
        component: () => import('@/views/center.vue'),
        meta: {
          title: '个人信息'
        },
      },
      {
        path: '/updatePassword',
        component: () => import('@/views/updatePassword.vue'),
        meta: {
          title: '修改密码',
        },
      },
      {
        path: '/config/:type',
        component: ListPage,
      },
      {
        path: '/xuesheng',
        component: ListPage
      },  
      {
        path: '/jiaoshi',
        component: ListPage
      },  
      {
        path: '/xueyuan',
        component: ListPage
      },  
      {
        path: '/zhuanye',
        component: ListPage
      },  
      {
        path: '/banji',
        component: ListPage
      },  
      {
        path: '/kechengleixing',
        component: ListPage
      },  
      {
        path: '/kechengxinxi',
        component: ListPage
      },  
      {
        path: '/kechengxuanke',
        component: ListPage
      },  
      {
        path: '/ketangkaoqinfaqi',
        component: ListPage
      },  
      {
        path: '/ketangkaoqinqiandao',
        component: ListPage
      },  
      {
        path: '/qingjiaxinxi',
        component: ListPage
      },  
      {
        path: '/xuexijindu',
        component: ListPage
      },  
      {
        path: '/chengjixinxi',
        component: ListPage
      },  
      {
        path: '/kechengkaoqin',
        component: ListPage
      },  
      {
        path: '/kaoqinshensu',
        component: ListPage
      },  
      {
        path: '/kaoqinyujing',
        component: ListPage
      },  
      {
        path: '/scheduletime',
        component: ListPage
      },  
      {
        path: '/kebiaoxinix',
        component: ListPage
      },  
      {
        path: '/tousufankui',
        component: ListPage
      },  
      {
        path: '/jiaoxuetongzhi',
        component: ListPage
      },  
      {
        path: '/chat',
        component: ListPage
      },  
      {
        path: '/storeup',
        component: ListPage
      },  
      {
        path: '/users',
        component: ListPage
      },  
    ],
  },
  {
    path: '/login',
    component: () => import('@/views/login/login.vue'),
    meta: {
      title: "登录",
    },
  },
  {
    path: '/register',
    component: () => import('@/views/register/register.vue'),
    meta: {
      title: "注册",
    },
  },
  {
    path: '/forgetPassword',
    component: () => import('@/views/forgetPassword.vue'),
    meta: {
      title: '忘记密码',
    },
  },
]


export default routes