package cse312.demo.APIController;

import com.alibaba.fastjson.JSON;
import cse312.demo.Model.Chat;
import cse312.demo.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;


@Component
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/")
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
                if(!username.equals("null")){
                    session.getBasicRemote().sendText(message);
                    System.out.println("Message sending to: " + username);
                }
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
//                    System.out.println("Message sending to: " + username);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        });
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        if(!username.equals("null") ){
            String broad = Chat.jsonStr("text", username, "All", Chat.JOIN);
            broadCastMessage(broad);
            onlineSession.put(username, session);
        }
    }

    @OnMessage
    public void onMessage(Session session, String jsonStr){
        Chat chat = JSON.parseObject(jsonStr,Chat.class);
        chatService.insertChat(chat);
        String message = Chat.jsonStr(chat.getMessageType(), chat.getFromUsername(), chat.getToUsername(),htmlEscape(chat.getMessage()));
        if(chat.getFromUsername() != null){
            sendToMessage(chat.getFromUsername(), message);
        }
        if(chat.getToUsername() != null){
            sendToMessage(chat.getToUsername(), message);
        }
    }

    @OnClose
    public void onClose(@PathParam("username") String username){
        if(!username.equals("null")){
            onlineSession.remove(username);
            String broad = Chat.jsonStr("text", username, "All", Chat.LEAVE);
            broadCastMessage(broad);
        }
    }

    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }

    @PostMapping("/chatHistory")
    public List<String> chatHistory(@RequestBody Map<String, String> userPair){
        String fromUser = userPair.get("fromUser");
//        System.out.println("fromUser: " + fromUser);
        String toUser = userPair.get("toUser");
//        System.out.println("toUser: " + toUser);
        List<String> chatHistory = new ArrayList<>();
        List<Chat> chatHistoryDB = chatService.getAllChat();
        for(Chat tmpChat : chatHistoryDB){
            if((tmpChat.getFromUsername().equals(fromUser) && tmpChat.getToUsername().equals(toUser)) || (tmpChat.getFromUsername().equals(toUser) && tmpChat.getToUsername().equals(fromUser))){
                String tmpMessage = Chat.jsonStr(tmpChat.getMessageType(), tmpChat.getFromUsername(), tmpChat.getToUsername(),tmpChat.getMessage());
                chatHistory.add(tmpMessage);
            }
        }
        return chatHistory;
    }

    public String htmlEscape(String text){
        text = text.replace("&", "&amp");
        text = text.replace("<", "&lt");
        text = text.replace(">", "&gt");
        return text;
    }



}


