package cse312.demo.Service;

import cse312.demo.Dao.ChatRepository;
import cse312.demo.Model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.*;


@Service
public class ChatService{

    final ChatRepository chatRepository;
    static Map<String, Session> onlineSession;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
        this.onlineSession = new HashMap<>();
    }

    public void insertChat(Chat chat) {
        chatRepository.save(chat);
    }

}
