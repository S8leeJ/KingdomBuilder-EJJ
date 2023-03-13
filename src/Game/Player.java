package Game;

import Card.TerrainCard;

public class Player {
    String color; 
    int settlements;
    TerrainCard card;
    int num;
    int curSettlements;

    public Player(String col){
        color = col;
        settlements = 40;
        curSettlements = 0;
    }
    

    public int getNum(){
        return num;
    }
    public void setType(TerrainCard car){
       card = car;
    }
    
    public TerrainCard getTerrainCard(){
        return card;
    }
    public String getColor(){
        return color;
    }
    public int curSettlements(){
        return curSettlements;
    }
    public void resetSettlements(){
        curSettlements = 0;
    }
    public void useSettlement(){
        curSettlements++;
        settlements--;
    }
}
   

