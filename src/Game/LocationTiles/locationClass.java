 import java.awt.Graphics;
import java.util.*;
 import Board.Board;
import Game.Game;
import Game.Player;

 public class locationClass{
    Game game;
    Barn barn = new Barn();
    Harbor harbor = new Harbor();
    OracleFarmOasis ofo;
    Paddock pad = new Paddock();
    Tavern tavern = new Tavern();
    Tower tower = new Tower();
    KingdomHelper help = new KingdomHelper();
    public locationClass(Game g){
        ofo =  new OracleFarmOasis();
        game = g;
    }
    public void action(/*which location tile picked*/ int loc, Player player, int x, int y, Graphics g){
          
            if(loc == 9){
		    	boolean arr[][] = game.getBoard().getAvailable(2, player.getColor());
                help.drawGray(g, arr, game);
                ofo.farm(player.getColor(), 2, x, y, game);
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