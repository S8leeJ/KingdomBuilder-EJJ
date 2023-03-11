import javax.swing.JFrame;

public class GraphicConstruct extends JFrame {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

	public GraphicConstruct (String title) {
		super(title);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new KingdomPanel());
		setResizable(false);
		setVisible(true);
		JFrame frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

}