import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import Antd from "ant-design-vue"
import 'ant-design-vue/dist/antd.css';
import router from './router'
import store from './store'
import axios from "@/plugins/axios";
// import {Dropdown} from "ant-design-vue"
Vue.use(Antd)
Vue.use(axios);
// Vue.use(Dropdown)
Vue.config.productionTip = false
new Vue({
  router,
  store, // 在这里添加store代表挂在store/index.js 中的store 对象， 从而在vue 中获取store的数据
  render: h => h(App)
}).$mount('#app')
