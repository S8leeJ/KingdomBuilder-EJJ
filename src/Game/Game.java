package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import Board.Board;
import Board.Hex;
import Board.LocationTiles;
import Card.TerrainCard;
import Card.terrainDeck;
import ObjectiveCards.ObjectiveCard;

public class Game {
    public int oneid, twoid, threeid, fourid;
    public Board board;
    private Player one, two, three, four;
    private ArrayList<Player> players;
    private ArrayList<ObjectiveCard> objCards;
    public int curPlayer;
    public terrainDeck deck;
    public LocationTiles locTile;
    int curLocX;
    int curLocY;
    int oppX[] = {-1, 0, 1, 0, -1, 1, 1, -1};
    int oppY[] = {0, -1, 0, 1, -1, -1, 1, 1};
    
    public Game(){
        objCards = new ArrayList<>();
        locTile = new LocationTiles();
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1,2, 3,4, 5, 6, 7, 8));
        //oneid = nums.remove((int)(Math.random()*nums.size()));
        oneid = nums.remove((int)(Math.random()*nums.size()));
        twoid = nums.remove((int)(Math.random()*nums.size()));
        threeid = nums.remove((int)(Math.random()*nums.size()));
        fourid = nums.remove((int)(Math.random()*nums.size()));
    
        board = new Board(oneid, twoid, threeid, fourid);
        System.out.println("sectors" + oneid + " " + twoid + " " + threeid + " " + fourid);
        one = new Player("blue");
        two = new Player("white");
        three = new Player("orange");
        four = new Player("black");
        players = new ArrayList<Player>();
        deck = new terrainDeck();
        players.add(one);
        players.add(two);
        players.add(three);
        players.add(four);
        int ran = (int)(Math.random()*4);
        curPlayer = ran;
        
    }
    public ArrayList<ObjectiveCard> getCards(){
        return objCards;
    }
    public void setCards(ArrayList<ObjectiveCard> cards){
        objCards = cards;
        for(int c = 0; c < cards.size(); c++){
            System.out.println(cards.get(c).getType() + " " +  cards.get(c).getImage());
        }
    }
    public void drawCard(){
        TerrainCard card = deck.getNext();
        if(deck.empty()) deck.resetTerrainDeck();
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
   
    public boolean CheckLocTiles(int x, int y){
        Hex curBoard[][] = board.getHexes();
        curLocX = -1;
        curLocY = -1;
      
        boolean isTrue = false;
        for(int i = 0; i<8; i++){
            int toppX = oppX[i];
            int toppY = oppY[i];
            boolean temp = false;
            if(validBounds((toppX+x), (toppY+y))){

                temp = curBoard[x+toppX][y+toppY].getType()>8;
                isTrue = isTrue||temp;
                if(temp){
                    curLocX = x+toppX;
                    curLocY = y+toppY;
                    break;
                }
                      
                if(x%2!=0 && i==3){
                    i=5;
                }
                if(x%2 ==0 && i==5){
                    break;
                }
            }
        }
       return isTrue;
    }


    public boolean validBounds(int i, int j){
        if(i>=0 && j>=0 && j<=19 && i<=19)
            return true;
        return false;
    }

    public int getTypeLoc(){
        Hex[][]tempBoard  = board.getHexes();
        return tempBoard[curLocX][curLocY].getType();
    }
    public int getCurLocX(){
        return curLocX;
    }
    public int getCurLocY(){
        return curLocY;
    }

    public int checkAround(int x, int y){
        String color = curPlayer().getColor();
        int settles = 0;
        Hex[][] curBoard = board.getHexes();
        String colorT = "";
        for(int i = 0; i<8; i++){
            int toppX = oppX[i];
            int toppY = oppY[i];
            if(validBounds(toppX+x, toppY+y)){
              
                colorT = curBoard[x+toppX][y+toppY].getColor();
                if(colorT.equals(color)) settles++;
                if(x%2!=0 && i==3){
                    i=5;
                }
                if(x%2 ==0 && i==5){
                    break;
                }
            }

        }
        return settles;        
    }
}
