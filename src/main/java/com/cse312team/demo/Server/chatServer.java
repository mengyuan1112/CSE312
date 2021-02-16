package com.cse312team.demo.Server;

import com.cse312team.demo.Model.Chat;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@ServerEndpoint("/chat")
public class chatServer {
    private static Map<String, Session> onlineSession = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        onlineSession.put(session.getId(), session);
        broadCastMessage(Chat.JOIN);
    }

    @OnMessage
    public void onMessage(Session session, String message){
        broadCastMessage(Chat.SPEAKING + message);
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

    private static void broadCastMessage(String message){
        onlineSession.forEach((id,session)->{
            try{
                session.getBasicRemote().sendText(message);
            }catch(IOException e){
                e.printStackTrace();
            }
        });
    }
}
