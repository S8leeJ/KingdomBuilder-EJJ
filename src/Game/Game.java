package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import Board.Board;
import Card.terrainDeck;

public class Game {
    public int oneid, twoid, threeid, fourid;
    private Board board;
    private Player one, two, three, four;
    private ArrayList<Player> players;

    public terrainDeck TerrainDecks;
    public Game(){
        //creates sectors 
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));
        oneid = nums.remove((int)(Math.random()*nums.size()));
        twoid = nums.remove((int)(Math.random()*nums.size()));
        threeid = nums.remove((int)(Math.random()*nums.size()));
        fourid = nums.remove((int)(Math.random()*nums.size()));
        board = new Board(oneid, twoid, threeid, fourid);

        one = new Player(1);
        two = new Player(2);
        three = new Player(3);
        four = new Player(4);
        players = new ArrayList<Player>();
        TerrainDecks = new terrainDeck();
        players.add(one);
        players.add(two);
        players.add(three);
        players.add(four);
    }
    public Board getBoard(){
        return board;
    }
    public Player getFirst(){
        int ran = (int)(Math.random()*4);
        reorder(players.get(ran), ran);
        for(int i = 0; i<4; i++){
            System.out.println(players.get(i).getType());
        }
        return players.get(ran);
    }
    public void reorder(Player player, int ran){
        players.set(0, player);
        players.set(1, getNext(ran));
        players.set(2, getNext(ran+1));
        players.set(3, getNext(ran+2));
    }
    public Player getNext(int i){
       i = (i+1)%4;
        return players.get(i+1);
    }
    
}
