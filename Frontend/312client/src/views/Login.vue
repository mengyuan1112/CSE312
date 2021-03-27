<template>
<div v-if="!logStatus">
    <label label = "userName">Username: </label><br>
    <input type="text" v-model = "userName"><br>
    <label for="password">Password: </label><br>
    <input type="password" id="password" v-model = "accountPassword"><br>
    <button type="submit" @click = "submitLogin">Login</button>
    <br>
    <router-link to="/register">I do not have an account, register Now!</router-link>
</div>
  <div v-else>
    <h1> You are Logged in already</h1>
    <router-link to="/">Click me to go back</router-link>
  </div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      userName:"",
      accountPassword:"",
      logStatus :"",
    }
  },
  methods:{
    submitLogin(){
      const url = "http://localhost:8080/loginform";
      this.axios.post(url,{userName:this.userName,password:this.accountPassword}).then(res=>{
        if(res.data == false){
          alert("Incorrect username or password");
        }
        else{
          alert("You've logged in successful");
          this.$router.push('/');
          this.$store.commit("updateusername",this.userName)
          this.$store.commit("loginTrue");
        }
      })
    }

  },
  created() {
    this.logStatus = this.$store.state.loginStatus;
  }

}
</script>

<style scoped>

</style>
