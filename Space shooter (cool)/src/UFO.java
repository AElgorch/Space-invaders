import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class UFO extends JPanel {
	public static int ufoX = 100; 
	public static int ufoY = 100; 
	public static int ufoHeight = 100; 
	public static int ufoWidth = 100; 
	public static int ufoXSpeed = 1; 
	public static int ufoYSpeed = 1; 
	Image ufo = new ImageIcon("UFO.png").getImage(); 
	Image ufo2 = new ImageIcon("UFO2.png").getImage(); 
	Image ufo3 = new ImageIcon("UFO3.png").getImage(); 
	
	public Image getImage1()
	{
		return ufo; 
	}
	
	public Image getImage2()
	{
		return ufo2; 
	}
	
	public Image getImage3()
	{
		return ufo3; 
	}
	
	public int getXLocation()
	{
		return ufoX; 
	}
	
	public int getYLocation()
	{
		return ufoY; 
	}
	
	public int getWidth()
	{
		return ufoWidth; 
	}
	
	public int getHeight()
	{
		return ufoHeight; 
	}
	
	public int getXSpeed()
	{
		return ufoXSpeed; 
	}
	
	public int getYSpeed()
	{
		return ufoYSpeed; 
	}
	

}
