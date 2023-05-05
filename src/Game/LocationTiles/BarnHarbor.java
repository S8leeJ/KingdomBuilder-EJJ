import Board.Hex;
import Game.Game;

public class BarnHarbor {
    //change this to draw pick only a settlement 
    //move an existing settlement to your terrain card
          
         //removes the settlement
         public boolean remove(String color, Game game, int x, int y){    
            Hex hex = game.getBoard().getHex(x, y);
            if(hex.getColor().equals(color)){
                int hX = hex.getX();
                int hY = hex.getY();
                   if(game.CheckLocTiles(hX, hY)){ // if settlement is touching a location tile
                    Hex temp[][] =  game.getBoard().getHexes();
                    Hex locHex = temp[game.getCurLocX()][game.getCurLocY()];
                    int checkIfAvailable = game.checkAround(game.getCurLocX(), game.getCurLocY()); //number of settlmenets around that location tile
    
                    if(checkIfAvailable == 1){ // if thats the only settlement
                        int locType = locHex.getType();
                        game.curPlayer().removeLocTile(locType); // removes that location tile 
                    }
                }
                hex.setColor("");
                game.curPlayer().addSettlements();
          
                return true;
            }
            //return true;
            return false;
         }
         public boolean barn(String color, int terrainType, Game game, int x, int y){    
            //allws player to chose new spot to move settlemt
              Hex hex = game.getBoard().getHex(x, y);
              if(hex.getType() == terrainType && hex.getColor().length() == 0 && hex.gray){
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
      
          } public boolean harbor(String color, Game game, int x, int y){    
            //allws player to chose new spot to move settlemt
              Hex hex = game.getBoard().getHex(x, y);
              if(hex.getType() == 7 && hex.getColor().length() == 0){
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
      
      

