import Board.Board;

public class OracleFarmOasis{
    Board board;
    public OracleFarmOasis(Board b){
        board = b;
    }
    public void action(int loc, String color, int location){
        if(loc == 14){
            // oracle();
        }
        if(loc == 9){
            //farm();
        }
        if(loc == 13){
            // oasis();
        }
        else System.out.println("not ofund");
    }

    public void farm(String color, int location){
        boolean[][] available = board.getAvailable(location, color);
        
    }

}