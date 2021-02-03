import Vue from 'vue'
import App from './App.vue'
import router from './router'
import "./assets/font/iconfont.css"
import './plugins/element.js'
import './assets/css/global.css'
// 导入axios
import axios from 'axios'
// 挂载axios
Vue.prototype.$http = axios
// 设置访问根路径
axios.defaults.baseURL = "http://localhost:9000"



Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
