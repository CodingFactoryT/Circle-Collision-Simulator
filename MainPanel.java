import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.ColorUIResource;

import MathLib.util.Vector2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MainPanel extends JPanel {
	public static final int BORDER_THICKNESS = 10;
	private ArrayList<Circle> circles = new ArrayList<>();
	private int[] circleColors = new int[]{0x59D9B8, 0x5ECC6F, 0x7875B7};
	
	Circle circle1 = new Circle(new Vector2D(600, 200), new Vector2D(0,3),  new Color(circleColors[0]));
	Circle circle2 = new Circle(new Vector2D(600, 700), new Vector2D(0,-3), new Color(circleColors[1]));

	Timer timer = new Timer(16, new MovementHandler());

	public MainPanel() {
		this.setBounds(0,0,MainFrame.WIDTH, MainFrame.HEIGHT);
		this.setBorder(BorderFactory.createLineBorder(new Color(0x487F66), BORDER_THICKNESS));
		this.setBackground(new Color(0x28424C));
		circles.add(circle1);
		circles.add(circle2);
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for(Circle circle : circles) {
			g2d.setColor(circle.color);
			g2d.fillOval((int) circle.position.x - circle.RADIUS, MainFrame.HEIGHT - (int) circle.position.y - circle.RADIUS,circle.DIAMETER,circle.DIAMETER);			
		}
	}
	
	public class MovementHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for(Circle circle : circles) {
				circle.position = Vector2D.add(circle.position, circle.velocity);
			}				
			
			for(Circle circle : circles) {
				circle.handleBorderCollision();
			}
			Circle.handleCircleCollision(circles);			
			
			repaint();
		}
		
	}
}
