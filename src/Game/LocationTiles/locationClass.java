 import java.awt.Graphics;
import java.util.*;
 import Board.Board;
import Board.Hex;
import Game.Game;
import Game.Player;

 public class locationClass{
    Game game;
    BarnHarbor barnHarb = new BarnHarbor();
    OracleFarmOasis ofo;
    Paddock pad = new Paddock();
    Tavern tavern = new Tavern();
    KingdomHelper help;
    int xsave, ysave;
    public locationClass(Game g){
        ofo =  new OracleFarmOasis();
        game = g;
        help = new KingdomHelper(game);
    }

    public void setXY(int x, int y){
        xsave = x;
        ysave = y;
    }
    public boolean action(/*which location tile picked*/ int loc, Player player, int x, int y){
        if(loc == 9){
             return ofo.farm(player.getColor(), 2, game, x, y);
          // return true;
        }
        if(loc == 10){
            //boat
            return barnHarb.harbor(player.getColor(), game, x, y);
        }
        if(loc == 11){
            //barn
            //return the other one
            return barnHarb.barn(player.getColor(), player.getTerrainCard().getType(), game, x, y);

        }
        if(loc == 12){
            //horse
            //return;
            return pad.paddock(game, x, y, xsave, ysave);
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
            return tavern.tavern(player, x, y, game);
        }
        return false;
    }   

    public boolean pickingSettlements(Player player, int x, int y){
        if(barnHarb.remove(player.getColor(), game, x, y)){
            Hex hex = game.getBoard().getHex(x, y);
            setXY(hex.getX(), hex.getY());
            return true;
        }
        return false;
    }
    public void drawPaddock( Graphics g){
        //draws where payer can place settlement
        //setXY(x, y);
        boolean[][] arr =game.getBoard().getAvailablePaddock(xsave, ysave);
        help.drawGray(g, arr, game);
    }
    public void drawMoves(Player player, Graphics g, int locType){
        //draws all spots where player can place their settlement
        boolean[][] arr = new boolean[20][20];
        if(locType == 11) arr = game.getBoard().getAvailable(player.getTerrainCard().getType(), player.getColor());
        if(locType == 10) arr = game.getBoard().getAvailable(7, player.getColor());
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
            boolean arr[][] = game.getBoard().getAvailableBarnHarb(player.getColor());
             help.drawGray(g, arr, game);
             help.drawHexNumbers(g, game);
             help.drawSettlements(g);
        }
        if(loc == 11){
            //draws all spots where player can select their settlement to move

            //barn
             boolean arr[][] = game.getBoard().getAvailableBarnHarb(player.getColor());
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
            boolean arr[][] = game.getBoard().getAvailableBarnHarb(player.getColor());
             help.drawGray(g, arr, game);
             help.drawHexNumbers(g, game);
             help.drawSettlements(g);
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
            //tavern
            boolean arr[][] = game.getBoard().getAvailableTavern(player.getColor());
          
            help.drawGray(g, arr, game);
            help.drawHexNumbers(g, game);
            help.drawSettlements(g);   
            

        }
        //else System.out.println("not ofund");
    }   
    
 }