import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
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
	int xpos, ypos;
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
	KingdomHelper help = new KingdomHelper();
	locationClass locclass;
	boolean viewCards;
	int x, y;

	public KingdomPanel() {
		game = new Game();
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
	public void drawSettlements(Graphics g){
		for(int c = 0; c < 20; c++){
			for(int d = 0; d < 20; d++){
				Hex board[][] = game.getBoard().getHexes();
				Hex hex = board[c][d];	
				if(hex.getColor().length() > 0){
					if(c%2 == 0)g.drawImage(help.settlementColor(hex.getColor()), 520 + d * (hexwidth -2), 24 + c * (hexlength - 13), hexwidth - 15, hexlength - 15, null);
					else g.drawImage(help.settlementColor(hex.getColor()), 538 + d * (hexwidth -2), 24 + c * (hexlength-13), hexwidth - 15, hexlength - 15, null);				
				}		
			}
		}
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
			g.drawString("Draw a card", 41, 380);
		}
		//wen player picks between token or settlement
		if(gameState == 1){
			resetFont(g, 15);
			g.drawString("Click on the 'TOKENS' or Settlement", 65, 400);
		}
		//draws terrain card
		if(gameState >=1){
			g.drawImage(game.curPlayer().getTerrainCard().getImage(), 121, 503, 94, 150, null);
		}
		//if player picks to use tokens
		if(gameState == 9){
			locclass.action(9, game.curPlayer(), x, y, g);
		}
		if(usedTokens){
			g.setColor(Color.black);			
			g.fillRoundRect(400, 490, 20, 20, 20, 20);
			resetFont(g, 20);
			g.drawString("Done", 41 , 147);
			int []arr = new int[8];
			arr = game.locTile.getNumbers(copy);
			help.displayLocs(g, arr);
		}

		//if player is placing their 3 settlememts
		if(gameState>=1 && usedSettlements && game.curPlayer().curSettlements()<3 && !usedTokens){
			String color = game.curPlayer().getColor();
			boolean arr[][] = game.getBoard().getAvailable(game.curPlayer().getTerrainCard().getType(), color);
			help.drawGray(g, arr, game);
			//drawGray(g, arr);
		}
		help.drawHexNumbers(g, game);
		drawSettlements(g);
		//once player has placed 3 settlements
		if(gameState == 2 && game.curPlayer().curSettlements()==3){
			g.setColor(new Color(202, 210, 235));
			g.fillRoundRect(223, 89, 496-223, 146-89, 20, 20);
			resetFont(g, 25);
			g.setColor(new Color(9, 25, 77));
			g.drawString("End Turn", 36+244, 190-65);
		}
		drawRest(g);		
	}
	public void drawRest(Graphics g){
		g.drawRect(24, 500, 457, 156);
		g.drawRect(24, 160, 457, 320);
		//objective cards
		//drawObjective(g);
		help.drawObjective(g, objCard);
		//draw Chosen IF chosen
		resetFont(g, 22);
		g.drawString("Tokens", 245, 525);
		g.drawString("X"+game.curPlayer().getSettlement(), 415, 640);
	
		resetFont(g, 40);
		String color = game.curPlayer().getColor();	
		g.setColor(new Color(28, 35, 61));
		g.fillRect(222, 15, 275, 55);

		//draw tokesn
		help.drawToken(g, game);
		help.drawSettlement(g, color);
		g.drawString("Player: " + player, 250, 58);
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
		x = e.getX();
		y = e.getY();
		System.out.println("("+x+" " + y+"(");
		System.out.println("GAMESTATE" + gameState);
		xpos = x; ypos = y;
		if(gameState == 0 && x >= 27 && x <= 121 && y >= 503 && y <= 653){
			game.drawCard();
			gameState+=1;
		    ArrayList<Integer> curLocation = game.curPlayer().getLoc();
			for(int i = 0; i<curLocation.size(); i++){
				copy.add(curLocation.get(i));
			}
		}
		if(usedSettlements == false && x>=371 && x<=462 && y<=639 && y>=459){
			//settlement
			usedSettlements = true;
		}
		if(usedTokens == false && x>=243 && x<=342 && y>=502 && y<=520 && game.curPlayer().getLoc().size()>0){
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
			int arr[] =  new int [8];
			arr = game.locTile.getNumbers(game.curPlayer().getLoc());
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
		repaint();


		if(x >= 515 && x <= 1255 && y >= 15 && y <= 652 && usedSettlements == true){
			if(game.curPlayer().curSettlements() < 3){
				Hex hex = game.getBoard().getHex(x, y, gridHeight, gridWidth);
				if(hex.getType() == game.curPlayer().getTerrainCard().getType() && hex.getColor().length() == 0 && hex.gray == true){
					if(game.curPlayer().curSettlements() == 2) gameState++;
					hex.setColor(game.curPlayer().getColor());
					game.curPlayer().useSettlement();
					//get coord of the actual hex
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

		if(gameState == 2 && x >= 222 && y >= 87 && x <= 498 && y <= 150 ){
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
		gameState = locType;
		for(int j = 0; j<copy.size(); j++){
			if(copy.get(j) == locType){
				copy.remove(j);
				repaint();
			}
		}
	}
}
