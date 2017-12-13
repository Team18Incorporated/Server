package Model;

import java.io.Serializable;



public class TrainCard extends Card implements Serializable{

    private CardColor color;

    public TrainCard(CardColor color) {
        this.color = color;
    }

    public TrainCard(String color)
    {
        this.color = new CardColor(color);
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }
    
    public String getColorString()
    {
    	return color.getColor();
    }
}
