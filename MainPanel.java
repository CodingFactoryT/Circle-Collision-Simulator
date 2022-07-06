import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MainPanel extends JPanel {
	public static final int BORDER_THICKNESS = 10;
	Circle circle = new Circle(30, 30, Color.red);
	Timer timer = new Timer(1, new MovementHandler());

	public MainPanel() {
		this.setBounds(0,0,MainFrame.WIDTH, MainFrame.HEIGHT);
		this.setBorder(BorderFactory.createLineBorder(new Color(0x487F66), BORDER_THICKNESS));
		this.setBackground(new Color(0x28424C));
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(circle.color);
		g2d.fillOval(circle.x - circle.RADIUS,circle.y - circle.RADIUS,circle.DIAMETER,circle.DIAMETER);
	}
	
	public class MovementHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			circle.x += circle.velocity_x;
			circle.y += circle.velocity_y;
			circle.handleBorderCollision();
			repaint();
		}
		
	}
}
