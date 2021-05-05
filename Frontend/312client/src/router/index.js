import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
// import Profile from "@/views/Profile";
// import App from "../App.vue"
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path:'/profile',
    name:'Profile',
    component: ()=> import('../views/Profile.vue')
  },
  {
    path:'/login',
    name:'Login',
    component: ()=>import('../views/Login.vue')
  },
  {
    path:'/register',
    name:'Register',
    component: ()=>import('../views/Register.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
