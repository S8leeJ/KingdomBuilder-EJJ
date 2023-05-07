import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;

import Board.Board;
import Board.Hex;
import Card.TerrainCard;
import Game.Game;
import ObjectiveCards.ObjectiveCard;
import Scoring.GeneralScoring;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.Timer;


public class KingdomPanel extends JPanel implements MouseListener, MouseMotionListener{
	Game game;
	int gameState;
	boolean usedSettlements = false;
	boolean usedTokens = false;
	private static BufferedImage background;
	public int sectwidth = 381, sectheight = 322;
	public int hexwidth = 38, hexlength = 44;
	double  gridHeight = 31.25, gridWidth = 36.25;
	int player = 1;
	ArrayList<Integer> UsedLocs;
	ArrayList<Integer> copy;
	KingdomHelper help;
	locationClass locclass;
	boolean viewCards;
	int locpicked;
	boolean moveSettlement;
	boolean playerDone;
	int objCard;
	int scorePlayer;
	boolean next = false;
	GeneralScoring score;
	boolean viewStandings;

	public KingdomPanel() {
		objCard = -1;
		scorePlayer = -1; 
		viewStandings = false;
		moveSettlement = false;
		locpicked = 0;
		game = new Game();
		score = new GeneralScoring(game);
		help = new KingdomHelper(game);
		locclass = new locationClass(game, help);
		gameState = 0;
		viewCards = false;
		UsedLocs = new ArrayList<>();
		copy = new ArrayList<>();

		try {     
			background = ImageIO.read(getClass().getResourceAsStream("/Board/Images/background.jpg"));
		
			} catch (Exception E) {
			System.out.println("Exception Error");
			return;
		}
		game.setCards(help.get3Obj());
		addMouseListener(this);
	}

	
	
	public void drawBoard(Graphics g){
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(help.getSector(game.oneid), 515,19, sectwidth, sectheight, null);
		g.drawImage(help.getSector(game.twoid), 515 + 361,19, sectwidth, sectheight, null);
		g.drawImage(help.getSector(game.threeid), 515,19 + 313, sectwidth, sectheight, null);
		g.drawImage(help.getSector(game.fourid), 515 + 361,19 + 313, sectwidth, sectheight, null);
	}
	
	public void resetFont(Graphics g, int size){
		g.setFont(new Font("Castellar", 1, size));
		g.setColor(Color.white);
	}
		
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		if(gameState >= 4){
			drawBoard(g);
			g.setFont(new Font("Castellar", 1, 50));
			g.setColor(Color.white);
			g.drawString("Scoring", 50, 67);
			help.drawHexNumbers(g, game);
			help.drawSettlements(g);	
			//21 300
			//488 618
			help.drawEnd(g, objCard, scorePlayer, game.getCards());
			if(next) drawNext(g);
			if(viewCards) {
				help.drawViewCards(g, game.getCards());
			}
			if(gameState == 5){
				g.setColor(new Color(48,81,110, 127));
				g.fillRoundRect(335, 24, 351 - 200, 655 - 617, 20, 20);
				g.setColor(Color.white);
				g.drawRoundRect(335, 24, 351 - 200, 655 - 617, 20, 20);
				g.setFont(new Font("Castellar", 1, 20));
				g.drawString("Play Again",  341, 51);
				help.drawTotal(g);
				g.setColor(new Color(48,81,110, 127));
				g.fillRoundRect(335, 65, 351 - 200, 655 - 617, 20, 20);
				g.setColor(Color.white);
				g.drawRoundRect(335, 65, 351 - 200, 655 - 617, 20, 20);
				g.setFont(new Font("Castellar", 1, 17));
				g.drawString("See Standings",  340, 92);

				if(viewStandings){
					help.viewStandings(g);
				}

			}
			

		}
		else{
		//draws the board image
		drawBoard(g);
		  help.drawFirstPlayer(g);
		// when players turn starts
		if(gameState == 0){
			resetFont(g, 48);
			g.drawString("Draw a card", 43, 457);
		}
		if(gameState>=1 && gameState != 2 && usedSettlements && game.curPlayer().curSettlements()<3 && !usedTokens){
			resetFont(g, 25);
			g.drawString("Place " +  Math.min((3 - game.curPlayer().curSettlements()), game.curPlayer().getSettlement()) + " Settlement(s)", 88, 457);
			String color = game.curPlayer().getColor();
			boolean arr[][] = game.getBoard().getAvailable(game.curPlayer().getTerrainCard().getType(), color);
			help.drawGray(g, arr, game);
		}
		//wen player picks between token or settlement
		if(gameState == 1 && !usedSettlements && locpicked == 0){
			resetFont(g, 21);
			g.drawString("Choose Tokens or Settlements", 55, 457);
		}
		//draws terrain card
		if(gameState >=1 && gameState !=4){
			g.drawImage(game.curPlayer().getTerrainCard().getImage(), 121, 503, 94, 150, null);
			resetFont(g, 15);
			if(game.deck.getSize() == 1)
			g.drawString("Resetting Terrain Deck After Next Turn",  57, 350);

			g.drawString(game.deck.getSize()+"X left", 37, 495);

		}
		//if player picks to use tokens
		if(locpicked > 0){
			if(moveSettlement){
				if(locpicked == 10 || locpicked == 11) locclass.drawMoves(game.curPlayer(), g, locpicked);
				if(locpicked == 12) locclass.drawPaddock(g);
			}
			else{
				if(locpicked == 16){
					boolean[][] arr = game.board.getAvailableTavern(game.curPlayer().getColor());
					if(arr.length==1){
						locpicked = 0;
					}
				}
				locclass.drawGray(locpicked, game.curPlayer(), g);
			}
		}
		if(locpicked == 0 && usedTokens){
			g.setColor(Color.black);			
			g.fillRoundRect(400, 490, 20, 20, 20, 20);
			resetFont(g, 20);
			g.setColor(new Color(202, 210, 235));
			g.fillRoundRect(34, 127, 114-34, 148-124, 10, 10);
			g.setColor(new Color(9, 25, 77));
			g.drawString("Done", 41 , 147);
			g.setColor(Color.white);
			int []arr = new int[8];
			arr = game.locTile.getNumbers(copy);
			help.displayLocs(g, arr);
		}

		//if player is placing their 3 settlememts
		help.drawHexNumbers(g, game);
		help.drawSettlements(g);
		//once player has placed 3 settlements
		if(gameState == 2 && game.curPlayer().curSettlements()==3 || game.curPlayer().getSettlement() == 0){
			g.setColor(new Color(202, 210, 235));
			g.fillRoundRect(223, 73, 494-223, 110-73, 20, 20);
			resetFont(g, 25);
			g.setColor(new Color(9, 25, 77));
			g.drawString("End Turn", 36+242, 190-65 - 26);
		}
		drawRest(g);	
		if(viewCards){
			help.drawViewCards(g, game.getCards());
		}	
	}
	}
	
	public void drawRest(Graphics g){
		g.setColor(Color.white);
		g.drawRoundRect(24, 500, 457, 156, 20, 20);
		g.drawRoundRect(24, 160, 457, 320, 20, 20);
		help.drawObjective(g, game.getCards());
		resetFont(g, 22);	
		resetFont(g, 40);
		String color = game.curPlayer().getColor();	
		g.setColor(new Color(121, 121, 121));
		g.fillRect(222, 15, 275, 55);

		help.drawTokenList(g);
		help.drawSettlement(g, color);
		help.setFontSize(g, 40);
		g.drawString("Player: " + player, 250, 58);
		help.drawRemainingSettlements(g);

	}
	public void drawNext(Graphics g){
		g.setColor(new Color(48,81,110, 127));
		g.fillRoundRect(412, 247, 492 - 412, 283 - 247, 20, 20);
		g.setColor(Color.white);
		g.drawRoundRect(412, 247, 492 - 412, 283 - 247, 20, 20);
		g.setFont(new Font("Castellar", 1, 20));
		g.drawString("NEXT",  422, 272);
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}



	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(viewCards){
			viewCards = false;
			repaint();
			return;
		}
		
		if(viewStandings){
			viewStandings = false;
			repaint();
			return;
		}
		

		System.out.println(x+ " " + y);
		if(x >= 27 && x <= 403 && y >= 98 && y <= 286 && gameState >= 4 && !viewCards){
			viewCards = true;
			repaint();
			return;
		}
		if(!viewCards){
			//335, 24, 351 - 200, 655 - 617,
			if(x >= 335 && x <= 486 && y >= 24 && y <= 62 && gameState == 5){
				objCard = -1;
				scorePlayer = -1; 
				moveSettlement = false;
				locpicked = 0;
				game = new Game();
				help.setGame(game);
				locclass = new locationClass(game, help);
				gameState = 0;
				viewCards = false;
				UsedLocs = new ArrayList<>();
				copy = new ArrayList<>();
				game.setCards(help.get3Obj());
				repaint();
			}
//				g.drawRoundRect(335, 65, 351 - 200, 655 - 617, 20, 20);

			if(x >= 335 && x <= 486 && y >= 65 && y <= 103 && gameState == 5){
				viewStandings = true;
				repaint();
				return;
				//do the stnadings
			}
			if(x >= 412 && x <= 492 && y >= 247 && y <= 282 && gameState == 4 && next){
				next = false;
				scorePlayer++;
				if(scorePlayer == 4){
					scorePlayer = 0;
					objCard++;
				}	
				if( objCard == 4){
					gameState = 5;
					repaint();
					return;
				}	
				//call method	
				
				if(objCard <= 2) {
					int scored = score.score(game.getCards().get(objCard), game.getPlayers().get(scorePlayer));
					game.getPlayers().get(scorePlayer).getScores()[objCard]  = scored;
					
				}
				else{
					int scored = score.scoreCastle(game.getPlayers().get(scorePlayer));
					game.getPlayers().get(scorePlayer).getScores()[objCard]  = scored;
				}
				repaint();
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					@Override
					public void run(){
						next = true;
						repaint();
					}
				}, 1000);
			}
		}
		if(gameState< 4){
		if(x >= 13 && x <= 209 && y >= 13 && y <= 113 && !viewCards){
			viewCards = true;
			repaint();
			return;
		}
		if(!viewCards){
		if(locpicked >0){
			if((locpicked == 9 || locpicked == 13 || locpicked == 14 || locpicked == 15 || locpicked == 16) && locclass.action(locpicked, game.curPlayer(),  x, y)) locpicked = 0;
			else if((locpicked == 11 || locpicked == 10 || locpicked == 12) && !moveSettlement){ //player hasnt picked settlementt to move
				if(locclass.pickingSettlements(game.curPlayer(), x, y)){
					moveSettlement = true;
					repaint();
					return;
				}
			}
			else if((locpicked == 11 || locpicked == 10 || locpicked == 12) && moveSettlement){ //player has pikced settlement to move
				if(locclass.action(locpicked, game.curPlayer(), x, y)){
					locpicked = 0;
					moveSettlement = false;

					repaint();
					return;
				}
			}
		}
		if(gameState == 0 && x >= 27 && x <= 121 && y >= 503 && y <= 653){
			game.drawCard();
			gameState = 1;
		    ArrayList<Integer> curLocation = game.curPlayer().getLoc();
			for(int i = 0; i<curLocation.size(); i++){
				copy.add(curLocation.get(i));
			}
		}
		if(usedSettlements == false && x >= 226 && x <= 468 && y >= 507 && y <= 546 && !usedTokens){
			//settlement
			usedSettlements = true;
		}
		if(usedTokens == false && x >= 225 && x <= 470 && y >= 557 && y <= 643 && game.curPlayer().getLoc().size()>0 && (game.curPlayer().curSettlements() == 0 || game.curPlayer().curSettlements() == 3 || game.curPlayer().getSettlement() == 0)){
			//token
			if(game.curPlayer().getSettlement()>3)
			usedTokens = true;
		}
		//if(usedTokens && coordinates click the done button, then make usedTokens to false)
		if(usedTokens && x>=39 && x<=108 && y>=130 && y<=146 && locpicked == 0){
			usedTokens = false;
			if(usedSettlements){
				gameState = 2;
			}
			else{
				gameState = 1;
			}
		}
		
		if(usedTokens && x>=25 && x<=480 && y>= 158 && y<=477 && game.curPlayer().getLoc().size()>0){
			int arr[] =  game.locTile.getNumbers(copy);
			for(int i = 0; i<arr.length; i++){
				if(arr[i]>=1){
					if(i<4){
						if(x>=24 && x<=250 && y>=158+i*80 && y<=234+i*80){
						removing(i);
					}
				}
					else{
						if(x>=251 && x<=481 && y>=158+((i-4)*80) && y<=234+((i-4)*80)){
						removing(i);
						}
					}
				}
			}
		}


		if(x >= 515 && x <= 1255 && y >= 15 && y <= 652 && usedSettlements){
			if(game.curPlayer().curSettlements() < 3 && game.curPlayer().getSettlement() > 0){
				Hex hex = game.getBoard().getHex(x, y);
				if(hex.getType() == game.curPlayer().getTerrainCard().getType() && hex.getColor().length() == 0 && hex.gray){
					if(game.curPlayer().curSettlements() == 2) gameState = 2;
					hex.setColor(game.curPlayer().getColor());
					game.curPlayer().useSettlement();
					
				
					//here is where we would change the gamestate to end screen 
					//get coord of the actual hexdrwe
					int boardX = hex.getX();
					int boardY = hex.getY();
					//if the settlement touches location tiles
					if(game.CheckLocTiles(boardX, boardY)){
					    // if that settlement is the only one touching it, 
						Hex temp[][] =  game.getBoard().getHexes();
						Hex locHex = temp[game.getCurLocX()][game.getCurLocY()];
						int checkIfAvailable = game.checkAround(game.getCurLocX(), game.getCurLocY());
						if(checkIfAvailable == 1 && locHex.getLoc()>0){
							//set player a token
							int locType = locHex.getType();
							locHex.decLoc();
							game.curPlayer().addLocTile(locType);
						}
					}
					if(game.curPlayer().getSettlement() == 0){
						//set boolean to true 
					   playerDone = true;
					   gameState=2;
					   //usedSettlements = false;
					   repaint();
					   return;
					}
				}	
			}
		}

		if(gameState == 2 && x >= 222 && y >= 74 && x <= 498 && y <= 109 ){

			player++;
			if(player >= 5) player = 1;
			usedSettlements = false;
			usedTokens = false;
			game.curPlayer().resetSettlements();
			game.changePlayer();
			gameState = 0;
			copy = new ArrayList<>();
			if(player == 1 && playerDone){ 
				gameState = 4;
				objCard = 0;
				scorePlayer = 0;
				next = true;
				int s = score.score(game.getCards().get(objCard), game.getPlayers().get(scorePlayer));
				System.out.println(s);
				game.getPlayers().get(scorePlayer).getScores()[objCard]  = s;
				repaint();
			}

		}
		
		if(game.curPlayer().curSettlements() == 3 && usedSettlements){
			gameState = 2;
		}
		//

		repaint();
		}
	}
	}

	
	public boolean validHex(Hex hex){
		return hex.getType() == game.curPlayer().getTerrainCard().getType() && hex.getColor().length() == 0;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override //use this for mouse motion
	public void mouseMoved(MouseEvent e) {
	}
	
	public void removing(int i){
		int locType = game.locTile.getLocation(i);
		locpicked = locType;
		for(int j = 0; j<copy.size(); j++){
			if(copy.get(j) == locType){
				copy.remove(j);
				repaint();
			}
		}
	}
}
