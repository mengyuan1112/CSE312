<template>

  <div id = "NavigationBarDiv"  @click = "updateOnlineUser"  >
    Online Users:{{onlineNumber}}
    <br>
    <p >Me: {{userName}}</p>
    <hr>
      <a-menu  mode = "vertical" trigger='["click"]'>
            <a-menu-item v-for = "(member,index) in onlineName" :key = "index" >
              <p @click="chatMember(member)">{{member}}</p>
            </a-menu-item>
      </a-menu>

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
      userName:"",
    }
  },
  methods:{
    updateOnlineUser:function(){
      const url = "http://localhost:8080/onlineuserfresh";
      setInterval(()=>{
        this.axios.get(url).then(res=>{
          this.onlineUser = res.data;
          this.onlineNumber = Object.keys(this.onlineUser)[0];
          this.onlineName = [];
          for (let i in Object.values(this.onlineUser)[0]){
            if (this.userName != Object.values(this.onlineUser)[0][i].userName )this.onlineName.push(Object.values(this.onlineUser)[0][i].userName);
          }
        })
      },1000)
    },
    chatMember:function(member){
      this.$store.commit("startChat",member)
        const url = "http://localhost:8080/chatHistory"
        this.$axios.post(url,{fromUser:this.$store.state.username,toUser:this.$store.state.chatWith}).then(res=>{
          let tempdata = [];
          if(!res.data){
            this.$forceUpdate()
          }
          else{
            for (let i of res.data) {
              i = JSON.parse(i);
              console.log(i);
              if(i.messageType ==="text"){
                if(i.toUsername === "All"){
                  tempdata.push('Broadcast: ' + i.fromUsername + ' ' + i.message);
                }else{
                  tempdata.push(i.fromUsername + ": " + i.message);
                }
              }
              else if (i.messageType === "image"){
                if(i.toUsername === "All"){
                  tempdata.push('Broadcast: ' + i.fromUsername + ' ' + i.message);
                }else{
                  tempdata.push(i.fromUsername + ": " + '<div>' + '<img width="300px" v-html src='+i.message+'>' + '</div>');
                }
              }
            }
          }
          this.$store.commit("updateChatHistory",tempdata)
        })
    }
  },
  computed:{
  },
  created() {
    this.updateOnlineUser();
    this.userName = this.$store.state.username
  }
}
</script>

<style scoped>
/*#NavigationBarDiv{*/
/*  text-align: left;*/
/*  padding-left:25%;*/

/*}*/

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
