import Board.Board;
/*
 * Oracle- build on same terrain as card
 * Farm- build on grass terrain
 * oasis- build on desert hex
 */
public class OracleFarmOasis{
    Board board;
    public OracleFarmOasis(Board b){
        board = b;
    }
   

    public void farm(String color){
        boolean[][] available = board.getAvailable(2, color);        
    }

}