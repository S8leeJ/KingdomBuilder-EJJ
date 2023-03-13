package Card;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class terrain {
    int type;
    BufferedImage Canyon, Desert, Flower, Forest, Grass, back;
    public terrain(int type){
        this.type = type; 
        try {     
        Canyon =  ImageIO.read(getClass().getResourceAsStream("/Card/TerrainImages/KB-Card-Canyon.png"));

            //addlater      
        } catch (Exception e) {
            return;
        }
    }
    public int getType(){
        return type;
    }
    public BufferedImage getImage(){
        return Canyon;
    }
}
