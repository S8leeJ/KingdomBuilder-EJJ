import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

import Board.Board;
import Board.Hex;
import Card.TerrainCard;
import Game.Game;
import ObjectiveCards.ObjectiveCard;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class KingdomPanel extends JPanel implements MouseListener, MouseMotionListener{
	Game game;
	int gameState;
	boolean usedSettlements = false;
	boolean usedTokens = false;
	private static BufferedImage background;
	public int sectwidth = 381, sectheight = 322;
	public int hexwidth = 38, hexlength = 44;
	double  gridHeight = 31.25, gridWidth = 36.25;
	ObjectiveCard objC;
	int player = 1;
	ArrayList<BufferedImage> objCard;
	ArrayList<Integer> UsedLocs;
	ArrayList<Integer> copy;
	KingdomHelper help;
	locationClass locclass;
	boolean viewCards;
	int locpicked;
	//int x, y;

	public KingdomPanel() {
		locpicked = 0;
		game = new Game();
		help = new KingdomHelper(game);
		locclass = new locationClass(game);
		gameState = 0;
		objC = new ObjectiveCard();
	    objCard = objC.get3();
		viewCards = false;
		UsedLocs = new ArrayList<>();
		copy = new ArrayList<>();

		try {     
			background =   ImageIO.read(getClass().getResourceAsStream("/Board/Images/background.jpg"));
		
			} catch (Exception E) {
			System.out.println("Exception Error");
			return;
		}
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
		//draws the board image
		drawBoard(g);
		// when players turn starts
		if(gameState == 0){
			resetFont(g, 48);
			g.drawString("Draw a card", 50, 457);
		}
		if(gameState>=1 && usedSettlements && game.curPlayer().curSettlements()<3 && !usedTokens){
			resetFont(g, 25);
			g.drawString("Place Three Settlements", 55, 465);
			String color = game.curPlayer().getColor();
			boolean arr[][] = game.getBoard().getAvailable(game.curPlayer().getTerrainCard().getType(), color);
			help.drawGray(g, arr, game);
			//drawGray(g, arr);
		}
		//wen player picks between token or settlement
		if(gameState == 1 && !usedSettlements){
			resetFont(g, 21);
			g.drawString("Choose Tokens or Settlements", 40, 457);
		}
		//draws terrain card
		if(gameState >=1){
			g.drawImage(game.curPlayer().getTerrainCard().getImage(), 121, 503, 94, 150, null);
		}
		//if player picks to use tokens
		if(locpicked > 0){
			locclass.drawGray(locpicked, game.curPlayer(), g);
		}
		if(usedTokens){
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
			//System.out.println(Arrays.toString(arr));
			//here
			help.displayLocs(g, arr);
		}

		//if player is placing their 3 settlememts
		
		help.drawHexNumbers(g, game);
		help.drawSettlements(g);
		//once player has placed 3 settlements
		if(gameState == 2 && game.curPlayer().curSettlements()==3){
			g.setColor(new Color(202, 210, 235));
			g.fillRoundRect(223, 73, 494-223, 110-73, 20, 20);
			resetFont(g, 25);
			g.setColor(new Color(9, 25, 77));
			g.drawString("End Turn", 36+244, 190-65 - 26);
		}
		drawRest(g);	
		if(viewCards){
			//System.out.println("cardsa re drawn");
			help.drawViewCards(g, objCard);
		}	
	}
	
	public void drawRest(Graphics g){
		g.setColor(Color.white);
		g.drawRoundRect(24, 500, 457, 156, 20, 20);
		g.drawRoundRect(24, 160, 457, 320, 20, 20);
		//objective cards
		//drawObjective(g);
		help.drawObjective(g, objCard);
		//draw Chosen IF chosen
		resetFont(g, 22);
		//g.drawString("Tokens", 245, 540);
	
		resetFont(g, 40);
		String color = game.curPlayer().getColor();	
		g.setColor(new Color(28, 35, 61));
		g.fillRect(222, 15, 275, 55);

		//draw tokesn
		help.drawTokenList(g);
		help.drawSettlement(g, color);
		help.setFontSize(g, 40);
		g.drawString("Player: " + player, 250, 58);
		help.drawRemainingSettlements(g);

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
		System.out.println(x + " " + y);
		if(x >= 13 && x <= 209 && y >= 13 && y <= 113){
			viewCards = !viewCards;
			//System.out.println("viewcard true");
			repaint();
			return;
		}
		if(!viewCards){
		if(locpicked >0){
			if(locclass.action(locpicked, game.curPlayer(), getGraphics(), x, y)) locpicked = 0;
			// if(usedSettlements)
			// gameState = 2;
			// else
			// gameState = 1;
		}
		if(gameState == 0 && x >= 27 && x <= 121 && y >= 503 && y <= 653){
			game.drawCard();
			gameState+=1;
		    ArrayList<Integer> curLocation = game.curPlayer().getLoc();
			for(int i = 0; i<curLocation.size(); i++){
				copy.add(curLocation.get(i));
			}
		}
		if(usedSettlements == false && x >= 226 && x <= 468 && y >= 507 && y <= 546){
			//settlement
			usedSettlements = true;
		}
		if(usedTokens == false && x >= 225 && x <= 470 && y >= 557 && y <= 643 && game.curPlayer().getLoc().size()>0){
			//token
			usedTokens = true;
		}
		//if(usedTokens && coordinates click the done button, then make usedTokens to false)
		if(usedTokens && x>=39 && x<=108 && y>=130 && y<=146){
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


		if(x >= 515 && x <= 1255 && y >= 15 && y <= 652 && usedSettlements == true){
			if(game.curPlayer().curSettlements() < 3){
				Hex hex = game.getBoard().getHex(x, y);
				if(hex.getType() == game.curPlayer().getTerrainCard().getType() && hex.getColor().length() == 0 && hex.gray == true){
					if(game.curPlayer().curSettlements() == 2) gameState++;
					hex.setColor(game.curPlayer().getColor());
					game.curPlayer().useSettlement();
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
		}
		
		if(game.curPlayer().curSettlements() == 3 && usedSettlements){
			gameState = 2;
		}
		repaint();
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
