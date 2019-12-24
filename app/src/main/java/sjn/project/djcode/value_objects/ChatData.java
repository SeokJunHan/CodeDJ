package sjn.project.djcode.value_objects;

public class ChatData {
    private String userName;
    private String message;
    private Boolean isSystemMsg;

    public ChatData() { }

    public ChatData(String userName, String message) {
        this.userName = userName;
        this.message = message;
        this.isSystemMsg = false;
    }

    public ChatData(String userName, String message, Boolean isSystemMsg) {
        this.userName = userName;
        this.message = message;
        this.isSystemMsg = isSystemMsg;
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

    public Boolean getSystemMsg() {
        return isSystemMsg;
    }

    public void setSystemMsg(Boolean systemMsg) {
        isSystemMsg = systemMsg;
    }
}
