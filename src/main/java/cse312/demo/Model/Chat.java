package cse312.demo.Model;
import com.alibaba.fastjson.JSON;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "message")

public class Chat {
    public static final String JOIN = "JOIN";
    public static final String SPEAKING = "SPEAKING";
    public static final String LEAVE = "LEAVE";

    @Id
    private String fromUsername;
    private String toUsername;

    private String message;
    private String messageType;

    public static String jsonStr(String messageType, String fromUsername, String toUsername, String message) {
        return JSON.toJSONString(new Chat(messageType, fromUsername, toUsername, message));
    }


    public Chat(String messageType, String fromUsername, String toUsername, String message) {
        this.messageType = messageType;
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
        this.message = message;
    }


    public static String getJOIN() {
        return JOIN;
    }

    public static String getSPEAKING() {
        return SPEAKING;
    }

    public static String getLEAVE() {
        return LEAVE;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}