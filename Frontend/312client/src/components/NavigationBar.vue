<template>

  <div id = "NavigationBarDiv"  @click = "updateOnlineUser"  >
      <a-menu  mode = "horizontal" trigger='["click"]'>
        <a-sub-menu trigger="['click']">
          <span slot="title">Friend List</span>

          <a-menu-item-group title = "Online Users" class = "menuScroll" >
<!--            <a href="javascript:;" >Online Users</a>-->

<!--            <a-menu-item v-for ="(member,index) in $store.state.onlineMembers" :key ="index" >-->
            <a-menu-item v-for = "(member,index) in onlineName" :key = "index" >
              <p>{{member}}</p>
            </a-menu-item>
<!--            <a-menu-item>-->
<!--              <a href="javascript:;">Some User</a>-->
<!--            </a-menu-item>-->
          </a-menu-item-group>
        </a-sub-menu>
        <a-menu-item>
          <router-link to="/posts">
            <a> Posts </a>
          </router-link>
        </a-menu-item>
      </a-menu>
    {{onlineNumber}}
  </div>
</template>

<script>
export default {
  name: "NavigationBar",
  data(){
    return{
      onlineUser:{},
      onlineNumber: 0,
      onlineName:[],
    }
  },
  methods:{
    updateOnlineUser:function(){
      const url = "http://localhost:8080/onlineuserfresh";
      setInterval(()=>{
        this.axios.get(url).then(res=>{
          this.onlineUser = res.data;
          // console.log(this.onlineUser);
          this.onlineNumber = Object.keys(this.onlineUser)[0];
          this.onlineName = [];
          for (let i in Object.values(this.onlineUser)[0]){
            this.onlineName.push(Object.values(this.onlineUser)[0][i].personName);
          }
          // console.log(res.data);
        })
      },1000)
    },


    // seemember:function(){
    //   console.log(Object.keys(this.onlineUser)[0]);
    //
    //   console.log(Object.values(this.onlineUser)[0][0].personName);
    // }
  },
  created() {
    this.updateOnlineUser();
  }
}
</script>

<style scoped>
#NavigationBarDiv{
  text-align: left;
  padding-left:25%;

}

/*#NavigationBar{*/
/*  font-size:20px;*/
/*}*/

#itemFont{
  font-size:90px;
}

.ant-menu{
  font-size:28px;
}

.ant-menu-submenu{
  padding-right:25%;
}

.menuScroll{
  font-size:125%;
  max-height:300px;
  overflow: auto;
}

</style>
