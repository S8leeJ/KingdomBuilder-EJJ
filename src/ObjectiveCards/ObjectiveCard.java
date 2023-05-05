package ObjectiveCards;
import java.awt.image.*;


public class ObjectiveCard{
    String type;
    BufferedImage image;
   public ObjectiveCard(String t, BufferedImage im){
    type = t;
    image = im;
   }
   public BufferedImage getImage(){
    return image;
   }
   public String getType(){
    return type;
   }

}
