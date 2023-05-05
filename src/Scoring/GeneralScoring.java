package Scoring;

import Board.Hex;
import Game.Game;
import ObjectiveCards.ObjectiveCard;

public class GeneralScoring {
    //this is where we iterate through and call coresponding methods ig  ya wanna write some pseudocodefor the ez ones
    Game game; 
    public GeneralScoring(Game Sgame){
        game = Sgame;
     }

     public void score(ObjectiveCard objCard){
        int scored = objCard.score(); 
        game.curPlayer().incScore(scored);
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
