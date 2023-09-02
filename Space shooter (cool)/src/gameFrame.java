import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gameFrame extends JFrame{ 
	JPanel gPanel;
	
	//Making the cursor transparent 
	BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	
	gameFrame()
	{	
		this.setSize(1280, 720);
		this.setVisible(true);
		this.setTitle("Game");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes all the windows
		this.setLocationRelativeTo(null);
		this.getContentPane().setCursor(blankCursor);
		
		gPanel = new gamePanel(); 
		this.add(gPanel); 
	
	}

}
