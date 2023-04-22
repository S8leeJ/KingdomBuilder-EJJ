 import java.util.*;
 import Board.Board;

 public class locationClass{
    Board board;
    Barn barn = new Barn();
    Harbor harbor = new Harbor();
    OracleFarmOasis ofo;
    Paddock pad = new Paddock();
    Tavern tavern = new Tavern();
    Tower tower = new Tower();
    public locationClass(){
        ofo =  new OracleFarmOasis(board);
    }
    public void action(/*which location tile picked*/ int loc, /*player*/ String color, /*terrain*/ int terrain){
          
            if(loc == 9){
                //farm();
            }
            if(loc == 10){
                //boat
            }
            if(loc == 11){
                //estate
            }
            if(loc == 12){
                //horse
            }
              if(loc == 13){
                // oasis();
            }
              if(loc == 14){
                // oracle();
            }
          
            if(loc == 15){
                //tower
            }
            if(loc == 16){
                //house
            }
            else System.out.println("not ofund");
        }   
 }