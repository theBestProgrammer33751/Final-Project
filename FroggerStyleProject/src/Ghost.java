import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Ghost {
    private Image forward, backward, left, right;  // Images for different directions
    private AffineTransform tx;

    int dir = 0; // 0-forward, 1-backward, 2-left, 3-right
    int width, height;
    int x, y; // Position of the object
    int vx, vy; // Movement variables
    double scaleWidth = 0.05; // Change to scale image
    double scaleHeight = 0.05; // Change to scale image

    public Ghost() {
        forward = getImage("/imgs/" + "ghost.gif"); // Load forward image

        // Load images for backward, left, and right (add logic here)

        width = 100;
        height = 100;

        x = 1200 / 2 - width / 2;
        y = 600;

        vx = 5;
        vy = 0;

        tx = AffineTransform.getTranslateInstance(0, 0);

        init(x, y); // Initialize location
    }

    public Ghost(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        x += vx;
        y += vy;

        // Reset y when it goes off the bottom of the screen
        if (x > 1200) {
            x = -1150; // Adjust the reset position as needed
        }

        init(x, y);

        if (Frame.debugging) {
            g.setColor(Color.orange);
            g.drawRect(x, y, width, height);
        }

        // Choose and draw the correct image based on direction (add logic here)
        g2.drawImage(forward, tx, null);
    }

    private void init(double x, double y) {
        tx.setToTranslation(x, y);
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