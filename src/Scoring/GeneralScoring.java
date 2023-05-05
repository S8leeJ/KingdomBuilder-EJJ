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
            }
            if(x==2){
                //farmer
            }
            if(x==5){
                //knights
            }
            if(x==10){
                scoreWorker();
            }
    
       }
       public void knightsDiscovers(){
        Hex[][] board= game.getBoard().getHexes();
        int settlesPerRow;
            for(int i = 0; i<20; i++){
                settlesPerRow = 0;
                for(int j = 0; j<20; j++){
                    if(board[i][j].getColor().equals(game.curPlayer().getColor())){

                    }
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
