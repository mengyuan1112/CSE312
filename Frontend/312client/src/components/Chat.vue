<template>
  <div class="container">
    <div class="header">
      <h1>ChatBox</h1>
      <i> Welcome! User:</i>
      <p id="username">{{username}}</p>
    </div>
    <div class="body">
      <div class="user_message" v-for="(message,index) in messageContent" :key="index">
        {{message}}
      </div>
    </div>
    <div class="footer">
      <input type="text" id="msg" v-model="msg">
      <button @click="sendText()">Send</button>
      <!--        <button onclick="sendImg()" id="file">File</button>-->
      <input type="file" id="file" @change="sendImg()">
    </div>


  </div>

</template>

<script>

export default {
name: "Chat",
  data(){
  return{
    msg:"",
    webSocket:null,
    messageContent:[12,1,2],
    img:"",
    username:null,
  }
  },
  methods: {
    getWebSocket: function () {
      this.webSocket = new WebSocket('ws://localhost:8080/chat');
      this.webSocket.onopen = function () {
        console.log('WebSocket Connected');
      };
      this.webSocket.onmessage = this.websocketOnMessage
      this.webSocket.onclose = function () {
        console.log('WebSocket Connection Closed');
      };
     this.webSocket.onerror = function () {
        console.log('WebSocket Error Occur');

      };
    },

    sendText: function () {
      const message = this.msg;

      if (message) {
        const obj = {messageType: "text", username1:this.$store.state.username, username2: this.$store.state.chatWith, message: message};
        this.webSocket.send(JSON.stringify(obj));
        this.msg = "";
      }
    },

    sendImg:function() {
      const files = document.querySelector("#file").files
      if(files.length>0){
        let s;
        const username = this.username;
        var fileReader = new FileReader();
        fileReader.readAsDataURL(files[0])
        fileReader.onload=function (e) {
          s =  JSON.stringify({messageType: "image", username: username, message:e.target.result});
          // console.log(e.target.result)
        }
        this.webSocket.send(s)

      }
      // console.log("hi img");
    },

    websocketOnMessage: function (event) {
      console.log('WebSocket getting message：%c' + event.data, 'color:green');
      const message = JSON.parse(event.data) || {};
      if (message.messageType === "text") {
        console.log("text");
        this.messageContent.push(message.message);
      } else if (message.messageType === "image") {
        this.messageContent.push(message.message);
      // $messageContainer.append('<div>' + '<img width="150px" src=' + message.message + '>' + '</div>');
      console.log("received image");

      }

    },

  },


  created() {
    this.getWebSocket();
    this.username = this.$store.state.username;
  }
}
</script>

<style scoped>
*{
  margin: 0;
  padding: 0;
  font-family: sans-serif;
  box-sizing: border-box;
  text-align: left;

}

body
{
  height: 100vh;
  background-color: #f8f8f8;
  display: flex;
  justify-content: center;
  align-items: center;
}

.container{
  width: 450px;
  height: 80vh;
  display: flex;
  flex-direction: column;
  box-shadow: 2px 2px 20px rgba(0,0,0,0.4);
  background-color: dodgerblue;
}
.header h1{
  color: white;
  padding: 15px;
}

.body{
  flex: 1;
  color: white;
  background-color: rgba(0,0,0,0.7);
  padding: 20px 30px;
}
.message{
  background-color: dodgerblue;
  padding: 10px;
  color: white;
  width: fit-content;
  border-radius: 10px;
  margin-bottom: 15px;
}

.user_message{
  margin-left: auto;
  background-color: white;
  color: black;
}

.footer form{
  display: flex;
}
form input{
  flex: 1;
  height: 40px;
  border: none;
  outline: none;
  padding-left: 5px;
  font-size: 16px;
}

form button{
  width: 100px;
  font-size: 18px;
  border: none;
  outline: none;
  background-color: dodgerblue;
  color: white;
  cursor: pointer;
}
form button:hover{
  background-color: blue;
  transition: 0.2s ease;
}
</style>