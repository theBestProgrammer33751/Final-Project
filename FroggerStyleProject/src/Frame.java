import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//for ay debugging code we add
	public static boolean debugging = false;
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;

	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	//Pumpkin pumpkin = new Pumpkin();
	//Pumpkin pumpkin2 = new Pumpkin(100, 200);
	//Eminem eminem = new Eminem();
	//Eminem eminem2 = new Eminem(200, 200);
	//a row of Pumpkin Objects!
	//Pumpkin[] row1 = new Pumpkin[20];
	//Eminem[] row2 = new Eminem[20];
	Bat bat = new Bat(600,1175);
	//Ghost[] row3 = new Ghost[20];
	Bluecar[] row1 = new Bluecar[20];
	Bluecar[] row2 = new Bluecar[20];
	Redcar[] row3 = new Redcar[20];
	Train[] row4 = new Train[20];
	ArrayList<Log> logsList = new ArrayList<Log>(20);
	Background2 background = new Background2();
	//frame width/height
	int width = 1200;
	int height = 1200;	
	Random random = new Random();
	

	public void paint(Graphics g) {
		
		super.paintComponent(g);
		background.paint(g);
		//pumpkin.paint(g);
		//pumpkin2.paint(g);
		//eminem.paint(g);
		//eminem2.paint(g);
		/*for(Ghost ghosts : row3) {
			ghosts.paint(g);
		}*/
		
		
		//have the row1 objects paint on the screen!
		//for each obj in row1
		for(Bluecar obj : row1) {
			obj.paint(g);
		}

		for(Bluecar obj : row2) {
			obj.paint(g);
		}

		for(Redcar obj : row3) {
			obj.paint(g);
		}
		for(Train obj : row4) {
			obj.paint(g);
		}
		for(Log obj : logsList) {
			obj.paint(g);
		}
		bat.paint(g);
		
		
		/*for(Eminem obj : row2) {
			obj.paint(g);
		}*/
		/*for (Log obj : logsList) {
			obj.paint(g);
		}*/

	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		//backgroundMusic.play();
		
		/*
		 * Setup ay 1D array here! - create the objects that go in them ;)
		 * Traverse the array
		 */

		// These arrays are what set up the objects to slide across the screen
		for(int i = 0; i < row1.length; i++) {
			row1[i] = new Bluecar(i*300, 1100);
		}

		for(int i = 0; i < row2.length; i++) {
			row2[i] = new Bluecar(i*300, 1000);
		}
 
		for(int i = 0; i < row3.length; i++) {
			row3[i] = new Redcar(i*300, 900);
		}

		for(int i = 0; i < row4.length; i++) {
			row4[i] = new Train(i*300, 800);
		}

		for(int i = 0; i < logsList.size(); i++) {
			logsList.add(new Log(i*300, 700));
		}

		
	
		/*for(int i = 0; i < row2.length; i++) {
			row2[i] = new Eminem(i*300, 300);
		}
		for(int i = 0; i < row3.length; i++) {
			row3[i] = new Ghost(i*300, 500);
		}
		for (int i = 0; i < logsList.size(); i++) {
			logsList.add(new Log(i*300, 700));
		}*/

		
		
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	//User control for the character. The bat will move depending on the up or down button pressed
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			bat.vy = -3;
		}

		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			bat.vy = 3;
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//Each of these check the collision for each object in the game
	public void checkCollisionOne() {
			for(int i = 0; i < row1.length; i++) {
					if(row1[i].getBounds().intersects(bat.getBounds())) {
						System.out.println("Game Over!");
						bat.x = 600;
						bat.y = 1175;
					}
			
			}
	}
	public void checkCollisionTwo() {
			for(int i = 0; i < row2.length; i++) {
				if(row2[i].getBounds().intersects(bat.getBounds())) {
					System.out.println("Game Over!");
					bat.x = 600;
					bat.y = 1175;
				}
			}
			}
	public void checkCollisonThree() {
		for(int i = 0; i < row3.length; i++) {
			if(row3[i].getBounds().intersects(bat.getBounds())) {
				System.out.println("Game Over!");
				bat.x = 600;
				bat.y = 1175;
			}
		}
	}
	public void checkCollisionFour() {
		for(int i = 0; i < row4.length; i++) {
			if(row4[i].getBounds().intersects(bat.getBounds())) {
				System.out.println("Game Over!");
				bat.x = 600;
				bat.y = 1175;
			}
		}
	}
	// Rideable Log implemented here
	public void checkCollisionFive() {
		for(int i = 0; i < logsList.size(); i++) {
			if(logsList.get(i).getBounds().intersects(bat.getBounds())) {
				bat.vx = logsList.get(i).vx;
			}
		}
	}
}
	
	
	





























