package com.cse312team.demo.Model;

public class Chat {
    public static final String JOIN = "JOIN";
    public static final String SPEAKING = "SPEAKING";
    public static final String LEAVE = "LEAVE";

    private String userName;
    private String message;
    private String messageType;


    public Chat(String messageType, String userName, String message) {
        this.messageType = messageType;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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



