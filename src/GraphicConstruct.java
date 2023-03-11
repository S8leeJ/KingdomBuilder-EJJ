import javax.swing.JFrame;

public class GraphicConstruct extends JFrame {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

	public GraphicConstruct (String title) {
		super(title);
		//setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new KingdomPanel());
		//setResizable(false);
		//setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		pack();
		setVisible(true);
	}

}