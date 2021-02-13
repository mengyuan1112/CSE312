<template>
<div>
  <form action="">
    <label label = "userName">Username: </label><br>
    <input type="text" v-model = "userName"><br>
    <label for="password">Password: </label><br>
    <input type="password" id="password" v-model = "accountPassword"><br>
    <button type="submit" @click = "submitLogin">Login</button>
    <br>
    <router-link to="/register">I do not have an account, register Now!</router-link>
  </form>
</div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      userName:"",
      accountPassword:"",
    }
  },
  methods:{
    submitLogin(){
      const url = "http://localhost:8080/loginform";
      this.axios.post(url,{userName:this.userName,password:this.accountPassword}).then(res=>{
        if(res.data == false){
          alert("Incorrect username or password");
          return;
        }
        alert("You've logged in successfully");
        return;
      })
    }

  }

}
</script>

<style scoped>

</style>
