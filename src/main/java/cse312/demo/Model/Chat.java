package cse312.demo.Model;
import com.alibaba.fastjson.JSON;

public class    Chat {
    public static final String JOIN = "JOIN";
    public static final String SPEAKING = "SPEAKING";
    public static final String LEAVE = "LEAVE";

    private String username;
    private String message;
    private String messageType;

    public static String jsonStr(String messageType, String username, String message) {
        return JSON.toJSONString(new Chat(messageType, username, message));
    }


    public Chat(String messageType, String username, String message) {
        this.messageType = messageType;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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