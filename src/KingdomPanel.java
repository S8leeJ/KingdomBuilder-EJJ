import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class KingdomPanel extends JPanel implements MouseListener, MouseMotionListener{
	private BufferedImage Hermit;
	private BufferedImage sector1, hexagon;
	public int sectwidth = 381, sectheight = 322;
	public int hexwidth = 37, hexlength = 43;
	public KingdomPanel() {

		try {       
			sector1 = ImageIO.read(getClass().getResourceAsStream("/Board/Images/sector1.png"));
			hexagon = ImageIO.read(getClass().getResourceAsStream("/Board/Images/hexagon.png"));
            Hermit = ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/HermitsObjective.png"));
		} catch (Exception E) {
			System.out.println("Exception Error");
			return;
		}
	
		addMouseListener(this);
		
	}
	public void drawHex(Graphics g){
		for(int c = 0; c < 20; c++){
			for(int d = 0; d < 20; d++){
				if(c%2 == 0)g.drawImage(hexagon, 517 + d * (hexwidth -1), 20 + c * (hexlength - 12), hexwidth, hexlength, null);
				else g.drawImage(hexagon, 535 + d * (hexwidth -1), 20 + c * (hexlength-12), hexwidth, hexlength, null);
				
			}
		}
	}
	public void paint(Graphics g) {
		g.drawImage(sector1, 515,19, sectwidth, sectheight, null);
		g.drawImage(sector1, 515 + 361,19, sectwidth, sectheight, null);
		g.drawImage(sector1, 515,19 + 310, sectwidth, sectheight, null);
		g.drawImage(sector1, 515 + 361,19 + 310, sectwidth, sectheight, null);

		drawHex(g);
		

		
		
		//board
		
		//tokens and settlements
		g.drawRect(10, 500, 500, 180);
		//g.drawRect(600, 20, 650, 650);

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
}