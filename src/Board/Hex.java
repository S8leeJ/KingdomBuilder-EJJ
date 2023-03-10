package Board;
import java.util.*;
public class Hex {
    int type;
    int x;
    int y;

    public Hex(int type){
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
