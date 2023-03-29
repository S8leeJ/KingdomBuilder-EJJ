package Board;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class LocationTiles {
    public static BufferedImage Boat, Estate, Farm, Horse, Oasis, Oracle, Tower, House;
    public int Location;
   public LocationTiles(){
    try {     
    
        Boat =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Boat2.png"));
        Estate =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Estate2.png"));
        Farm =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Farm2.png"));
        Horse =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Horse2.png"));
        Oasis  =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Oasis2.png"));
        Oracle  =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Oracle2.png"));
        Tower  =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Tower2.png"));
        House  =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/House2.png"));

    } catch (Exception e) {
            return;
        }
    }
    public static BufferedImage getLoc(int x){
        if(x == 9){
            return Farm;
        }
        else if (x == 10){
            return Boat;
        }
        else if (x == 11){
            return Estate;
        }
        else if (x == 12){
            return Horse;
        }
        else if (x == 13){
            return Oasis;
        }
        else if (x == 14){
            return Oracle;
        }
        else if (x == 15){
            return Tower;
        }
        else if (x == 16){
            return House;
        }
        return Farm;
    }
}

