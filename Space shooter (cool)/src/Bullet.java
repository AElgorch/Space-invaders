import java.awt.Graphics;

import javax.swing.JPanel;

public class Bullet extends JPanel{
	public int bulletX = 500;
	public int bulletY = 500; 
	public double bulletXSpeed = 0; 
	public double bulletYSpeed = 0;
	public double bulletSpeed = 5; 
	public int bulletRadius = 10;
	public double angle = 0;
	public int bulletAngle = (int) Math.toRadians(angle);
	
	public Bullet(int x, int y, int radius, int xVelocity, int yVelocity)
	{
		bulletX = x; 
		bulletY = y; 
		bulletRadius = radius; 
		bulletXSpeed = xVelocity; 
		bulletYSpeed = yVelocity; 
	}
	
	public int getXLocation()
	{
		return bulletX; 
	}
	
	public int setXLocation(int newX)
	{
		bulletX = newX; 
		return bulletX;
	}
	
	public int getYLocation()
	{
		return bulletY; 
	}
	
	public int setYLocation(int newY)
	{
		bulletY = newY; 
		return bulletY;
	}
	
	public double getXSpeed()
	{
		return bulletXSpeed; 
	}
	
	public double getYSpeed()
	{
		return bulletYSpeed; 
	}
	
	public int getRadius()
	{
		return bulletRadius; 
	}

}
