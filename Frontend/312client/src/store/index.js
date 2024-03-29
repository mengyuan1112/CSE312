//Note that if this product will be deployed by IE or any browser that does not support promise, then do :
// yarn add es6-promise, or npm install es6-promise --save
// https://vuex.vuejs.org/zh/installation.html for more details

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username:null,
    loginStatus:false,
    chatWith:null,
    chatHistory:null,
  },
  mutations: {
    updateusername(state,payload){
      state.username = payload;
      console.log(payload);
    },
    clearUsername(state){
      state.username=null;
    },
    loginTrue(state){
      state.loginStatus = true;
    },
    loginFalse(state){
      state.loginStatus = false;
    },
    startChat(state,payload){
      state.chatWith = payload;
    },
    endChat(state){
      state.chatWith = null;
    },
    updateChatHistory(state,payload){
      state.chatHistory = payload;
    }
  },
  actions: {
  },
  modules: {
  }
})
