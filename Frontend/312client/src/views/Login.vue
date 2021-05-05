<template>
<div >

  <h1 style="font-size: 150%;">Login Page</h1>
    <label label = "userName">Username: </label><br>
  <a-input class="inputsize" type="text" v-model = "userName"></a-input><br><br>
    <label for="password">Password: </label><br>
  <a-input class="inputsize" type="password" id="password" v-model = "password"></a-input><br><br>
    <div v-if="canSubmit === true" >
      <a-button type="submit" @click = "submitLogin">Login</a-button>
    </div>
    <div v-else>
      <a-button disabled >Login</a-button>
    </div>
    <br>
    <router-link to="/register">I do not have an account, register Now!</router-link>
</div>
<!--  <div v-else>-->
<!--    <h1> You are Logged in already</h1>-->
<!--    <router-link to="/">Click me to go back</router-link>-->
<!--  </div>-->
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      userName:"",
      password:"",
      logStatus :"",
    }
  },
  methods:{
    submitLogin(){
      const url = "http://cse312-12.dcsl.buffalo.edu:8080/loginform";
      this.axios.post(url,{userName:this.userName,password:this.password}).then(res=>{
        if(res.data.length === 0){
          alert("You've logged in successful");
          this.$router.push('/');
          this.$store.commit("updateusername",this.userName)
          this.$store.commit("loginTrue");
        }
        else{
         alert(res.data);
        }
      })
    }
  },
  computed:{
    canSubmit(){
      if(this.userName != "" && this.password != ""){
        return true;
      }
      return false;
    }
  },
  created() {
    this.logStatus = this.$store.state.loginStatus;
    if(this.$store.state.loginStatus === true){
      alert("You are logged in already");
      this.$router.push('/');
    }
  }

}
</script>

<style scoped>
.inputsize{
  width:400px !important;
}
</style>
