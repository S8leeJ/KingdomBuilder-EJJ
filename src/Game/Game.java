package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import Board.Board;
import Board.LocationTiles;
import Card.TerrainCard;
import Card.TerrainDeck;
import ObjectiveCards.ObjectiveCard;

public class Game {
    public int oneid, twoid, threeid, fourid;
    public Board board;
    private Player one, two, three, four;
    private ArrayList<Player> players;
    public int curPlayer;
    public TerrainDeck deck;
    public LocationTiles locTile;
    public Game(){
        //creates sectors 
        locTile = new LocationTiles();
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3,4));
        oneid = nums.remove((int)(Math.random()*nums.size()));
        twoid = nums.remove((int)(Math.random()*nums.size()));
        threeid = nums.remove((int)(Math.random()*nums.size()));
        fourid = nums.remove((int)(Math.random()*nums.size()));
        board = new Board(oneid, twoid, threeid, fourid);
        
        one = new Player("blue");
        two = new Player("white");
        three = new Player("orange");
        four = new Player("black");
        players = new ArrayList<Player>();
        deck = new TerrainDeck();
        players.add(one);
        players.add(two);
        players.add(three);
        players.add(four);
        int ran = (int)(Math.random()*4);
        curPlayer = ran;
        
        //System.out.println(ran+"A RAn");
    }
    public void drawCard(){
        TerrainCard card = deck.getNext();
        curPlayer().setType(card);
    }
    public Board getBoard(){
        return board;
    }
    public Player curPlayer(){
        return players.get(curPlayer);
    }
    public int curPlayerInd(){
        return curPlayer;
    }
    public void changePlayer(){
        curPlayer = (curPlayer+1)%4;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public void setNext(int i){
       i = (i+1)%4;
    }
    public void updateLocTiles(){
        //find hex around currentSettlement, if there is a location tile, then 
        //iterate through the location tile, and see if it has only one
        // hex of the color of the player
        // if so, then the player gets that location tile
        boolean [][] playerSettles = curPlayer().getPlacedSettlements(board.getHexes());
        
        boolean [][] adjHex = board.combineAdjLocs();
        
        for(int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                boolean Rcur = adjHex[i][j];
                boolean Pcur = playerSettles[i][j];
                if(Rcur && Pcur && curPlayer().doesContainCood(i, j)){
                    //AND if that exact location hasnt been counted yet 
                    System.out.println("TOKEN WOKEN" + i+" " + j);
                    curPlayer().addCoord(i, j);
                    if(i<10 && j<10)
                    curPlayer().addLocTile(board.one.getLocType());
                    else if(i>=10 && j<10){
                        curPlayer().addLocTile(board.two.getLocType());
                    }
                    else if(i<10 && j>=10){
                        curPlayer().addLocTile(board.three.getLocType());
                    }
                    else 
                        curPlayer().addLocTile(board.four.getLocType());
                    System.out.println(curPlayer().getLoc());
                    //dolnt forget to store loc so you cant get it from this location again: maybe use hashmap to store 
                    // key as pos, and value as loc value
                }
            }
        }     
    }
//we need perameters of int x, int y, and return a boolean if there is a 
// location tile
    public void updateLocTiles2(){
        //get currentSettlement (already inn paenl as hex)
        //find hex around currentSettlement, if there is a location tile, then 
        //iterate through the location tile, and see if it has only one
        // hex of the color of the player
        // if so, then the player gets that location tile

        
    }
}
