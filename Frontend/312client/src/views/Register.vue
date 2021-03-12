/* eslint-disable */
<template>
<div>
  <form action="">
    <label >Account Name:</label><br>
    <input type="text" v-model="accountName"><br>
<!--    <button class = "blueunderline" >Check Account Name</button><br><br>-->

    <label for="userName">Username:</label><br>
    <input type="text" id="userName" v-model = "userName"><br><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" v-model = "password"><br>
    <p v-show = "validPassword">Invalid password</p>

    <button type = "button" :disabled ="validPassword" @click = "submitRegister" >Register!</button>
    <br>
  </form>
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
    }
  },
  methods:{
    // checkAccountName:()=> {
    //   const pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    //   // var realEmail = /^[\w\-\.]+@[a-z0-9]+(\-[a-z0-9]+)?(\.[a-z0-9]+(\-[a-z0-9]+)?)*\.[a-z]{2,4}$/i;
    //   var isOk = pattern.test(this.accountName);
    //   if (isOk == false) {
    //     alert("Please Enter Correct Email!");
    //     this.accountName.focus();
    //   }
    //   return isOk;
    // }
    submitRegister(){
      const url = "http://localhost:8080/registerform";
      this.axios.post(url,{personName:this.accountName,userName:this.userName,password:this.password}).then(res=>{
        if(res.data === false){
          alert("userName in use");
          return;
        }
        alert("registered successfully! Use your username and password to login.")
      })
    }
  },
  computed:{
    validPassword(){
      if(!this.accountName)return false;
      if(!this.userName)return false;
      if(this.password.length<=12){
        return false;
      }
      return true;
    }
  }
}
</script>

<style scoped>

</style>