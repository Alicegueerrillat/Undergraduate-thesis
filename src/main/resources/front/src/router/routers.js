/**
 * @description: 路由页面列表
 */

const routes = [
  {
    path: "/",
    redirect: "/index/home",
  },  
  {
    path: '/index',
    component: () => import('@/views/layout/layout.vue'),  
    children: [
      {
        path: 'home',
        component: () => import('@/views/home/home.vue'),
      },
      {
        path: 'center',
        component: () => import('@/views/center/center.vue'),
      },
      {
        path: 'storeup',
        component: () => import('@/views/storeup/storeUp.vue'),       
      },
      {
        path: ':tableName(\\w+)Detail',
        component: () => import('@/views/detail/detail.vue'),
        props: route => ({ tableName: route.params.tableName }),
        meta: {
          title: '详情',
        },
      },
      {
        path: 'kebiaoxinix',
        redirect: '/index/kebiaoxinixCourse',        
      },
      {
        path: ':tableName(\\w+)Course',
        component: () => import('@/components/TimeTable.vue'),
        props: route => ({ tableName: route.params.tableName }),
        meta: {
          title: '课程表',
        },
      },
      {
        path: ':tableName(\\w+)',
        component: () => import('@/views/list/list.vue'),
        props: route => ({ tableName: route.params.tableName }),
        meta: {
          title: '列表',
        },
      },            
    ],
  },
  {
    path: "/login",
    component: () => import("@/views/login/login.vue"),
  },
  {
    path: "/register",
    component: () => import("@/views/register/register.vue"),
  },
  {
    path: "/forgetPassword",
    component: () => import("@/views/forgetPassword.vue"),
  },
  {
    path: "/:pathMatch(.*)*",
    component: () => import("@/views/404.vue"),
  },
]


export default routes