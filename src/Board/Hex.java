package Board;
import java.util.*;
public class Hex {
    public boolean gray;
    int type;
    private String color;
    int x;
    int y;
    

    public Hex(int type){
        gray = true;
        this.type = type;
        color = "";
    }
    public void setGray(boolean bool){
        gray = bool;
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
}
