//Note that if this product will be deployed by IE or any browser that does not support promise, then do :
// yarn add es6-promise, or npm install es6-promise --save
// https://vuex.vuejs.org/zh/installation.html for more details

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // onlineMembers:["membwwwwwwwwwwwwwssssssssssssssssssswwwer1","member2","member1","member2","member1","member2","member1","member2",],
    username:"",
    loginStatus:false,
  },
  mutations: {
    updateusername(state,payload){
      state.username = payload;
      console.log(payload);
    },
    clearUsername(state){
      state.username="";
    },
    loginTrue(state){
      state.loginStatus = true;
    },
    loginFalse(state){
      state.loginStatus = false;
    }
  },
  actions: {
  },
  modules: {
  }
})
