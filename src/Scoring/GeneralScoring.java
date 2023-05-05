package Scoring;
import java.util.ArrayList;
import java.util.Collections;
import Board.Hex;
import Game.Game;
import ObjectiveCards.ObjectiveCard;

public class GeneralScoring {
    //this is where we iterate through and call coresponding methods ig  ya wanna write some pseudocodefor the ez ones
    Game game; 
    ArrayList<String>  ObjectiveNames;
    int oppX[] = {-1, 0, 1, 0, -1, 1, 1, -1};
    int oppY[] = {0, -1, 0, 1, -1, -1, 1, 1};
    
    public GeneralScoring(Game Sgame){
        game = Sgame;
        ObjectiveNames = new ArrayList<>();
       Collections.addAll(ObjectiveNames, "Citizens", "Discoverers", "Farmers", "Fishermen", "Hermits", "Knights", "Lords", "Merchants", "Miners", "Workers");
   
     }

     public void score(ObjectiveCard obj){ //this will only return the int
        
        //i used the index of the type as a specific number cuz i not writing all that out
        for(int i = 0; i<ObjectiveNames.size(); i++){
            if(obj.getType().equals(ObjectiveNames.get(i))){
                score2(i);
            }
        }
       }
       public void score2(int x){
            if(x==0){
                //citizens
            }
            if(x==1){
                //discover
                Discoverers();
            }
            if(x==2){
                //farmer
                Farmer();
            }
            if(x==3){
                //fisher
                FishMine(7);
            }
            if(x==4){
                //hermits this is floodfill
            }
            if(x==5){
                //knights
                Knights();
            }
            if(x==6){
                //lorfs
            }
            if(x==7)
            //merchants
            if(x==8){
                FishMine(6);
            }
            if(x==10){
                scoreWorker();
            }
    
       }
       public void Farmer(){
        Hex s1[][] = game.board.one.getSector();
        Hex s2[][] = game.board.two.getSector();
        Hex s3[][] = game.board.three.getSector();
        Hex s4[][] = game.board.four.getSector();
        int s1s = FarmerHelper(s1);
        int s2s = FarmerHelper(s2);
        int s3s = FarmerHelper(s3);
        int s4s = FarmerHelper(s4);

        int lowers = Math.min(s4s, Math.min(s3s, Math.min(s1s, s2s)));
        game.curPlayer().incScore(lowers*3);

        

       }
       public int FarmerHelper(Hex x[][]){
        int count = 0;
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                if(x[i][j].getColor().equals(game.curPlayer().getColor())){
                    count++;
                }
            }
        }
        return count;
       }
       public void FishMine(int type){
        Hex curBoard[][] = game.board.getHexes();
        for(int i = 0; i<20; i++){
                for(int j = 0; j<20; j++){
                    if(curBoard[i][j].getColor().equals(game.curPlayer().getColor())){
                        if(CheckLocTiles(i, j, type)){
                            game.curPlayer().incScore(1);

                        }
                    }
                }
            }

       }

       public boolean CheckLocTiles(int x, int y, int type){
        Hex curBoard[][] = game.board.getHexes();
        
        for(int i = 0; i<8; i++){
            int toppX = oppX[i];
            int toppY = oppY[i];
            if(game.validBounds((toppX+x), (toppY+y))){

                if(curBoard[toppX+x][toppY+y].getType() == type){
                    return true;
                }
                if(x%2!=0 && i==3){
                    i=5;
                }
                if(x%2 ==0 && i==5){
                    break;
                }
            }
        }
       return false;
    }



       public void Knights(){
        Hex[][] board= game.getBoard().getHexes();
        int maxSettle = 0;
            for(int i = 0; i<20; i++){
                int temp = 0;
                for(int j = 0; j<20; j++){
                    if(board[i][j].getColor().equals(game.curPlayer().getColor())){
                        temp++;
                    }
                }
                if(Math.max(maxSettle, temp)==temp){
                    maxSettle = temp;
                }
            }
            game.curPlayer().incScore(maxSettle);

       }
       public void Discoverers(){
        Hex[][] board= game.getBoard().getHexes();
        boolean hasSettle;
            for(int i = 0; i<20; i++){
                hasSettle = false;
                for(int j = 0; j<20; j++){
                    if(board[i][j].getColor().equals(game.curPlayer().getColor())){
                        hasSettle = true;
                    }
                }
                if(hasSettle){
                    game.curPlayer().incScore(1);
                }
            }
       }
       public void scoreWorker(){
        Hex[][] board= game.getBoard().getHexes();
        for(int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                if(board[i][j].getType() == 8){
                    int settles = game.checkAround(i, j);
                    game.curPlayer().incScore(settles);
                }
            }
        }
     }
     public void scoreCastle(){
        Hex[][] board= game.getBoard().getHexes();
        for(int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                if(board[i][j].getType() == 8){
                    int settles = game.checkAround(i, j);
                    if(settles>=1){
                        game.curPlayer().incScore(3);
                    }
                }
            }
        }
     }

}
