package cse312.demo.Service;

import cse312.demo.Dao.ChatRepository;
import cse312.demo.Model.Chat;
import cse312.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.*;


@Service
public class ChatService{

    final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void insertChat(Chat chat) {
        chatRepository.save(chat);
    }

    public List<Chat> getAllChat(){
        return chatRepository.findAll();
    }

}
