package Board;
import java.util.*;
public class Hex {
    //public boolean gray;
    int type;
    private String color;
    int x;
    int y;
    int numberOfLocTiles;
    
    public Hex(int type){
        this.type = type;
        color = "";
        if(type>8)
            numberOfLocTiles = 2;
        else
            numberOfLocTiles = 0;
    }
    public int getType(){
        return type;
    }
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return color;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void decLoc(){
        numberOfLocTiles--;
    }
    public int getLoc(){
        return numberOfLocTiles;
    }
}
