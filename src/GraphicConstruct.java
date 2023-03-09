import javax.swing.JFrame;

public class GraphicConstruct extends JFrame {

	private static final int WIDTH = 1600;
	private static final int HEIGHT = 960;

	public GraphicConstruct (String title) {
		super(title);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new KingdomPanel());

		setVisible(true);
	}

}