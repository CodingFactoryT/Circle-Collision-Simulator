import java.awt.Color;
import java.util.ArrayList;

import javax.imageio.plugins.tiff.ExifGPSTagSet;
import javax.swing.RepaintManager;

import MathLib.util.Vector2D;

public class Circle {
	final int DIAMETER = 30;
	final int RADIUS = DIAMETER / 2;
	Vector2D position;
	final double mass = Math.PI*RADIUS*RADIUS;
	Vector2D velocity;
	Color color;
	
	public Circle(Vector2D position, Vector2D velocity, Color color) {
		this.position = position;
		this.velocity = velocity;
		this.color = color;
	}
	
	public double squaredDistanceTo(Circle circle) {
		double distance_x = Math.abs(this.position.x - circle.position.x);
		double distance_y = Math.abs(this.position.y - circle.position.y);
		return distance_x*distance_x + distance_y*distance_y;
	}
	
	public boolean checkCollisionWith(Circle circle) {
		return this.squaredDistanceTo(circle) <= 4*RADIUS*RADIUS;
	}
	
	public void handleBorderCollision() {
		int bt = MainPanel.BORDER_THICKNESS;
		
		if(position.x + RADIUS >= MainFrame.WIDTH - bt || position.x - RADIUS <= bt) velocity = Vector2D.multiply(velocity, new Vector2D(-1, 1));
		if(position.y + RADIUS >= MainFrame.HEIGHT - bt || position.y - RADIUS <= bt) velocity = Vector2D.multiply(velocity, new Vector2D(1, -1));
	}

	public static void handleCircleCollision(ArrayList<Circle> circles) {
		Circle c1 = circles.get(0);
		Circle c2 = circles.get(1);
		
		if(c1.checkCollisionWith(c2)) {	
			System.out.println("Collision occured");
			Vector2D c1Velocity = calculateNewVelocity(c1, c2);
			Vector2D c2Velocity = calculateNewVelocity(c2, c1);
			c1.velocity = c1Velocity;
			c2.velocity = c2Velocity;
			System.out.println(c1Velocity.x + "|" + c1Velocity.y);
			System.out.println(c2Velocity.x + "|" + c2Velocity.y);
		}
	}
	
	public static Vector2D calculateNewVelocity(Circle c1, Circle c2) {
		Vector2D p1 = c1.position;
		Vector2D p2 = c2.position;
		Vector2D v1 = c1.velocity;
		Vector2D v2 = c2.velocity;

		double first = 2*c2.mass / (c1.mass+c2.mass);
		Vector2D second_upper = Vector2D.scalarProduct(Vector2D.subtract(v1, v2), Vector2D.subtract(p1, p2));
		double second_lower = Math.pow(Vector2D.subtract(p1, p2).magnitude(), 2);
		Vector2D second = second_upper.divide(second_lower);
		Vector2D third = Vector2D.subtract(p1, p2);
		
		return Vector2D.subtract(v1, Vector2D.multiply(second.multiply(first), third));
	}
}
