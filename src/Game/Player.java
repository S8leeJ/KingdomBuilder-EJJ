package Game;

import java.util.Locale;

import Board.Hex;
import Board.LocationTiles;
import Card.TerrainCard;

public class Player {
    String color; 
    int settlements;
    TerrainCard card;
    int num;
    int curSettlements;
    LocationTiles locTile;

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
    public int getSettlement(){
        return settlements;
    }

    public LocationTiles getLoc(){
        return locTile;
    }
    public void setLocation(LocationTiles loc){
        locTile = loc;
    }
    public boolean[][] getPlacedSettlements(Hex[][] board){
        boolean[][] placedSettlements = new boolean[20][20];
        for(int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                Hex curHex = board[i][j];
                String curColor = curHex.getColor();
                if(curColor.equals(color)){
                    placedSettlements[i][j] = true;
                }
            }
        }
        return placedSettlements;
    }
}
   

