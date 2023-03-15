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

    public boolean[][] getLocAdj(Hex[][] sector){
        //find the location tiles first, then u get the available 
        boolean adj[][] = new boolean[10][10];

        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
          

                if(sector[i][j].getType()> 8){
                    ////
                    adj[i-1][j] = true;
                    ////
                    adj[i][j-1] = true;
                    ////
                    adj[i+1][j] = true;
                    ////
                    adj[i][j+1] = true;
                    if(j%2 == 0){
                //
                        adj[i-1][j-1] = true;
    
                        adj[i+1][j-1] = true;
                    }
                    else{
                        //
                        adj[i+1][j+1] = true;
                        //
                        adj[i-1][j+1] = true;
                    }
                }
            }
        }
 
        return adj;
    }

    public boolean isLocationTile(){
        return false;
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
        //add more
    }
}

