import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Log{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	//attributes of this class
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;              //collision detection (hit box)
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1;		//change to scale image
	double scaleHeight = 1; 		//change to scale image

	public Log() {
		//load the main image (front or forward view)
		forward 	= getImage("/imgs/"+"logs.png"); //load the image for the log
		//alter these
		width = 100;
		height = 100;
		x = -width;
		y = 400;
		
		//if your movement will not be "hooping" base
		vx = 5;
		vy = 0;
		
		//width and height for hit box
		width = 114;
		height = 114;
	
		
		//used for replacement on the JFrame
		x = 1200/2-width/2;
		y = 600;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	//2nd constructor - allow setting x and y during construction
	public Log(int x, int y) {
		
		//call the default constructor for all the normal stuff
		this(); //invokes default constructor
		//do the specific task for THIS constructor
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//update xx and y if using vx, vy variables
		x+=vx;
		y+=vy;	
		
		//for infinite scrolling - teleport to the other side
		//once it leaves the other side
		if (x > 1250) {
			x = -150;
		}
		
		
		
		
		init(x,y);
		
		g2.drawImage(forward,  tx,  null);
		
		//draw hit box based on x, y, width, height
		//for collision detection
		if(Frame.debugging) {
			//draw hitbox only if debugging
			g.setColor(Color.green);
			g.drawRect(x, y, width, height);
		}
		
		/*switch(dir) {
		case 0:
			g2.drawImage(forward, tx, null);
			break;
		case 1:
			g2.drawImage(backward, tx, null);

			break;
		case 2:
			g2.drawImage(left, tx, null);

			break;
		case 3:
			g2.drawImage(right, tx, null);
			break;
		}*/

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
