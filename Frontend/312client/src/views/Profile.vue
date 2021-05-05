<template>

  <div>
      <a-avatar :size="64" icon = "user"/>
<!--    <br><br><br>-->
<!--    <input type="text"><button>Update Name</button>-->
    <br><br><br>
    <input type="text" v-model="password"><button @click="updatePassword">Update Password</button>
    <br><br><br><br><br>
    <router-link to = "/"><a>Return to Homepage</a></router-link>
  </div>
</template>

<script>
export default {
name: "Profile",
  data(){
  return{
    password:""
  }
  },
  methods:{
    updatePassword:function(){
      const url = "http://cse312-12.dcsl.buffalo.edu:8080/updateProfile";
      this.axios.post(url,{userName:this.$store.state.username, password:this.password}).then(res=>{
        if (res.data === true){
          alert(" you have changed your password, please login with your password");
          this.$router.push('/login');
        }
        console.log("password update");

      })
    }
  }
}
</script>

<style scoped>

</style>
