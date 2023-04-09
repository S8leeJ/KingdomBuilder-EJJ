import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import Board.Hex;
import Board.LocationTiles;
import Card.TerrainCard;
import Game.Game;
import ObjectiveCards.ObjectiveCard;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class KingdomPanel extends JPanel implements MouseListener, MouseMotionListener{
	Game game;
	double gameState;
	int xpos, ypos;
	private static BufferedImage sector1,sector2, gray, sector3, sector4, sector5, sector6, sector7, sector8, hexagon, background, blackhouse, bluehouse, orangehouse, whitehouse, backTerrain, locOne, locTwo, locations;
	public int sectwidth = 381, sectheight = 322;
	public int hexwidth = 38, hexlength = 44;
	double  gridHeight = 31.25, gridWidth = 36.25;
	ObjectiveCard objC;
	int player = 1;
	ArrayList<BufferedImage> objCard;
	public KingdomPanel() {
		game = new Game();
		gameState = 0;
		objC = new ObjectiveCard();
	    objCard = objC.get3();
		try {     
			sector2 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector2.png"));
			sector3 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector3.png"));
			sector4 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector4.png"));
			sector5 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector5.png"));
			sector6 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector6.png"));
			sector7 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector7.png"));
			sector8 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector8.png"));
			blackhouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/blackhouse.png"));
			bluehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/bluehouse.png"));
			orangehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/orangehouse.png"));
			whitehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/whitehouse.png"));
			background =   ImageIO.read(getClass().getResourceAsStream("/Board/Images/background.jpg"));
			sector1 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector1.png"));
			hexagon = ImageIO.read(getClass().getResourceAsStream("/Board/Images/hexagon.png"));
			backTerrain =  ImageIO.read(getClass().getResourceAsStream("/Card/TerrainImages/KB-Card-Back.png"));
			locOne =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/1.png"));
			locTwo =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/2.png"));
			locations = ImageIO.read(getClass().getResourceAsStream("/Board/Images/Locations.PNG"));
			gray = ImageIO.read(getClass().getResourceAsStream("/Board/Images/darkrect.png"));
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
					if(c%2 == 0)g.drawImage(settlementColor(hex.getColor()), 520 + d * (hexwidth -2), 24 + c * (hexlength - 13), hexwidth - 15, hexlength - 15, null);
					else g.drawImage(settlementColor(hex.getColor()), 538 + d * (hexwidth -2), 24 + c * (hexlength-13), hexwidth - 15, hexlength - 15, null);				
				}		
			}
		}
	}
	public void drawGray(Graphics g, boolean combined[][]){
		for(int c = 0; c < 20; c++){
			for(int d = 0; d < 20; d++){
				//Hex hex = game.getBoard().getHexes()[c][d];
				if(!combined[c][d]){
					if(c%2 == 0)g.drawImage(hexagon, 515 + d * (hexwidth - 2), 19 + c * (hexlength - 13), hexwidth, hexlength, null);
					else g.drawImage(hexagon, 533 + d * (hexwidth - 2), 19 + c * (hexlength-13), hexwidth, hexlength, null);
				}		
			}
		}
	}

	public void drawHexNumbers(Graphics g){
		for(int c = 0; c < 20; c++){
			for(int d = 0; d < 20; d++){
				Hex hex = game.getBoard().getHexes()[c][d];
				if(hex.getType()>8){
					//check the "number" of location tiles left and then display corresponding one 
					if(hex.getLoc() == 1){
					if(c%2 == 0)g.drawImage(locOne, 517 + d * (hexwidth - 2), 10 + c * (hexlength - 13),33, 33, null);
					else g.drawImage(locOne, 538 + d * (hexwidth - 2), 10 + c * (hexlength-13), 30, 30, null);
					}
					else if(hex.getLoc() == 2){
						if(c%2 == 0)g.drawImage(locTwo, 517 + d * (hexwidth - 2), 10 + c * (hexlength - 13),33, 33, null);
						else g.drawImage(locTwo, 538 + d * (hexwidth - 2), 10 + c * (hexlength-13), 30, 30, null);
						}
					}
				}		
			}
		}
	
	public void drawBoard(Graphics g){
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(getSector(game.oneid), 515,19, sectwidth, sectheight, null);
		g.drawImage(getSector(game.twoid), 515 + 361,19, sectwidth, sectheight, null);
		g.drawImage(getSector(game.threeid), 515,19 + 313, sectwidth, sectheight, null);
		g.drawImage(getSector(game.fourid), 515 + 361,19 + 313, sectwidth, sectheight, null);
	
	}
	
	public void drawObjective(Graphics g){
		g.drawImage(objCard.get(0), 12, 13, 65, 100, null);
		g.drawImage(objCard.get(1), 77, 13, 65, 100, null);
		g.drawImage(objCard.get(2), 142, 13, 65, 100, null);
		g.drawImage(backTerrain, 27, 503, 94, 150, null);
		resetFont(g, 20);
		g.drawString("View Cards", 41 , 137);
	
	}
	public void resetFont(Graphics g, int size){
		g.setFont(new Font("Castellar", 1, size));
		g.setColor(Color.white);
	}

	public void drawToken(Graphics g){
		ArrayList<Integer> curLocs = new ArrayList<>();
		curLocs = game.curPlayer().getLoc();
		if(curLocs.size() > 0){
			for(int i = 0; i<curLocs.size(); i++){
				g.drawImage(LocationTiles.getLoc(curLocs.get(i)), 250 + i*40, 540, 40, 40, null);
			}
		}
	}
	public void drawSettlement(Graphics g, String color){
		if(color == "orange"){
			g.setColor(new Color(255, 180, 0));
			g.drawImage(orangehouse, 375, 615, 30,30, null);
		}
		else if(color == "black"){
			g.setColor(Color.black);
			g.drawImage(blackhouse, 375, 615, 30,30, null);
		}
		else if(color == "blue"){
			g.setColor(new Color(73, 134, 231));
			g.drawImage(bluehouse, 375, 615, 30,30, null);
		}
		else{
			g.setColor(Color.white);
			g.drawImage(whitehouse, 375, 615, 30,30, null);
		}
	}




	public void paint(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
		//if player is placing 
		if(gameState == 0){
			resetFont(g, 48);
			g.drawString("Draw a card", 41, 380);
		}
		if(gameState == 0.5){
			resetFont(g, 15);
			g.drawString("Click on the 'TOKENS' or Settlement", 65, 400);
		}
		if(gameState >=0.5 && gameState<=2){
			g.drawImage(game.curPlayer().getTerrainCard().getImage(), 121, 503, 94, 150, null);
		}
		if(gameState == 0.75){
			if(game.curPlayer().getLoc().size() == 0){
				gameState+=.25;
			}
			else{
				resetFont(g, 15);
				g.drawImage(locations, 24, 160, 457, 320, null);
				System.out.println("Players locationTiles: " + game.curPlayer().getLoc());
				int arr[] =  new int [8];
				arr = game.locTile.getNumbers(game.curPlayer().getLoc());
				System.out.println("PLAYER DISPLAY NUMBER");
				for(int i = 0; i<arr.length; i++){
					if(arr[i]!=0){
						resetFont(g, 15);
						if(i<4)
						g.drawString(arr[i]+"", 216, 205+i*80);
						else
						g.drawString(arr[i]+"", 452, 205+(i-4)*80);
					}
					else{
						if(i<4)
						g.drawImage(gray, 27, 158+(80*i), 230, 80, null);
						else{
						g.drawImage(gray, 253, 158+(80*(i-4)), 230, 80, null);

						}
					}
					System.out.print(arr[i]);
				}
				//216, 194
				// +80
				//452, 192
				//let player choose which token to use, by 
				//then player choose, temporarity removing, then draw dray based on that 
				//move to mouse clicker: based on which, check the test cases for those
			}
			gameState+=.25;
		}
		if(gameState == 1){
			String color = game.curPlayer().getColor();
			boolean arr[][] = game.getBoard().combineAvailable(game.curPlayer().getTerrainCard().getType(), color);
			drawGray(g, arr);
		}
		drawHexNumbers(g);
		drawSettlements(g);

		if(gameState == 2){
			g.setColor(new Color(202, 210, 235));
			g.fillRect(22+244, 145-56, 177, 70);
			resetFont(g, 25);
			g.setColor(new Color(9, 25, 77));
			g.drawString("End Turn", 32+244, 190-56);
		}
		
		//board
		//tokens and settlements
		
		g.drawRect(24, 500, 457, 156);
		g.drawRect(24, 160, 457, 320);
		//objective cards
		drawObjective(g);
		//draw Chosen IF chosen
		resetFont(g, 22);
		g.drawString("Tokens", 245, 525);
		g.drawString("X"+game.curPlayer().getSettlement(), 415, 640);

		
		resetFont(g, 40);
		String color = game.curPlayer().getColor();	
		g.setColor(new Color(28, 35, 61));
		g.fillRect(222, 15, 275, 55);

		//draw tokesn
		drawToken(g);
		drawSettlement(g, color);
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
		int x = e.getX();
		int y = e.getY();
		System.out.println("("+x+" " + y+"(");
		if(x >= 515 && x <= 1255 && y >= 15 && y <= 652){
			game.getBoard().getHex(x, y, gridHeight, gridWidth);
		}
		xpos = x; ypos = y;
		if(gameState == 0 && x >= 27 && x <= 121 && y >= 503 && y <= 653){
				game.drawCard();
				gameState+=0.5;
		}
		if(gameState == 0.5 && x>=371 && x<=462 && y<=639 && y>=616){
			//settlement
			gameState+=0.5;
		}
		if(gameState == 0.5 && x>=243 && x<=342 && y>=502 && y<=520){
			//token
			gameState+=.25;
		}
		//if x >= 515 && x <= 1255 && y >= 15 && y <= 652 && gameState == 0.75, you are using a token
		//move your hex or whatever  
		if(x >= 515 && x <= 1255 && y >= 15 && y <= 652 && gameState == 1 ){

			if(game.curPlayer().curSettlements() < 3){
				Hex hex = game.getBoard().getHex(x, y, gridHeight, gridWidth);
				if(hex.getType() == game.curPlayer().getTerrainCard().getType() && hex.getColor().length() == 0){
					if(game.curPlayer().curSettlements() == 2) gameState++;			
					hex.setColor(game.curPlayer().getColor());
					game.curPlayer().useSettlement();
					//get coord of the actual hex
					int boardX = hex.getX();
					int boardY = hex.getY();
					//if the settlement touches location tiles
					System.out.println(game.CheckLocTiles(boardX, boardY));
					if(game.CheckLocTiles(boardX, boardY)){
					    // if that settlement is the only one touching it, 
						Hex temp[][] =  game.getBoard().getHexes();
						Hex locHex = temp[game.getCurLocX()][game.getCurLocY()];
						int checkIfAvailable = game.checkAround(game.getCurLocX(), game.getCurLocY());
						if(checkIfAvailable == 1 && locHex.getLoc()>0){
							//set player a token
							int locType = game.getTypeLoc();
							locHex.decLoc();
							System.out.println(locHex.getLoc());
							game.curPlayer().addLocTile(locType);
						}
					}
				}	
			}
		}
		if(gameState == 2 && x >= 312 && y >= 12 && x <= 494 && y <= 163){
			player++;
			if(player == 5) player = 1;
			game.curPlayer().resetSettlements();
			game.changePlayer();
			gameState = 0;
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
	public static BufferedImage settlementColor(String color){
		if(color.equals("orange")) return orangehouse;
		if(color.equals("black")) return blackhouse;
		if(color.equals("white")) return whitehouse;
		else return bluehouse;
	}
	public BufferedImage getSector(int id){
		if(id == 1) return sector1;
		if(id == 2) return sector2;
		if(id == 3) return sector3;
		if(id == 4) return sector4;
		if(id == 5) return sector5;
		if(id == 6) return sector6;
		if(id == 7) return sector7;
		return sector8;

	}
}
