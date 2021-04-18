<template>
  <div id="Home">

    <!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <a-layout>
      <a-layout-sider class='sider'>       <NavigationBar/></a-layout-sider>
      <a-layout class="body"><Functionality/>
<!--        <a-layout-content>Content</a-layout-content>-->
        <Chat/>
      </a-layout>
    </a-layout>
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import NavigationBar from "@/components/NavigationBar";
import Functionality from "@/components/Functionality";
import Chat from "@/components/Chat";
export default {
  name: 'Home',
  data(){
    return{
      username:"",
    }
  },
  methods:{
    // checklogin:function(){
    //
    // }
  },
  components: {
    // HelloWorld,
    NavigationBar,
    Functionality,
    Chat
  },
  created(){
    this.username = this.$store.state.username;
    if(this.$store.state.loginStatus === false){
      alert("please log in before continuing ");
      this.$router.push('/login');
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}


.sider {
  background: white;
  line-height: 70px;
  height:800px;
}
.header{
  background: white;
}
.body{
  background:white;
}
</style>
