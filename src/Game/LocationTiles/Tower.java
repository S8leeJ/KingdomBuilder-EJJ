import Board.Hex;
import Game.Game;
public class Tower {
    int oppX[] = {-1, 0, 1, 0, -1, 1, 1, -1};
    int oppY[] = {0, -1, 0, 1, -1, -1, 1, 1};
   
    /*
     *   Hex curHex = board[i][j];
                if(curHex.getColor().equals(color)){
                    for(int l= 0; l<8; l++){
                        int toppX = oppX[l];
                        int toppY = oppY[l];                       
                        if(validBounds(toppX, toppY, i, j)){
                            if(x%2!=0 && l==4){
                                l=6;
                            }
                            if(x%2 ==0 && l==6){
                                break;
                            }
                            if(valid(i+toppX, j+toppY, x)){
                                System.out.println(i+toppX + " " + j+toppY);
                                avail[i+toppX][j+toppY] = true;
                                numAvail++;
                            }
                         }
                     }
                }
     */
    public boolean[][] getAvailable(Game game){
        boolean[][] avail = new boolean[20][20];
        int numAvail = 0;
        for(int c = 0; c <= 19; c++){
            Hex hex = game.board.getHex(c, 0);
            if(hex.getColor().equals((game.curPlayer().getColor()))){
                for(int l = 0; l<8; l++){
                int toppX = oppX[l];
                int toppY = oppY[l];                       
                if(validBounds(toppX, toppY, c, j)){
                    if(x%2!=0 && l==4){
                        l=6;
                    }
                    if(x%2 ==0 && l==6){
                        break;
                    }
                    if(valid(i+toppX, j+toppY, x)){
                        System.out.println(i+toppX + " " + j+toppY);
                        avail[i+toppX][j+toppY] = true;
                        numAvail++;
                    }
                 }
             }
        
            }
        }
        for(int c = 0; c <= 19; c++){
            Hex hex = game.board.getHex(0, c);
        }
    }
}


r = 0-19 (r,0)
r = 0 c = 0-29 (r, c)