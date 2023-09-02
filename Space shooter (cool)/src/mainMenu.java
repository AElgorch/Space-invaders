import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainMenu extends JFrame{
	
	JButton start = new JButton("Start"); 
	JButton highscores = new JButton("Highscores"); 
	JButton exit = new JButton("Exit"); 
	GridLayout buttonLayout = new GridLayout(3, 1);
	JPanel centerButtons = new JPanel(); 
	
	mainMenu()
	{
		this.setTitle("Space Shooter");
		this.setSize(500, 500);
		this.setContentPane(new JLabel(new ImageIcon("SpaceBackground.jpg")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		addingButtons(); 
	}
	
	public void addingButtons()
	{
		centerButtons.setBounds(150, 150, 200, 200);
		//Customizing buttons here
		start.setBackground(new Color(193, 204, 219));
		highscores.setBackground(new Color(193, 204, 219)); 
		exit.setBackground(new Color(193, 204, 219));
		start.setFont(new Font("Georgia", Font.ITALIC, 30));
		highscores.setFont(new Font("Georgia", Font.ITALIC, 30));
		exit.setFont(new Font("Georgia", Font.ITALIC, 30));
		
		//Assigning buttons here
		buttonLayout.setHgap(10);
		buttonLayout.setVgap(10);
		centerButtons.setLayout(buttonLayout);
		centerButtons.setBackground(new Color(4, 4, 92));
		centerButtons.add(start); 
		centerButtons.add(highscores); 
		centerButtons.add(exit);
		this.add(centerButtons);

		//Adding action listeners here
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//gameFrame gFrame = new gameFrame();
				RulesAndControls rulesAndControls = new RulesAndControls(); 
				
			}
		});
		highscores.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//gameFrame gFrame = new gameFrame();
				HighScore highscores = new HighScore();				
			}
		});
		

		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose(); 
				
			}
		});
	}


}
