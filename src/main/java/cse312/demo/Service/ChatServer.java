package cse312.demo.Service;

import com.alibaba.fastjson.JSON;
// import cse312.demo.Dao.ChatDao;
import cse312.demo.Model.Chat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@ServerEndpoint("/chat/{username}")
public class ChatServer {
    @Autowired
//     private ChatDao chatdao;

    private static Map<String, Session> onlineSession = new HashMap<>();

    private static void broadCastMessage(String message){
        onlineSession.forEach((username,session)->{
            try{
                session.getBasicRemote().sendText(message);
                System.out.println("Message sending to: " + username);
            }catch(IOException e){
                e.printStackTrace();
            }
        });
    }

    private void sendToMessage(String toUsername, String message){
        onlineSession.forEach((username,session)->{
            try{
                if(username.equals(toUsername)){
                    session.getBasicRemote().sendText(message);
                    System.out.println("Message sending to: " + username);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        });
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        onlineSession.put(username, session);
        String broad = Chat.jsonStr("text", username, "All", Chat.JOIN);
        broadCastMessage(broad);
    }

    @OnMessage
    public void onMessage(Session session, String jsonStr){
        Chat chat = JSON.parseObject(jsonStr,Chat.class);
//        System.out.println(chat.getMessageType() + " " + chat.getFromUsername() + " " + chat.getToUsername() + " " + chat.getMessage());
        String message = Chat.jsonStr(chat.getMessageType(), chat.getFromUsername(), chat.getToUsername(),chat.getMessage());
        sendToMessage(chat.getFromUsername(), message);
        sendToMessage(chat.getToUsername(), message);
//        broadCastMessage(Chat.jsonStr(chat.getMessageType(), chat.getFromUsername(), chat.getToUsername(), chat.getMessage()));
        //chatdao.saveMessage(chat);
    }

    @OnClose
    public void onClose(@PathParam("username") String username){
        onlineSession.remove(username);
        String broad = Chat.jsonStr("text", username, "All", Chat.LEAVE);
        broadCastMessage(broad);
        System.out.println(username + "is leaving");
    }

    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }


}
