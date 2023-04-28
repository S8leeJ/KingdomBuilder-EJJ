import Board.Hex;
import Game.Game;

public class Barn {
    //change this to draw pick only a settlement 
    //move an existing settlement to your terrain card
          public Barn(){
          }
         //removes the settlement
          public void remove(String color, int terrainType, Game game, int x, int y){    
            Hex hex = game.getBoard().getHex(x, y);
            if(hex.getColor().equals(color) && hex.gray == true){
                hex.setColor("");
                game.curPlayer().addSettlements();
            }
            //return true;
         }
          public boolean barn(String color, int terrainType, Game game, int x, int y){    
              Hex hex = game.getBoard().getHex(x, y);
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
                  return true; 
              }	
              return false;
      
          }
          
      }
      
      

