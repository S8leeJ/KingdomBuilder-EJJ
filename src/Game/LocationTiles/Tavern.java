import Board.Hex;
import Game.Game;
import Game.Player;

public class Tavern {
    public boolean tavern(Player player, int x, int y, Game game){
             Hex hex = game.getBoard().getHex(x, y);
             if(hex.gray){
                 hex.setColor(game.curPlayer().getColor());
                 game.curPlayer().decSettlement();
                 int boardX = hex.getX();
                 int boardY = hex.getY();
                 //if the settlement touches location tiles
                 if(game.CheckLocTiles(boardX, boardY)){
                     // if that settlement is the only one touching it, 
                     Hex temp[][] =  game.getBoard().getHexes();
                     Hex locHex = temp[game.getCurLocX()][game.getCurLocY()];
                     int checkIfAvailable = game.checkAround(game.getCurLocX(), game.getCurLocY());
                     if(checkIfAvailable == 1 && locHex.getLoc()>0){
                         //set player a token
                         int locType = locHex.getType();
                         locHex.decLoc();
                         game.curPlayer().addLocTile(locType);
                     }
                 }  
                 return true; 
             }	
             return false;
     
         }
    }

