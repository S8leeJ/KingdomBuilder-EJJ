package ObjectiveCards;
import java.awt.image.*;
import java.util.*;

import Board.Hex;
import Game.Player;
import Scoring.GeneralScoring;


public class ObjectiveCard{
    String type;
   public ObjectiveCard(String t){
    type = t;
    }
   
   public String getType(){
    return type;
   }
   
}
