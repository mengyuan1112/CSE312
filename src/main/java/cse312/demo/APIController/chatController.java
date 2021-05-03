package cse312.demo.APIController;

import com.alibaba.fastjson.JSON;
import cse312.demo.Model.Chat;
import cse312.demo.Service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Controller
@ServerEndpoint("/chat/{username}")
public class chatController{

    private static ChatService chatService;

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;

    }
    static Map<String, Session> onlineSession = new HashMap<>();

    public static void broadCastMessage(String message){
        onlineSession.forEach((username,session)->{
            try{
                session.getBasicRemote().sendText(message);
                System.out.println("Message sending to: " + username);
            }catch(IOException e){
                e.printStackTrace();
            }
        });
    }

    public void sendToMessage(String toUsername, String message){
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
        System.out.println("The jsonStr: " + jsonStr);
        Chat chat = JSON.parseObject(jsonStr,Chat.class);
        chatService.insertChat(chat);
        System.out.println(chat.getMessageType() + ", " + chat.getFromUsername() + ", " + chat.getToUsername() + ", " + chat.getMessage());
//        Chat chatTmp = new Chat(chat.getMessageType(), chat.getFromUsername(), chat.getToUsername(),chat.getMessage());
//        chatRepository.save(chatTmp);
//        chatRepository.save(chat);
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


