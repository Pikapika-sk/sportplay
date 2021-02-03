import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../components/Login.vue"
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import UserList from '../components/admin/UserList.vue'
Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    component:Login
  },
  {
    path:"/login",
    component: Login
  },
  {
    path:"/home",
    component:Home,
    redirect:"/welcome",
    children:[
      {
        path:"/welcome",
        component:Welcome,
      },
      {
        path:"/user",
        component:UserList,
      }
    ]
  },
 
]

const router = new VueRouter({
  routes
})
// 挂载路由导航
router.beforeEach((to,from,next)=>{
  if(to.path=='/login')return next();
  const userFlag = window.sessionStorage.getItem("user")//取出当前用户做判断 
  if(!userFlag)return next('/login');
  next();
})

export default router
