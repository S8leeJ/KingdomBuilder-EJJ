package Game;

import java.util.Locale;
import java.util.*;
import Board.Hex;
import Board.LocationTiles;
import Card.TerrainCard;

public class Player {
    String color; 
    int settlements;
    TerrainCard card;
    int num;
    int curSettlements;
    ArrayList<Integer> locTile;

    public Player(String col){
        locTile = new ArrayList<>();
        color = col;
        settlements = 40;
        curSettlements = 0;
    }
    

    public void addLocTile(int x){
        locTile.add(x);
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
    public int getSettlement(){
        return settlements;
    }

    public ArrayList<Integer> getLoc(){
        return locTile;
    }

}
   

