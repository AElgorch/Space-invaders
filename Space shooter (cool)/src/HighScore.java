import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighScore extends JFrame
{
	//DIFFERENCE BETWEEN HighScore && Highscore
	
	JPanel HighscorePanel = new JPanel();
		
	JButton start = new JButton("Start");

	HighScore(){
		this.setSize(750, 400);
		this.setTitle("HighScore");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		HighscorePanel.setLayout(null);
		this.add(HighscorePanel);
		

		// Button
		start.setBounds(600, 300, 100, 50);
		start.setFont(new Font("Serif", Font.ITALIC, 25));
		start.setBackground(new Color(193, 204, 219));
		HighscorePanel.add(start);
	
		start.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameFrame gFrame = new gameFrame();
					
	
			}
		});
	}
}
