import Board.Hex;
import Game.Game;
public class Tower {
    
    
    /*public boolean[][] getAvailable(int x, String color){
        boolean [][] avail = new boolean[20][20];
        int numAvail = 0;
        for(int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                Hex curHex = board[i][j];
                if(curHex.getColor().equals(color)){
                    if(i>=1){
                        if(valid(i-1, j, x)){
                            avail[i-1][j] = true;
                            numAvail++;
                        }
                    }
                    if(j>=1){
                        if(valid(i, j-1, x)){
                            avail[i][j-1] = true;
                            numAvail++;
                        }
                    }
                    if(i<19){
                        if(valid(i+1, j, x)){
                            avail[i+1][j] = true;
                            numAvail++;
                        }
                    }
                    if(j<19){
                        if(valid(i, j+1, x)){
                            avail[i][j+1] = true;
                            numAvail++;
                        }
                    }
            
                    if(i%2 == 0){
                        if(i>=1 && j>=1){
                            if(valid(i-1, j-1, x)){
                                numAvail++;
                                avail[i-1][j-1] = true;
                            }
                        }
                        if(i<19 && j>=1){
                            if(valid(i+1, j-1, x)){
                                numAvail++;
                                avail[i+1][j-1] = true;
                            }
                        }
                    }
                    else{
                        if(i<19 && j<19){
                            if(valid(i+1, j+1, x)){
                                numAvail++;
                                avail[i+1][j+1] = true;
                            } 
                    }
                        if(i>=1 && j<19){
                            if(valid(i-1, j+1, x)){
                                numAvail++;
                                avail[i-1][j+1] = true;
                            }
                        }
                    }
                }
            }
        }
        //make an int and increment it every time 
        //if player has no settlementes / int is 0
        if(numAvail == 0){
            for(int i = 0; i<20; i++){
                for(int j = 0; j<20; j++){
                    Hex curHex = board[i][j];
                    if(curHex.getType() == x && curHex.getColor().length() == 0){
                        avail[i][j] = true;
                    }
                }
            } 
        }
        return avail;
    } */
}
