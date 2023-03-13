package Card;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class TerrainCard {
    int type;
    BufferedImage Canyon, Desert, Flower, Forest, Grass, back;
    public TerrainCard(int type){
        this.type = type; 
        try {     
        Canyon =  ImageIO.read(getClass().getResourceAsStream("/Card/TerrainImages/KB-Card-Canyon.png"));
        Desert =  ImageIO.read(getClass().getResourceAsStream("/Card/TerrainImages/KB-Card-Desert.png"));
        Flower =  ImageIO.read(getClass().getResourceAsStream("/Card/TerrainImages/KB-Card-Flower.png"));
        Forest =  ImageIO.read(getClass().getResourceAsStream("/Card/TerrainImages/KB-Card-Forest.png"));
        Grass  =  ImageIO.read(getClass().getResourceAsStream("/Card/TerrainImages/KB-Card-Meadow.png"));

    } catch (Exception e) {
            return;
        }
    }
    public int getType(){
        return type;
    }
    public BufferedImage getImage(){
        if(type == 1){
            return Desert;
        }
        else if(type == 2){
            return Grass;
        }
        else if(type == 3){
            return Flower;
        }
        else if(type == 4){
            return Canyon;
        }
        else{
            return Forest;
        }

    }
}
