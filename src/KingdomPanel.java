import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class KingdomPanel extends JPanel implements MouseListener, MouseMotionListener{
	private BufferedImage Hermit;

	public KingdomPanel() {

		try {       
            
            Hermit = ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/HermitsObjective.png"));
		} catch (Exception E) {
			System.out.println("Exception Error");
			return;
		}
	
		addMouseListener(this);
	}

	public void paint(Graphics g) {
		g.drawImage(Hermit, 0,0, 100, 200, null);
		//board
		g.drawRect(600, 20, 325, 325);
		g.drawRect(600+325, 20, 325, 325);
		g.drawRect(600, 20+325, 325, 325);
		g.drawRect(600+325, 20+325, 325, 325);
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