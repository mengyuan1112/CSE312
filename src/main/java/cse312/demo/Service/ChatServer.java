package cse312.demo.Service;

import com.alibaba.fastjson.JSON;
import cse312.demo.Model.Chat;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@ServerEndpoint("/chat")
public class ChatServer {
    private static Map<String, Session> onlineSession = new HashMap<>();

    private static void broadCastMessage(String message){
        onlineSession.forEach((id,session)->{
            try{
                session.getBasicRemote().sendText(message);
            }catch(IOException e){
                e.printStackTrace();
            }
        });
    }

    @OnOpen
    public void onOpen(Session session) {
        onlineSession.put(session.getId(), session);
        broadCastMessage(Chat.JOIN);
    }

    @OnMessage
    public void onMessage(Session session, String jsonStr){
        Chat chat = JSON.parseObject(jsonStr,Chat.class);
        broadCastMessage(Chat.jsonStr(Chat.SPEAKING,chat.getUserName(),chat.getMessage()));
    }

    @OnClose
    public void onClose(Session session){
        onlineSession.remove(session.getId());
        broadCastMessage(Chat.LEAVE);
    }

    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }


}