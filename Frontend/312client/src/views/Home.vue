<template>
  <div id="Home" @click ="checklogin">
    <NavigationBar/>
    <Functionality/>
    <!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->

  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import NavigationBar from "@/components/NavigationBar";
import Functionality from "@/components/Functionality";
export default {
  name: 'Home',
  data(){
    return{
      username:"",
    }
  },
  methods:{
    checklogin:function(){
      if(this.$store.state.loginStatus === false){
        alert("please log in before continuing ");
        this.$router.push('/login');
      }
    }
  },
  components: {
    // HelloWorld,
    NavigationBar,
    Functionality,
  },
  created(){
    this.username = this.$store.state.username;
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
</style>
