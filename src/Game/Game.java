package Game;

import java.util.ArrayList;
import java.util.Arrays;

import Board.Board;

public class Game {
    public int oneid, twoid, threeid, fourid;
    private Board board;
    private Player one, two, three, four;
    public Game(){
        //creates sectors 
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));
        oneid = nums.remove((int)(Math.random()*nums.size()));
        twoid = nums.remove((int)(Math.random()*nums.size()));
        threeid = nums.remove((int)(Math.random()*nums.size()));
        fourid = nums.remove((int)(Math.random()*nums.size()));
        board = new Board(oneid, twoid, threeid, fourid);

        one = new Player();
        two = new Player();
        three = new Player();
        four = new Player();
        //board = new Board()
    }
    public Board getBoard(){
        return board;
    }
}
