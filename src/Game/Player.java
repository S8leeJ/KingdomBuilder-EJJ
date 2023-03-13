package Game;

public class Player {
    String color; 
    int settlements;
    int cardType;
    private int avSettles;

    public Player(String color){
        this.color = color;
        settlements = 40;
        cardType = -1;
        avSettles = 0;

    }
    

    public void setType(int x){
        cardType = x;
    }
    
    public int getType(){
        return cardType;
    }
    public String getColor(){
        return color;
    }
    public boolean hasSettlements(){
        return settlements > 0;
    }
}
