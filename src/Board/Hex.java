package Board;
import java.util.*;
public class Hex {
    public boolean gray;
    int type;
    private int color;
    int x;
    int y;

    public Hex(int type){
        gray = true;
        this.type = type;
        color = -1;
        
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
    public void setColor(int color){
        this.color = color;
    }
    public int getColor(){
        return color;
    }
}
