package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import Board.Board;
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
    public Game(){
        //creates sectors 
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));
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
        curPlayer++;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public void setNext(int i){
       i = (i+1)%4;
    }
    
}
