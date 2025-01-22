import java.awt.Color;
import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.geom.AffineTransform;
	import java.net.URL;
public class redCar {
	private Image forward, backward, left, right;
	private AffineTransform tx;
	
	int dir = 0; 					
	int width, height;              
	int x, y;						
	int vx, vy;						
	double scaleWidth = 1;		
	double scaleHeight = 1;
	
	public redCar() {
		forward = getImage("/imgs"+"Red Car.png");
		width = 100;
		height = 100;
		
		x = -width;
		y = 300;
		
		vx = 5;
		vy = 0;
		
		width = 114;
		height = 114;
		
		x = 1200/2-width/2;
		y = 600;
		
		tx = AffineTransform.getTranslateInstance(0,0);
		
		init(x,y);
	}
	
	public redCar(int x, int y) {
		this();
		
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		if (x > 1250) {
			x = -150;
		}
		init(x,y);
		
		g2.drawImage(forward,  tx,  null);
		
		//draw hit box based on x, y, width, height
		//for collision detection
		if(Frame.debugging) {
			//draw hitbox only debugging
			g.setColor(Color.blue);
			g.drawRect(x, y, width, height);
		}
	}
	
	

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Sprite.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
