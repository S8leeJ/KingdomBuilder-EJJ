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
   
    public int[] getNumbers(ArrayList<Integer> x){
        int arr[] = new int[8];
        for(int i = 0; i<x.size(); i++){
            int cur = x.get(i);
            if(cur == 9) arr[1] = arr[1]+1; // farm
            if(cur == 10) arr[6] = arr[6]+1; // harbor
            if(cur == 11) arr[5] = arr[5]+1; // barn/estate
            if(cur == 12) arr[7] = arr[7]+1; // horse
            if(cur == 13) arr[2] = arr[2]+1; // hOasis
            if(cur == 14) arr[0] = arr[0]+1; // hOasis
            if(cur == 15) arr[3] = arr[3]+1; // tower
            if(cur == 16) arr[4] = arr[4]+1; // tower
        }
        return arr;
    }
    public int getLocation(int x){
        if(x == 0){
            return 14;
        }
        if(x==1) return 9;
        if(x==2) return 13;
        if(x==3) return 15;
        if(x==4) return 16;
        if(x==5) return 11;
        if(x==6) return 10;
        if(x==7) return 12;
        return -1;
    }
    public void remove(int x, ArrayList<Integer> y){
        for(int i = 0; i<y.size(); i++){
            if(y.get(i) == x){
                y.remove(i);
            }
        }
    }
}

