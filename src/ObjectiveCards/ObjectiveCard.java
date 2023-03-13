package ObjectiveCards;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;


public class ObjectiveCard{
   int type;
   public static BufferedImage Citizens, Discoverers, Farmers, Fishermen, Hermits, Knights, Lords, Merchants, Miners, Workers;
   public ArrayList<BufferedImage> objectives;
   
   public ObjectiveCard( ){
    objectives = new ArrayList<>();
    try {           
        Citizens =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/CitizensObjective.png"));
        Discoverers =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/DiscoverersObjective.png"));
        Farmers =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/FarmersObjective.png"));
        Fishermen =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/FishermenObjective.png"));
        Hermits =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/HermitsObjective.png"));
        Knights =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/KnightsObjective.png"));
        Lords =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/LordsObjective.png"));
        Merchants  =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/MerchantsObjective.png"));
        Miners =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/MinersObjective.png"));
        Workers =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/WorkersObjective.png"));
       
        Collections.addAll(objectives, Citizens, Discoverers, Farmers, Fishermen, Hermits, Knights, Lords, Merchants, Miners, Workers);
        //add method to turn from buff image to actual numbered card.
    } catch (Exception e) {
        return;
    }
   }

   public int getType(){
    return type;
   }

   public ArrayList<BufferedImage> get3(){
        Collections.shuffle(objectives);
        ArrayList<BufferedImage> bufIm = new ArrayList<>();
        bufIm.add(objectives.get(0));
        bufIm.add(objectives.get(1));
        bufIm.add(objectives.get(2));
        return bufIm;
   }

}
