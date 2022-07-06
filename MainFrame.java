import javax.swing.JFrame;

import java.awt.Dimension;

public class MainFrame extends JFrame {
	public static final int WIDTH = 1500;
	public static final int HEIGHT = 800;
	
	private MainPanel mainPanel = new MainPanel();
	
	public MainFrame() {
		this.setTitle("Circle Collision Simulator");
		this.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.add(mainPanel);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
