import Vue from 'vue'
import App from './App.vue'
import router from './router'
import "./assets/font/iconfont.css"
import './plugins/element.js'
import './assets/css/global.css'
Vue.config.productionTip = false
Vue.use(less)
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')