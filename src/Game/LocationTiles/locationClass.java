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
    KingdomHelper help;
    public locationClass(Game g){
        ofo =  new OracleFarmOasis();
        game = g;
        help = new KingdomHelper(game);
    }
    public boolean action(/*which location tile picked*/ int loc, Player player, Graphics g, int x, int y){
        if(loc == 9){
             boolean arr[][] = game.getBoard().getAvailable(2, player.getColor());
             help.drawGray(g, arr, game);
             help.drawHexNumbers(g, game);
             help.drawSettlements(g);
             return ofo.farm(player.getColor(), 2, game, x, y);


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
            boolean arr[][] = game.getBoard().getAvailable(1, player.getColor());
            help.drawGray(g, arr, game);
            help.drawHexNumbers(g, game);
            help.drawSettlements(g);
            return ofo.farm(player.getColor(), 1, game, x, y);            
        }
          if(loc == 14){
            // oracle();
            boolean arr[][] = game.getBoard().getAvailable(player.getTerrainCard().getType(), player.getColor());
             help.drawGray(g, arr, game);
             help.drawHexNumbers(g, game);
             help.drawSettlements(g);
             return ofo.farm(player.getColor(), player.getTerrainCard().getType(), game, x, y);
        }
      
        if(loc == 15){
            boolean arr[][] = game.getBoard().getAvailable(player.getTerrainCard().getType(), player.getColor());
        }
        if(loc == 16){
            //house
        }
        return false;
    }   
    public void drawGray(/*which location tile picked*/ int loc, Player player, Graphics g){
        if(loc == 9){
            boolean arr[][] = game.getBoard().getAvailable(2, player.getColor());
            help.drawGray(g, arr, game);
           // ofo.farm(player.getColor(), 2, game);
            help.drawHexNumbers(g, game);
            help.drawSettlements(g);

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
            boolean arr[][] = game.getBoard().getAvailable(1, player.getColor());
            help.drawGray(g, arr, game);
           // ofo.farm(player.getColor(), 2, game);
            help.drawHexNumbers(g, game);
            help.drawSettlements(g);
        }
          if(loc == 14){
            // oracle();
            boolean arr[][] = game.getBoard().getAvailable(player.getTerrainCard().getType(), player.getColor());
            help.drawGray(g, arr, game);
           // ofo.farm(player.getColor(), 2, game);
            help.drawHexNumbers(g, game);
            help.drawSettlements(g);
        }
      
        if(loc == 15){

            //tower
        }
        if(loc == 16){
            //house
        }
        //else System.out.println("not ofund");
    }   
    
 }