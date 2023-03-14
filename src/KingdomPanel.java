import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
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
	private static BufferedImage sector1, hexagon, background, blackhouse, bluehouse, orangehouse, whitehouse, backTerrain;
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
			
			blackhouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/blackhouse.png"));
			bluehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/bluehouse.png"));
			orangehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/orangehouse.png"));
			whitehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/whitehouse.png"));
			background =   ImageIO.read(getClass().getResourceAsStream("/Board/Images/background.jpg"));
			sector1 = ImageIO.read(getClass().getResourceAsStream("/Board/Images/sector1.png"));
			hexagon = ImageIO.read(getClass().getResourceAsStream("/Board/Images/hexagon.png"));
			backTerrain =  ImageIO.read(getClass().getResourceAsStream("/Card/TerrainImages/KB-Card-Back.png"));

		} catch (Exception E) {
			System.out.println("Exception Error");
			return;
		}
		addMouseListener(this);
	}
	public void drawSettlements(Graphics g){
		for(int c = 0; c < 20; c++){
			for(int d = 0; d < 20; d++){
				Hex hex = game.getBoard().getHexes()[c][d];	
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
				Hex hex = game.getBoard().getHexes()[c][d];
				if(!combined[c][d]){
					if(c%2 == 0)g.drawImage(hexagon, 515 + d * (hexwidth - 2), 19 + c * (hexlength - 13), hexwidth, hexlength, null);
					else g.drawImage(hexagon, 533 + d * (hexwidth - 2), 19 + c * (hexlength-13), hexwidth, hexlength, null);
				}		
			}
		}
	}

	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(sector1, 515,19, sectwidth, sectheight, null);
		g.drawImage(sector1, 515 + 361,19, sectwidth, sectheight, null);
		g.drawImage(sector1, 515,19 + 313, sectwidth, sectheight, null);
		g.drawImage(sector1, 515 + 361,19 + 313, sectwidth, sectheight, null);
		drawSettlements(g);
		//if player is placing 
		if(gameState == 1){
			System.out.println("AWDAWD");
			boolean arr[][] = game.getBoard().combineAvailable(game.curPlayer().getTerrainCard().getType());
			drawGray(g, arr);
			g.drawImage(game.curPlayer().getTerrainCard().getImage(), 121, 503, 94, 150, null);
		}
		if(gameState == 2){
			g.fillRect(312, 13, 182, 75);
			g.setFont(new Font("Castellar", 1, 25));
			g.setColor(Color.white);
			g.drawString("End Turn", 325, 58);
		}
		
		//board
		//tokens and settlements
		g.setColor(Color.white);
		g.drawRect(24, 500, 457, 156);
		g.drawRect(75, 313, 348, 149);
		//objective cards
		g.drawImage(objCard.get(0), 12, 13, 94, 150, null);
		g.drawImage(objCard.get(1), 105, 13, 92, 150, null);
		g.drawImage(objCard.get(2), 196, 13, 92, 150, null);
		//draw blank card
		g.drawImage(backTerrain, 27, 503, 94, 150, null);

		//draw Chosen IF chosen

		g.setFont(new Font("Castellar", 1, 15));


		g.drawString("Tokens", 245, 525);
		g.drawString("Settlements", 350, 610);

		g.setFont(new Font("Castellar", 1, 20));
		g.drawString("View Cards", 80 , 195);
		g.setFont(new Font("Castellar", 1, 60));

		String color = game.curPlayer().getColor();
		
		g.setColor(new Color(211, 211, 211));
		g.fillRect(45, 225, 403, 75);

		if(color == "orange"){
			g.setColor(new Color(255, 165, 0));
		}
		else if(color == "black"){
			g.setColor(Color.black);
		}
		else if(color == "blue"){
			g.setColor(Color.blue);
		}
		else{
			g.setColor(Color.white);
		}
		g.drawString("Player: " + player, 75, 290);


		//objective cards:


		//g.setFont(new Font("Times New Roman"))
		//g.drawRect(600, 20, 650, 650);
		//Baskerville Old Face, Berlin Sans FB, Bernard MT Condensed, Blackadder ITC, Bodoni MT Black, Britannic Bold, Broadway, Castellar, Colonna MT, Cooper Black, Engravers MT
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
		System.out.println("loc is (" + x + "," + y + ")");
		xpos = x; ypos = y;
		if(gameState == 0 && x >= 27 && x <= 121 && y >= 503 && y <= 653){
				game.drawCard();
				gameState++;
		}
		if(x >= 515 && x <= 1255 && y >= 15 && y <= 652 && gameState == 1 ){
			//game.getBoard().getHex(x, y, gridHeight, gridWidth).setGray(false);
			//highlight first
			//and the coordinates are within the available terrains
			if(game.curPlayer().curSettlements() < 3){

				Hex hex = game.getBoard().getHex(x, y, gridHeight, gridWidth);
				if(hex.getType() == game.curPlayer().getTerrainCard().getType() && hex.getColor().length() == 0){
					if(game.curPlayer().curSettlements() == 2) gameState++;			
					hex.setColor(game.curPlayer().getColor());
					game.curPlayer().useSettlement();
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override //use this for mouse motion
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public static BufferedImage settlementColor(String color){
		if(color.equals("orange")) return orangehouse;
		if(color.equals("black")) return blackhouse;
		if(color.equals("white")) return whitehouse;
		else return bluehouse;
	}

	
}