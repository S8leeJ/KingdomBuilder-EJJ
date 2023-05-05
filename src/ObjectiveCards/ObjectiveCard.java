package ObjectiveCards;
import java.awt.image.*;
import java.util.*;
import Game.Player;


public class ObjectiveCard{
    String type;
    BufferedImage image;
    ArrayList<String>  ObjectiveNames;

   public ObjectiveCard(String t, BufferedImage im){
    type = t;
    image = im;
    ObjectiveNames = new ArrayList<>();
    Collections.addAll(ObjectiveNames, "Citizens", "Discoverers", "Farmers", "Fishermen", "Hermits", "Knights", "Lords", "Merchants", "Miners", "Workers");
   }
   
   public BufferedImage getImage(){
    return image;
   }
   public String getType(){
    return type;
   }
   public int score(){
    int score = 0;
    //i used the index of the type as a specific number cuz i not writing all that out
    for(int i = 0; i<ObjectiveNames.size(); i++){
        if(type.equals(ObjectiveNames.get(i))){
            score+= score2(i);
        }
    }
    return score;
   }
   public int score2(int x){
    int score= 0;
        if(x==0){
            //citizens
        }
        if(x==1){
            //discover
        }
        if(x==2){
            //farmer
        }
        if(x==10){
            //worker
            
        }
        return score;

   }
}
