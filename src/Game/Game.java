package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import Board.Board;
import Board.Hex;
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
    int curLocX;
    int curLocY;
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
   
    public boolean CheckLocTiles(int x, int y){
        Hex curBoard[][] = board.getHexes();
        curLocX = -1;
        curLocY = -1;
        boolean one, two, three, four, five, six;
        one = false;
        two = false;
        three =  false;
        four = false;
        five =  false;
        six = false;
        
        if(x>=1){
            System.out.println("x-1, y " + curBoard[x-1][y].getType());
            one = curBoard[x-1][y].getType()>8;
            if(one){
                curLocX = x-1;
                curLocY = y;
            }
        }
        if(y>=1){
            two = curBoard[x][y-1].getType()>8;
            System.out.println("x, y-1 " + curBoard[x][y-1].getType());
            if(two){
                curLocX = x;
                curLocY = y-1;
            }
        }
        if(x<19){
            System.out.println("x+1, y " + curBoard[x+1][y].getType());
            three = curBoard[x+1][y].getType()>8;
            if(three){
                curLocX = x+1;
                curLocY = y;
            }
        }
        if(y<19){
            System.out.println("x, y+1 " + curBoard[x][y+1].getType());
            four = curBoard[x][y+1].getType()>8;
            if(four){
                curLocX = x;
                curLocY = y+1;
            }
        }

        if(x%2 == 0){
            if(x>=1 && y>=1){
                System.out.println("x-1, y-1 " + curBoard[x-1][y-1].getType());
            five = curBoard[x-1][y-1].getType()>8;

                if(five){
                    curLocX = x-1;
                    curLocY = y-1;
                }
            }
            if(x<19 && y>=1){
                System.out.println("x+1, y-1 " + curBoard[x+1][y-1].getType());
            six = curBoard[x+1][y-1].getType()>8;
                if(six){
                    curLocX = x+1;
                    curLocY = y-1;
                }
            }
        }
        else{
            if(x<19 && y<19){
            five = curBoard[x+1][y+1].getType()>8;
            System.out.println("x+1, y+1 " + curBoard[x+1][y+1].getType());

                if(five){
                    curLocX = x+1;
                    curLocY = y+1;
                }    
        }
            if(x>=1 && y<19){
                System.out.println("x-1, y+1 " + curBoard[x-1][y+1].getType());

            six = curBoard[x-1][y+1].getType()>8;
                if(six){
                    curLocX = x-1;
                    curLocY = y+1;
                }
            }
        }
        System.out.println("CURLOC of location tile: " + curLocX + " " + curLocY);
        return (one||two||three||four||five||six);
    }

    public int getCurLocX(){
        return curLocX;
    }
    public int getCurLocY(){
        return curLocY;
    }

    public int checkAround(int x, int y){
        String color = curPlayer().getColor();
        System.out.println(color);
        String c1, c2,c3,c4,c5,c6;
        int settles = 0;
        Hex[][] curBoard = board.getHexes();
        c1 = "";
        c2 = "";
        c3 = "";
        c4 = "";
        c5 = "";
        c6 = "";

        if(x>=1){
            c1 = curBoard[x-1][y].getColor();
            
                if(c1.equals(color)){
                    settles++;
                }
        }
        if(y>=1){
            c2 = curBoard[x][y-1].getColor();
            if(c2.equals(color)){
                settles++;
            }
        }
        if(x<19){
            c3 = curBoard[x+1][y].getColor();
            if(c3.equals(color)){
                settles++;
            }
        }
        if(y<19){
            c4 = curBoard[x][y+1].getColor();
            if(c4.equals(color)){
                settles++;
            }
        }

        if(x%2 == 0){
            if(x>=1 && y>=1){
            c5 = curBoard[x-1][y-1].getColor();

            if(c5.equals(color)){
                settles++;
            }
            }
            if(x<19 && y>=1){
            c6 = curBoard[x+1][y-1].getColor();
            if(c6.equals(color)){
                settles++;
            }
            }
        }
        else{
            if(x<19 && y<19){
            c5 = curBoard[x+1][y+1].getColor();
            if(c5.equals(color)){
                settles++;
            }   
        }
            if(x>=1 && y<19){
            c6 = curBoard[x-1][y+1].getColor();
            if(c6.equals(color)){
                settles++;
            }
            }
        }
        System.out.println(c1 + c2+ c3+c4+c5+c6);

        return settles;        
    }

}
