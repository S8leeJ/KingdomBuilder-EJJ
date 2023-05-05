package ObjectiveCards;
import java.awt.image.*;
import java.util.*;

import Board.Hex;
import Game.Player;
import Scoring.GeneralScoring;


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
