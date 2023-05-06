package Game;

import java.util.*;
import Card.TerrainCard;

public class Player {
    String color; 
    int settlements;
    TerrainCard card;
    int curSettlements;
    ArrayList<Integer> locTile;
    int[] scores;
    int pos;
//aint no way we dont have score bruh
    public Player(String col){
        locTile = new ArrayList<>();
        color = col;
        settlements = 40;
        curSettlements = 0;
        scores = new int[4];
    }
    public void setPos(int p){
        pos = p;
    }
    public int getPos(){
        return pos;
    }
    public int[] getScores(){
        return scores;
    }
    public int getScore(){
        int total = 0;
        for(int c = 0; c < 4; c++){
            total += scores[c];
        }
        return total;
    }
    
    public void addLocTile(int x){
        locTile.add(x);
    }
    public void removeLocTile(int x){
        int ind = locTile.indexOf(x);
        locTile.remove(ind);
    }
   
    public void setType(TerrainCard car){
       card = car;
    }
    
    public void addSettlements(){
        settlements++;
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
    public void decSettlement(){
        settlements--;
    }
    public void locSettlement(){
        curSettlements = 2;
    }
    public int getSettlement(){
        return settlements;
    }
    public ArrayList<Integer> getLoc(){
        return locTile;
    }
}
   

