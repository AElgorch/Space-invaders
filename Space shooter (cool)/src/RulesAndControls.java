import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RulesAndControls extends JFrame 
{

	JPanel rulesAndControlsPanel = new JPanel();

	JLabel controls = new JLabel("Controls");
	JLabel rules = new JLabel("Rules");
	JLabel adToMove = new JLabel("- A and D to move the Spaceship");
	JLabel spacetoShoot = new JLabel("- Space to shoot bullets");
	JLabel scrollToRotate = new JLabel("- Use mouse scrollwheel to rotate the Spaceship");
	JLabel threeLevels = new JLabel("- There will be 3 levels");
	JLabel gainPoints = new JLabel("- Defeat UFOs to gain points, 10 UFOs in each level");
	JLabel fasterUFOs = new JLabel("- The faster the UFOs the more points you get");
	JLabel cannotShoot = new JLabel("- Cannot shoot or rotate the Spaceship while it is reloading"); 

	JButton begin = new JButton("Begin");

	RulesAndControls() {
		this.setSize(750, 400);
		this.setTitle("Rules and Controls");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		rulesAndControlsPanel.setLayout(null);
		this.add(rulesAndControlsPanel);

		// CONTROLS Heading
		controls.setBounds(10, 10, 150, 50);
		controls.setForeground(Color.black);
		controls.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 30));
		rulesAndControlsPanel.add(controls);

		// Controls
		adToMove.setBounds(10, 50, 300, 50);
		adToMove.setForeground(Color.black);
		adToMove.setFont(new Font("Serif", Font.ITALIC, 20));
		rulesAndControlsPanel.add(adToMove);

		spacetoShoot.setBounds(10, 80, 300, 50);
		spacetoShoot.setForeground(Color.black);
		spacetoShoot.setFont(new Font("Serif", Font.ITALIC, 20));
		rulesAndControlsPanel.add(spacetoShoot);

		scrollToRotate.setBounds(10, 110, 400, 50);
		scrollToRotate.setForeground(Color.black);
		scrollToRotate.setFont(new Font("Serif", Font.ITALIC, 20));
		rulesAndControlsPanel.add(scrollToRotate);

		// RULES Heading
		rules.setBounds(10, 150, 200, 50);
		rules.setForeground(Color.black);
		rules.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 30));
		rulesAndControlsPanel.add(rules);

		// Rules
		threeLevels.setBounds(10, 180, 200, 50);
		threeLevels.setForeground(Color.black);
		threeLevels.setFont(new Font("Serif", Font.ITALIC, 20));
		rulesAndControlsPanel.add(threeLevels);

		gainPoints.setBounds(10, 210, 500, 50);
		gainPoints.setForeground(Color.black);
		gainPoints.setFont(new Font("Serif", Font.ITALIC, 20));
		rulesAndControlsPanel.add(gainPoints);

		fasterUFOs.setBounds(10, 240, 400, 50);
		fasterUFOs.setForeground(Color.black);
		fasterUFOs.setFont(new Font("Serif", Font.ITALIC, 20));
		rulesAndControlsPanel.add(fasterUFOs);
		
		cannotShoot.setBounds(10, 270, 500, 50);
		cannotShoot.setForeground(Color.black);
		cannotShoot.setFont(new Font("Serif", Font.ITALIC, 20));
		rulesAndControlsPanel.add(cannotShoot);

		// Button
		begin.setBounds(600, 300, 100, 50);
		begin.setFont(new Font("Serif", Font.ITALIC, 25));
		begin.setBackground(new Color(193, 204, 219));
		rulesAndControlsPanel.add(begin);

		begin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 gameFrame gFrame = new gameFrame();
				

			}
		});
	}
}
