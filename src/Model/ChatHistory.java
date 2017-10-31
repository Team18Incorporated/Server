package edu.byu.cs.team18.tickettoride.Common;

import java.util.List;

/**
 * Created by abram on 10/25/2017.
 */

public class ChatHistory {
    private List<ChatMessage> history;



    public void add(ChatMessage message)
    {
        history.add(message);
    }

    public ChatHistory(List<ChatMessage> history) {
        this.history = history;
    }

    public List<ChatMessage> getHistory() {
        return history;
    }

    public void setHistory(List<ChatMessage> history) {
        this.history = history;
    }
}
