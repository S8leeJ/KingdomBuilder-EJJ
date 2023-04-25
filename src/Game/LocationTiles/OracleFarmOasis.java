import Board.Board;
import Board.Hex;
import Game.Game;
/*
 * Oracle- build on same terrain as card
 * Farm- build on grass terrain
 * oasis- build on desert hex
 */
public class OracleFarmOasis{
  //  Board board;
    public OracleFarmOasis(){
    //    board = b;
    }
   
    public void farm(String color, int terrainType, int x, int y, Game game){    
        System.out.println("AWEEEEEEE");
        Hex hex = game.getBoard().getHex(x, y, 31.25, 36.25);
        if(hex.getType() == terrainType && hex.getColor().length() == 0 && hex.gray == true){
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
        }	
    }
}

