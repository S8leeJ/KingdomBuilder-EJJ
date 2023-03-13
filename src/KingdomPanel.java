import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Board.Hex;
import Card.terrain;
import Game.Game;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class KingdomPanel extends JPanel implements MouseListener, MouseMotionListener{
	Game game;
	int gameState;
	private BufferedImage Hermit;
	int xpos, ypos;
	private static BufferedImage sector1, hexagon, background, blackhouse, bluehouse, orangehouse, whitehouse;
	public int sectwidth = 381, sectheight = 322;
	public int hexwidth = 37, hexlength = 43;
	double  gridHeight = 31.25, gridWidth = 36.25;
	int index = 0;
	String fonts[] = new String [] {"Baskerville Old Face", "Berlin Sans FB", "Bernard MT Condensed", "Blackadder ITC", "Bodoni MT Black","Britannic Bold", "Broadway", "Castellar", "Colonna MT", "Cooper Black", "Engravers MT"};

	public KingdomPanel() {
		game = new Game();
		gameState = 0;
		try {     
			blackhouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/blackhouse.png"));
			bluehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/bluehouse.png"));
			orangehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/orangehouse.png"));
			whitehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/whitehouse.png"));
			background =   ImageIO.read(getClass().getResourceAsStream("/Board/Images/background.jpg"));
			sector1 = ImageIO.read(getClass().getResourceAsStream("/Board/Images/sector1.png"));
			hexagon = ImageIO.read(getClass().getResourceAsStream("/Board/Images/hexagon.png"));
            Hermit = ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/HermitsObjective.png"));
			
		} catch (Exception E) {
			System.out.println("Exception Error");
			return;
		}
	
		addMouseListener(this);
		
	}
	public void drawGray(Graphics g){
		for(int c = 0; c < 20; c++){
			for(int d = 0; d < 20; d++){
				Hex hex = game.getBoard().getHexes()[c][d];
				if(hex.gray){
					if(c%2 == 0)g.drawImage(hexagon, 517 + d * (hexwidth -1), 20 + c * (hexlength - 12), hexwidth, hexlength, null);
					else g.drawImage(hexagon, 535 + d * (hexwidth -1), 20 + c * (hexlength-12), hexwidth, hexlength, null);
				}
				if(hex.getColor().length() > 0){
					if(c%2 == 0)g.drawImage(settlementColor(hex.getColor()), 517 + d * (hexwidth -1), 20 + c * (hexlength - 12), hexwidth, hexlength, null);
					else g.drawImage(settlementColor(hex.getColor()), 535 + d * (hexwidth -1), 20 + c * (hexlength-12), hexwidth, hexlength, null);				
				}
				
			}
		}
	}
	public void paint(Graphics g) {
		index = (index + 1) % fonts.length;
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(sector1, 515,19, sectwidth, sectheight, null);
		g.drawImage(sector1, 515 + 361,19, sectwidth, sectheight, null);
		g.drawImage(sector1, 515,19 + 313, sectwidth, sectheight, null);
		g.drawImage(sector1, 515 + 361,19 + 313, sectwidth, sectheight, null);

		drawGray(g);
		

		
		
		//board
		
		//tokens and settlements
		g.setColor(Color.white);
		g.drawRect(24, 500, 457, 156);
		g.drawRect(75, 313, 348, 149);
		g.drawRect(12, 13, 280, 150);
		g.drawLine(105, 13, 105, 163);
		g.drawLine(199, 13, 199, 163);
		g.drawRect(312, 13, 182, 150);
		g.setFont(new Font(fonts[index], 1, 40));
		System.out.println(fonts[index]);
		g.drawString("View Cards", 55, 195);
		g.setFont(new Font(fonts[index], 1, 60));

		g.drawString("Player 1", 140, 290);
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
		if(gameState == 0){
			terrain curCard = game.TerrainDecks.getNext();
			game.getFirst().setType(curCard.getType());
			System.out.println("type"+ " " + curCard.getType());
			System.out.println("AOWDUHAWODHIAWLIDHOAIWdh");
			gameState++;
		}
		if(x >= 515 && x <= 1255 && y >= 15 && y <= 652 && gameState == 1){
			game.getBoard().getHex(x, y, gridHeight, gridWidth).setGray(false);
			game.getBoard().getHex(x, y, gridHeight, gridWidth).setColor("orange");
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