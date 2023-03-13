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
	private static BufferedImage canyon, desert, flower, forest, grass, back;
	public int sectwidth = 381, sectheight = 322;
	public int hexwidth = 38, hexlength = 44;
	double  gridHeight = 31.25, gridWidth = 36.25;
	ObjectiveCard objC;
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
	public void drawGray(Graphics g){
		for(int c = 0; c < 20; c++){
			for(int d = 0; d < 20; d++){
				Hex hex = game.getBoard().getHexes()[c][d];
				if(hex.gray){
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
			drawGray(g);
			g.drawImage(game.getCurrTerrain().getImage(), 121, 503, 94, 150, null);

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

		
		g.drawRect(312, 13, 182, 150);

		g.drawString("Tokens", 245, 525);
		g.drawString("Settlements", 355, 610);

		g.setFont(new Font("Castellar", 1, 40));
		g.drawString("View Cards", 55, 195);
		g.setFont(new Font("Castellar", 1, 60));
		g.drawString("Player 1" , 140, 290);


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
				game.setCurrTerrain(game.deck.getNext());
				game.curPlayer().setType(game.getCurrTerrain().getType());
				//System.out.println("type"+ " " + curCard.getType());
		
				gameState++;
		}
		if(x >= 515 && x <= 1255 && y >= 15 && y <= 652 && gameState == 1 ){
			//game.getBoard().getHex(x, y, gridHeight, gridWidth).setGray(false);
			game.getBoard().getHex(x, y, gridHeight, gridWidth).setColor(game.curPlayer().getColor());
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

	public static BufferedImage terrainCard(int card){
		switch (card) {
			case 1:
			  return desert;
			case 2:
			  return grass;
			case 3:
			  return flower;
			case 4:
			  return canyon;
		}
		return forest;

	}
}