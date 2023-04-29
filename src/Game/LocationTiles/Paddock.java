import Board.Hex;
import Game.Game;

public class Paddock {
    //move any hex 2 spots in a straigh line'
    int[] xNums = {-2, -2, 0, 0, 2, 2};
    int[] yNums = {-1, 1, -2, 2, -1, 1};
    
    public boolean paddock( Game game, int x, int y, int xhex, int yhex){    
        //allws player to chose new spot to move settlemt
       //System.out.println("calling pddock method");
          Hex hex = game.getBoard().getHex(x, y);
          int x2 = hex.getX();
          int y2 = hex.getY();
          if(hex.getType() < 6 && hex.getColor().length() == 0 && hex.gray){
            for(int c = 0; c < 6; c++){
                System.out.print(xhex + xNums[c] + " ");
                System.out.println(yhex + yNums[c]);
                if(xhex + xNums[c] == x2 && yhex + yNums[c] == y2){
                    System.out.println("wow it works " );
                    hex.setColor(game.curPlayer().getColor());
                    game.curPlayer().decSettlement();
                    int boardX = hex.getX();
                    int boardY = hex.getY();
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
            }
             
              //if the settlement touches location tiles
          }	
          return false;
  
      }
}
