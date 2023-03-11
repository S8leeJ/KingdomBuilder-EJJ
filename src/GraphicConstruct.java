import javax.swing.JFrame;

public class GraphicConstruct extends JFrame {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

	public GraphicConstruct (String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);

		//setResizable(false);
		//setVisible(true);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		//pack();
		add(new KingdomPanel());

		setVisible(true);

	}

}