
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Spaceship extends JPanel implements MouseMotionListener, KeyListener, MouseListener{

	public static int spaceshipX = 500; // X position of the spaceship
	public static int spaceshipY = 500; // Y position of the spaceship
	public static int spaceshipXSpeed = 40;
	public static int spaceshipYSpeed = 1;
	public static int spaceshipWidth = 100;
	public static int spaceshipHeight = 100;
	public static int spaceshipAngle = 0; 
	
	// Timer t = new Timer(1000, this);
	
	BufferedImage LoadImage(String FileName)
	{
		BufferedImage img = null; 
		try {
			img = ImageIO.read(new File(FileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return img; 
	}

	public int getXLocation() {
		return spaceshipX;
	}

	public int getYLocation() {
		return spaceshipY;
	}

	public int getXSpeed() {
		return spaceshipXSpeed;
	}

	public int getYSpeed() {
		return spaceshipYSpeed;
	}

	public int getWidth() {
		return spaceshipWidth;
	}

	public int getHeight() {
		return spaceshipHeight;
	}
	
	public int getAngle()
	{
		return spaceshipAngle; 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
