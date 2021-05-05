/* eslint-disable */
<template>
<div>
  <h1 style="font-size: 150%;">Register Page</h1>

<!--    <label >Account Name:</label><br>-->
<!--    <a-input type="text" v-model="accountName"></a-input><br><br>-->
<!--    <button class = "blueunderline" >Check Account Name</button><br><br>-->

    <label for="userName">Username:</label><br>
    <a-input class="inputsize" type="text" id="userName" v-model = "userName"></a-input><br><br>
    <label for="password">Password:</label><br>
    <a-input class="inputsize" type="password" id="password" v-model = "password"></a-input><br><br>
    <p v-show = "validPassword">Password must be less than 15 characters</p>
    <label for="repassword">Check Password:</label><br>
    <a-input class="inputsize" type="password" id="repassword" v-model = "repassword"></a-input><br><br>
    <p v-show = "checkPassword">Password you enter is not the same</p>

  <div v-if="canSubmit === true" >
      <a-button type="submit" @click = "submitRegister">Register Now</a-button>
    </div>
    <div v-else>
      <a-button disabled >Register Now</a-button>
    </div>
    <br>
  <router-link to="/login">I have an account, login now</router-link>
</div>
</template>

<script>
/* eslint-disable */
export default {
  name: "Register",
  data(){
    return{
      accountName:"", // accountName == personName in java
      userName:"",
      password:"",
      repassword:"",
    }
  },
  methods:{
    submitRegister(){
      const url = "http://cse312-12.dcsl.buffalo.edu:8080/registerform";
      this.axios.post(url,{personName:this.accountName,userName:this.userName,password:this.password}).then(res=>{
        if(res.data === false){
          alert("userName in use");
          return;
        }
        else{
          alert("registered successfully! Use your username and password to login.")
          this.$router.push("/login");
        }
      })
    }
  },
  computed: {
    canSubmit() {
      if (this.userName === "" || this.password === "") {
        return false;
      }
      if (this.password.length >= 15) {
        return false;
      }
      if (this.password != this.repassword) {
        return false
      }
      return true;
    },
    validPassword() {
      if (this.password.length <= 15) {
        return false;
      }
      return true;
    },
    checkPassword() {
      if (this.password != this.repassword) {
        return true
      }
      return false;
    }
  }
}
</script>

<style scoped>
.inputsize{
  width:400px !important;
}
</style>
