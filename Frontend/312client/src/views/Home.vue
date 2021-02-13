<template>
  <div id="Home">
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
  components: {
    // HelloWorld,
    NavigationBar,
    Functionality,
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
