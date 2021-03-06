package Model;

import java.io.Serializable;



public class CardColor implements Serializable{
    String color;

    public CardColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public boolean equals(Object obj) {
        CardColor ccolor= (CardColor) obj;
        if(ccolor.getColor().equals(color))
        {
            return true;
        }
        return false;
    }
}
