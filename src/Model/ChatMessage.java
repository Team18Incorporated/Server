package Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by abram on 10/25/2017.
 */

public class ChatMessage implements Serializable{
    private String message;
    private String messageID;
    private String playerName;
    private Date time;

    public ChatMessage(String message, String messageID, String playerName, Date time) {
        this.message = message;
        this.messageID = messageID;
        this.playerName = playerName;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
