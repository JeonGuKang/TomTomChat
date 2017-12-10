package model;

/**
 * Created by Kangjeongu on 2017. 12. 9..
 */

public class ChatData {
    private String key;
    private String userName;
    private String message;

    public ChatData() { }

    public ChatData(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public ChatData(String key, String userName, String message) {
        this.key = key;
        this.userName = userName;
        this.message = message;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
