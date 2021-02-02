import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../components/Login.vue"

import less from 'less'
Vue.use(VueRouter)
Vue.use(less)
const routes = [
  {
    path:'/',
    component:Login
  },
  {
    path:"/login",
    component: Login
  }
]

const router = new VueRouter({
  routes
})

export default router
