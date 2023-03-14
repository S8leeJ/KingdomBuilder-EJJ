package Board;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class LocationTiles {
    public BufferedImage Boat, Estate, Farm, Horse, Oasis, Oracle, Tower, House;
   public LocationTiles(){
    try {     
        Boat =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Boat2.png"));
        Estate =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Boat2.png"));
        Farm =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Boat2.png"));
        Horse =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Boat2.png"));
        Oasis  =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/Boat2.png"));

    } catch (Exception e) {
            return;
        }
    }
}

