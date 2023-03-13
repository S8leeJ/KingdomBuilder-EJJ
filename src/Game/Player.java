package Game;

public class Player {
    String color; 
    int settlements;
    int cardType;
    int num;
    int curSettlements;

    public Player(String col){
        color = col;
        settlements = 40;
        curSettlements = 0;
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

    public void useSettlement(){
        if(curSettlements<4){
        curSettlements++;
        settlements--;
        }
    }
}
   

