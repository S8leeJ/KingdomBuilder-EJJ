package Board;
import java.util.*;
public class Hex {
    boolean gray;
    int type;
    int x;
    int y;

    public Hex(int type){
        gray = true;
        this.type = type;
    }
    public int getType(){
        return type;
    }
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
    
}
