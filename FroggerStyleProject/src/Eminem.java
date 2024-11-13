	import java.awt.Color;
import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.geom.AffineTransform;
	import java.net.URL;
public class Eminem{
		private Image forward, backward, left, right; 	
		private AffineTransform tx;
		
		int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
		int width, height;
		int x, y;						//position of the object
		int vx, vy;						//movement variables
		double scaleWidth = 0.1;		//change to scale image
		double scaleHeight = 0.1; 		//change to scale image

		public Eminem() {
			forward 	= getImage("/imgs/"+"Eiminem.png"); //load the image for Tree
	

			//alter these
			width = 100;
			height = 100;
			x = -width;
			y = 300;
			//if your movement will not be "hoping" base
			vx = 5;
			vy = 0;
			
			//width & height for hit box
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
		public Eminem(int x, int y) {
			//call the default constructor for all the normal stuff
			this();
			//do the specific task for THIS constructor
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



