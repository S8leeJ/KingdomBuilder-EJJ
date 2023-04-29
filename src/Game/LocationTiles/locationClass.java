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
    KingdomHelper help;
    public locationClass(Game g){
        ofo =  new OracleFarmOasis();
        game = g;
        help = new KingdomHelper(game);
    }
    public boolean action(/*which location tile picked*/ int loc, Player player, int x, int y){
        if(loc == 9){
             return ofo.farm(player.getColor(), 2, game, x, y);
        }
        if(loc == 10){
            //boat
        }
        if(loc == 11){
            //barn
            //return the other one
            return barn.barn(player.getColor(), player.getTerrainCard().getType(), game, x, y);

        }
        if(loc == 12){
            //horse
        }
          if(loc == 13){
            return ofo.farm(player.getColor(), 1, game, x, y);            
        }
          if(loc == 14){
             return ofo.farm(player.getColor(), player.getTerrainCard().getType(), game, x, y);
        }
      
        if(loc == 15){
           return ofo.farm(player.getColor(), player.getTerrainCard().getType(), game, x, y);
        }
        if(loc == 16){
            //tavern
        }
        return false;
    }   

    public boolean pickingSettlements(int loc, Player player, int x, int y){
        return barn.remove(player.getColor(), player.getTerrainCard().getType(), game, x, y);
    }

    public void drawMoves(Player player, Graphics g){
        //draws all spots where player can place their settlement
        boolean arr[][] = game.getBoard().getAvailable(player.getTerrainCard().getType(), player.getColor());
        help.drawGray(g, arr, game);
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
            //draws all spots where player can select their settlement to move

            //estate
             boolean arr[][] = game.getBoard().getAvailableEstate(player.getColor());
             help.drawGray(g, arr, game);
             help.drawHexNumbers(g, game);
             help.drawSettlements(g);
        // //    //choose settlement then remove
        //    boolean arr2[][] = game.getBoard().getAvailable(player.getTerrainCard().getType(), player.getColor());
        //    help.drawGray(g, arr2, game);
        //    help.drawHexNumbers(g, game);
        //    help.drawSettlements(g);
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
            boolean arr[][] = game.getBoard().getAvailableTower(player.getTerrainCard().getType(), player.getColor());
            help.drawGray(g, arr, game);
            help.drawHexNumbers(g, game);
            help.drawSettlements(g);   
        }
        if(loc == 16){
            //house
        }
        //else System.out.println("not ofund");
    }   
    
 }