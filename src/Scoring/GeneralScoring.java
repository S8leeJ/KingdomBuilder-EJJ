package Scoring;
import java.util.ArrayList;
import java.util.Collections;
import Board.Hex;
import Game.Game;
import Game.Player;
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

     public int score(ObjectiveCard obj, Player player){ //this will only return the int
        //i used the index of the type as a specific number cuz i not writing all that out
        for(int i = 0; i<ObjectiveNames.size(); i++){
            if(obj.getType().equals(ObjectiveNames.get(i))){
                return score2(i, player);
            }
        }
            return 0;
       }
       public int score2(int x, Player player){
        System.out.println("AwddddW" + x);

            if(x==0){
                //citizens
            }
            else if(x==1){
                //discover
                return Discoverers(player);
                //works 
            }
            else if(x==2){
                //farmer
                return Farmer(player);
            }
            else if(x==3){
                //fisher
                return FishMine(7, player);
            }
            else if(x==4){
                //hermits this is floodfill
            }
            else if(x==5){
                //knights
                return Knights(player);
            }
            else if(x==6){
                //lorfs
                lords(player, game.getPlayers());
               
            }
            else if(x==7){
//emrchenats
            }
            //merchants
            else if(x==8){
                System.out.println("fishmine called");
                return FishMine(6, player);
            }
            else if(x==10){
                return scoreWorker(player);
            }
            return -1;
    
       }

       public int lords(Player Curone, ArrayList<Player>x){
             int c = 0;
            // for(int i = 0; i<3; i++){
            //     if(x.get(i).equals(Curone)){
            //         x.remove(i);
            //     }
            // }
            // int playerOne = lordHelp(Curone.getColor());
            // int playerTwo = lordHelp(x.get(0).getColor());
            // int playerThree = lordHelp(x.get(1).getColor());
            // int playerFour = lordHelp(x.get(2).getColor());
            // System.out.println(playerOne +  " " + playerTwo + " " + playerThree + " " + playerFour);
            // ArrayList<Integer> tots = new ArrayList<>();
        
            // tots.add(playerTwo);
            // tots.add(playerThree);
            // tots.add(playerFour);

            // Collections.sort(tots);
            // if(playerOne == tots.get(0)){
            //     c+=12;
            // }
            // else if((tots.size()>1) && playerOne == tots.get(1)){
            //     c+=6;
            // }

            return c;
       }
       public int lordHelp(String color){
        int count = 0;
        Hex[][] board = game.board.getHexes();
            for(int i = 0; i<20; i++){
                for(int j = 0; j<20; j++){
                        if(board[i][j].getColor().equals(color)){
                             count++;
                        }
                }   
            }
            return count;
       }
       public int Farmer(Player player){
        Hex s1[][] = game.board.one.getSector();
        Hex s2[][] = game.board.two.getSector();
        Hex s3[][] = game.board.three.getSector();
        Hex s4[][] = game.board.four.getSector();
        int s1s = FarmerHelper(s1, player);
        int s2s = FarmerHelper(s2, player);
        int s3s = FarmerHelper(s3, player);
        int s4s = FarmerHelper(s4, player);

        int lowers = Math.min(s4s, Math.min(s3s, Math.min(s1s, s2s)));
        return lowers * 3;
        

       }
       public int FarmerHelper(Hex x[][], Player player){
        int count = 0;
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                if(x[i][j].getColor().equals(player.getColor())){
                    count++;
                }
            }
        }
        return count;
       }
       public int FishMine(int type, Player player){
        int score = 0;
        Hex curBoard[][] = game.board.getHexes();
        for(int i = 0; i<20; i++){
                for(int j = 0; j<20; j++){
                    if(curBoard[i][j].getColor().equals(player.getColor())){
                        if(CheckLocTiles(i, j, type, player)){
                            score++;
                            System.out.println("ADWWDA"+ score);
                        }
                    }
                }
            }
            return score;
       }

       public boolean CheckLocTiles(int x, int y, int type, Player player){
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


       public int Knights(Player player){
        Hex[][] board= game.getBoard().getHexes();
        int maxSettle = 0;
            for(int i = 0; i<20; i++){
                int temp = 0;
                for(int j = 0; j<20; j++){
                    if(board[i][j].getColor().equals(player.getColor())){
                        temp++;
                    }
                }
                if(Math.max(maxSettle, temp)==temp){
                    maxSettle = temp;
                }
            }
            return maxSettle*2;

       }
       public int Discoverers(Player player){
        int score = 0;
        Hex[][] board= game.getBoard().getHexes();
        boolean hasSettle;
            for(int i = 0; i<20; i++){
                hasSettle = false;
                for(int j = 0; j<20; j++){
                    if(board[i][j].getColor().equals(player.getColor())){
                        hasSettle = true;
                    }
                }
                if(hasSettle){
                    score+=1;
                }
            }
            return score;
       }
       public int scoreWorker(Player player){
        int score = 0;
        Hex[][] board= game.getBoard().getHexes();
        for(int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                if(board[i][j].getType() == 8){
                    int settles = game.checkAround(i, j);
                    score+=settles;
                }
            }
        }
        return score;
     }
     public int scoreCastle(Player player){
        int score= 0;
        Hex[][] board= game.getBoard().getHexes();
        for(int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                if(board[i][j].getType() == 8){
                    int settles = checkAround2(i, j, player);
                    if(settles>=1){
                        score+=3;
                    }
                }
            }
        }
        return score;
     }
     public int checkAround2(int x, int y, Player player){
        String color = player.getColor();
        int settles = 0;
        Hex[][] curBoard = game.board.getHexes();
        String colorT = "";
        for(int i = 0; i<8; i++){
            int toppX = oppX[i];
            int toppY = oppY[i];
            if(game.validBounds(toppX+x, toppY+y)){
              
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
