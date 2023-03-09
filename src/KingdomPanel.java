import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class KingdomPanel extends JPanel implements MouseListener {
	private BufferedImage Hermit;

	public KingdomPanel() {

		try {       
            
            Hermit = ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/HermitsObjective.png"));
		} catch (Exception E) {
			System.out.println("Exception Error");
			return;
		}
	
		addMouseListener(this);
	}

	public void paint(Graphics g) {
		g.drawImage(Hermit, 0,0, 100, 200, null);

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
}