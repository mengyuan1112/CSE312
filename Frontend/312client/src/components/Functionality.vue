<template>
  <div class = "toleft">
    <a-dropdown :trigger = "['click']">
      <a class="ant-dropdown-link" @click = "e=>e.preventDefault()">
        <a-avatar :size="84" icon = "user"/>

      </a>
      <a-menu slot="overlay">
        <a-menu-item @click = "alertLogout">
          <br><router-link to="/login" >Log Out</router-link>
        </a-menu-item>
        <a-menu-item>
          <br><router-link to="/login"><span>Switch Account</span></router-link>
        </a-menu-item>
        <a-menu-item>
          <br><router-link to="/profile"><a>Edit My Profile</a></router-link>
        </a-menu-item>
      </a-menu>
    </a-dropdown>
  </div>
</template>



<script>
import axios from "axios"
export default {
  name: "Functionality",
  data(){
    return{
      username:"",
    }
  },
  methods:{
    alertLogout:function(){
      const url = "http://localhost:8080/logout";
      axios.post(url,{userName:this.username})
      this.$store.commit("loginFalse");
      this.$store.commit("clearUsername")
      this.username = this.$store.state.username;
      // alert("You have been logged out successfully");
      this.$router.push('/login').catch(()=>{});
    }
  },
  created(){
    this.username = this.$store.state.username;
  }
}
</script>

<style scoped>


.toleft{
  text-align:left;
  padding-left:12%;
  padding-top:3%;
}

.ant-dropdown{
  font-size:28px;
}

</style>