import java.awt.Color;

public class Circle {
	final int DIAMETER = 30;
	final int RADIUS = DIAMETER / 2;
	int x;
	int y;
	int velocity_x = 2;
	int velocity_y = 2;
	Color color;
	
	public Circle(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public double distanceTo(Circle circle) {
		int distance_x = Math.abs(this.x - circle.x);
		int distance_y = Math.abs(this.y - circle.y);
		return Math.sqrt(distance_x*distance_x + distance_y*distance_y);
	}
	
	public boolean checkCollisionWith(Circle circle) {
		return this.distanceTo(circle) < 2*RADIUS;
	}
	
	public void handleBorderCollision() {
		int bt = MainPanel.BORDER_THICKNESS;
		if(x + RADIUS > MainFrame.WIDTH - bt || x - RADIUS < bt) {
			velocity_x *= -1;
			System.out.println(this.x);
		}
		if(y + RADIUS > MainFrame.HEIGHT - bt || y - RADIUS < bt) {
			velocity_y *= -1;
			System.out.println(this.y);
		}
	}
}
