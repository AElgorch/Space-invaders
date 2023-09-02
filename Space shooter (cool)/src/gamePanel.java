import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class gamePanel extends JPanel
		implements ActionListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	Timer t = new Timer(10, this);

	// SCREEN AND UI VARIABLES
	Image backgroundImage = new ImageIcon("SpaceBackgroundGame.jpg").getImage();
	Image backgroundImage2 = new ImageIcon("SpaceBackgroundGame2.jpg").getImage();
	Image backgroundImage3 = new ImageIcon("SpaceBackgroundGame3.jpg").getImage();
	static final int SCREEN_WIDTH = 1280;
	static final int SCREEN_HEIGHT = 720;
	public static int totalScore = 0;
	public static int levelNum = 1;
	public static int totalHighscore = 0;
	public static long start; 
	public static long end; 
	public static long timeElapsed; 
	Random random = new Random();

	// Adding the spaceship DONT FORGET TO GET THE COORDINATES
	static Spaceship ship = new Spaceship();
	BufferedImage spaceshipImage;
	public static int shipX = ship.getXLocation();
	public static int shipY = ship.getYLocation();
	public static int shipXSpeed = ship.getXSpeed();
	public static int shipYSpeed = ship.getYSpeed();
	public static int shipWidth = ship.getWidth();
	public static int shipHeight = ship.getHeight();

	// Variables to rotate the ship
	public static double shipRotation = ship.getAngle();
	public static double rotationX = shipX + shipWidth / 2;
	public static double rotationY = shipY - shipHeight / 2;
	AffineTransform at;

	// Adding the UFOs
	static UFO ufo = new UFO();
	Image ufoImage = ufo.getImage1();
	Image ufoImage2 = ufo.getImage2();
	Image ufoImage3 = ufo.getImage3();
	public static int ufoX = ufo.getXLocation();
	public static int ufoY = ufo.getYLocation();
	public static int ufoXSpeed = ufo.getXSpeed();
	public static int ufoYSpeed = ufo.getYSpeed();
	public static int ufoWidth = ufo.getWidth();
	public static int ufoHeight = ufo.getHeight();
	public static boolean ufoHit = false;
	public static int ufoCount = 10;

	// Adding the bullet here
	Bullet bullet = new Bullet(shipX, shipY, 10, 0, 8);
	public static boolean bulletFired = false;
	
	//Variables for Super Powers 
	Image powerUp = new ImageIcon("PowerUp.png").getImage(); 
	Image powerDown = new ImageIcon("PowerDown.png").getImage(); 
	public static int powerYSpeed = 2; 
	public static int powerY; 
	public static int powerX; 
	public static double chance; 
	public static boolean morePower = false; 
	public static boolean lessPower = false; 
	public static int powerUpWidth = 50;  
	public static int powerUpHeight = 50; 
	public static int powerDownWidth = 50;
	public static int powerDownHeight = 50; 
	public static boolean collected = false; 
	public static boolean powerAvailable = false; 

	// Adding texts to the screen;
	JLabel reload = new JLabel("Reloading");
	JLabel angle = new JLabel("Angle: ");
	JLabel shipAngle = new JLabel(Double.toString(shipRotation));
	JLabel location = new JLabel("Ship Location: ");
	JLabel shipLocation = new JLabel(Double.toString(shipX));
	JLabel scoreText = new JLabel("Score: ");
	JLabel highscoreText = new JLabel("Your highscore this game is: ");
	JLabel highscore = new JLabel(String.valueOf(totalHighscore));
	JLabel score = new JLabel(String.valueOf(totalScore));
	JLabel ufoRemaining = new JLabel("Remaining UFOs: ");
	JLabel ufoTotal = new JLabel(String.valueOf(ufoCount));
	JLabel level = new JLabel("Level: ");
	JLabel levelNumber = new JLabel(String.valueOf(levelNum));
	JLabel gameOver = new JLabel("GAME OVER!");
	JLabel timeText = new JLabel("Time: "); 
	JLabel time = new JLabel(Long.toString(timeElapsed) + " seconds"); 



	

	gamePanel() {
		t.start();
		//Start the time here 
		start = System.nanoTime()/1000000000; 
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		this.setLayout(null);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);

		// Adding texts and info
		addInfo();

	}

	// DRAWING EVERYTHING HERE
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (levelNum == 1) {
			g.drawImage(backgroundImage, 0, 0, null);
		} else if (levelNum == 2) {
			g.drawImage(backgroundImage2, 0, 0, null);
		} else if (levelNum >= 3) {
			g.drawImage(backgroundImage3, 0, 0, null);
		}

		// Only draw the ufo when it has not been hit
		if (ufoHit == false && (ufoX + ufoWidth < SCREEN_WIDTH + 20 && ufoY + ufoHeight < SCREEN_HEIGHT + 20)) {
			if (levelNum == 1) {
				g.drawImage(ufoImage, ufoX, ufoY, ufoWidth, ufoHeight, null);
			} else if (levelNum == 2) {
				g.drawImage(ufoImage2, ufoX, ufoY, ufoWidth, ufoHeight, null);
			} else if (levelNum >= 3) {
				g.drawImage(ufoImage3, ufoX, ufoY, ufoWidth, ufoHeight, null);
			}

		} else if (ufoHit == true && ufoCount > 0 && levelNum <= 3) {

			// remove ufo before
			ufoHit = false;
			ufoX = random.nextInt(960 - 320) + 320;
			ufoY = random.nextInt(100);

			if (ufoX > 640) {
				if (levelNum == 1) {
					ufoXSpeed = random.nextInt(-1 + 3) - 3;
					ufoYSpeed = random.nextInt(3 - 1) + 1;
				} else if (levelNum == 2) {
					ufoXSpeed = random.nextInt(-3 + 5) - 5;
					ufoYSpeed = random.nextInt(5 - 3) + 3;
				} else if (levelNum == 3) {
					ufoXSpeed = random.nextInt(-5 + 7) - 7;
					ufoYSpeed = random.nextInt(7 - 5) + 5;
				}
			} else if (ufoX <= 640) {
				if (levelNum == 1) {
					ufoXSpeed = random.nextInt(3 - 1) + 1;
					ufoYSpeed = random.nextInt(3 - 1) + 1;
				} else if (levelNum == 2) {
					ufoXSpeed = random.nextInt(5 - 3) + 3;
					ufoYSpeed = random.nextInt(5 - 3) + 3;
				} else if (levelNum == 3) {
					ufoXSpeed = random.nextInt(7 - 5) + 5;
					ufoYSpeed = random.nextInt(7 - 5) + 5;
				}
			}

		}
		
		 

		at = AffineTransform.getTranslateInstance(shipX, shipY);
		spaceshipImage = ship.LoadImage("Spaceship.png");
		at.rotate(Math.toRadians(shipRotation), spaceshipImage.getWidth() / 2, spaceshipImage.getHeight() / 2);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(spaceshipImage, at, null);

		// Adding the bullet here
		g.setColor(Color.red);
		if (bulletFired == true) {
			g.fillOval(bullet.bulletX, bullet.bulletY, bullet.bulletRadius, bullet.bulletRadius);
		}
		
		//Adding the power ups
		if(powerAvailable == true)
		{
			if(morePower == true && lessPower == false)
			{
				g.drawImage(powerUp, powerX, powerY, powerUpWidth, powerUpHeight, null);
			}
			else if(morePower == false && lessPower == true)
			{
				g.drawImage(powerDown, powerX, powerY, powerDownWidth, powerDownHeight, null); 
			}
		}
		

	}

	// ADD CONTROLS HERE
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// DONT FORGET TO ADD MORE UFOS BASED ON THE LEVEL
		moveUfos();
		moveBullet();
		addPowers(); 

		// ADDING INFO TO THE SCREEN
		displayInfo();

		shipLocation.setText(Double.toString(shipX));
		ufoTotal.setText(String.valueOf(ufoCount));
		
		end = System.nanoTime()/1000000000;
		timeElapsed = end - start; 
		time.setText(Long.toString(timeElapsed) + " seconds");
		
		if(levelNum > 3)
		{
			t.stop(); 
		}
		
		repaint();

	}

	// IMPORTANT FUNCTIONS ARE HERE
	public void moveBullet() {
		if (bulletFired == true) {
			// System.out.println(shipRotation);
			if (levelNum == 1) {
				bullet.bulletSpeed = 10;
				if(morePower == true)
				{
					bullet.bulletSpeed += 2; 
				}
				else if(lessPower == true)
				{
					bullet.bulletSpeed -= 2; 
				}
			} else if (levelNum == 2) {
				bullet.bulletSpeed = 10	;
				if(morePower == true)
				{
					bullet.bulletSpeed += 2; 
				}
				else if(lessPower == true)
				{
					bullet.bulletSpeed -= 2; 
				}
			} else if (levelNum >= 3) {
				bullet.bulletSpeed = 15;
				if(morePower == true)
				{
					bullet.bulletSpeed += 2; 
				}
				else if(lessPower == true)
				{
					bullet.bulletSpeed -= 2; 
				}
			}
			
			System.out.println(bullet.bulletSpeed);

			bullet.bulletXSpeed = (int) (bullet.bulletSpeed * Math.cos(Math.toRadians(shipRotation - 90)));
			bullet.bulletYSpeed = (int) (bullet.bulletSpeed * Math.sin(Math.toRadians(shipRotation - 90)));

			bullet.bulletX += bullet.bulletXSpeed;
			bullet.bulletY += bullet.bulletYSpeed;

			this.add(reload);
			if (bullet.bulletY < 0 || bullet.bulletY > SCREEN_HEIGHT) {
				bulletFired = false;
				this.remove(reload);
			} else if (bullet.bulletX < 0 || bullet.bulletX > SCREEN_WIDTH) {
				bulletFired = false;
				this.remove(reload);
			} else if (bullet.bulletX + bullet.bulletRadius < ufoX + ufoImage.getWidth(null) / 2
					&& bullet.bulletX > ufoX && bullet.bulletY < ufoY + ufoImage.getHeight(null) / 4
					&& bullet.bulletY > ufoY) // Collide the bullet with the UFO here
			{
				bulletFired = false;
				if (ufoCount > 0) {
					if (levelNum == 1) {
						totalScore += 100;
						//Calculate the power up probability here
						chance = random.nextDouble(); 
						if(chance >= 0.1 && chance <= 0.6 && ufoY < SCREEN_HEIGHT/2 - ufoHeight)
						{
							//System.out.println("Power Up");
							powerX = ufoX + ufoImage.getWidth(null)/2 - powerUp.getWidth(null)/2; 
							powerY = ufoY; 
							morePower = true; 
							lessPower = false; 
							powerAvailable = true; 
						}
						else if(chance >= 0.8 && chance <= 0.9 && ufoY < SCREEN_HEIGHT/2 - ufoHeight)
						{
							//System.out.println("Power Down");
							powerX = ufoX + ufoImage2.getWidth(null)/2 - powerDown.getWidth(null)/2; 
							powerY = ufoY; 
							lessPower = true; 
							morePower = false; 
							powerAvailable = true; 
						}
					} else if (levelNum == 2) {
						totalScore += 500;
						//Calculate the power up and power down probability here
						chance = random.nextDouble(); 
						if(chance >= 0.1 && chance <= 0.3 && ufoY < SCREEN_HEIGHT/2 - ufoHeight)
						{
							//System.out.println("Power Up");
							powerX = ufoX + ufoImage3.getWidth(null)/2 - powerUp.getWidth(null)/2; 
							powerY = ufoY;
							morePower = true; 
							lessPower = false; 
							powerAvailable = true; 
						}
						else if(chance >= 0.5 && chance <= 0.9 && ufoY < SCREEN_HEIGHT/2 - ufoHeight)
						{
							//System.out.println("Power Down");
							powerX = ufoX + ufoImage.getWidth(null)/2 - powerDown.getWidth(null)/2; 
							powerY = ufoY;
							morePower = false; 
							lessPower = true; 
							powerAvailable = true; 
						}
					} else if (levelNum >= 3) {
						totalScore += 1000;
						//Calculate the power up and power down probability here
						chance = random.nextDouble(); 
						if(chance >= 0.1 && chance <= 0.2 && ufoY < SCREEN_HEIGHT/2 - ufoHeight)
						{
							//System.out.println("Power Up");
							powerX = ufoX + ufoImage.getWidth(null)/2 - powerUp.getWidth(null)/2; 
							powerY = ufoY;
							morePower = true; 
							lessPower = false; 
							powerAvailable = true; 
						}
						else if(chance >= 0.3 && chance <= 0.9 && ufoY < SCREEN_HEIGHT/2 - ufoHeight)
						{
							//System.out.println("Power Down");
							powerX = ufoX + ufoImage.getWidth(null)/2 - powerDown.getWidth(null)/2; 
							powerY = ufoY;
							lessPower = true; 
							morePower = false; 
							powerAvailable = true; 
						}
					}

					score.setText(String.valueOf(totalScore));
					totalHighscore = totalScore;
					highscore.setText(String.valueOf(totalHighscore));

					this.remove(bullet);
					this.remove(reload);
					ufoHit = true;
					ufoCount--;

					if (ufoCount == 0) {
						levelNum++;
						levelNumber.setText(String.valueOf(levelNum));
						ufoCount = 11;
					}
					


				}

			}

		} else {
			bullet.bulletY = shipY;
		}
	}
	
	public void addPowers()
	{
		if(morePower == true && lessPower == false)
		{
			powerY += powerYSpeed; 
			if(powerY > SCREEN_HEIGHT + 10)
			{
				powerAvailable = false; 
			}
			
			if(powerY > shipY && powerY < shipY + spaceshipImage.getHeight() && powerX > shipX && powerX < shipX + spaceshipImage.getWidth())
			{
				powerAvailable = false; 
				// ADD THE POWER EFFECTS HERE 
				bullet.bulletSpeed *= 2; 
				
				
			}
		}
		else if(lessPower == true && morePower == false)
		{
			powerY += powerYSpeed; 
			if(powerY > SCREEN_HEIGHT + 10)
			{
				powerAvailable = false; 
				
			}
			
			if(powerY > shipY && powerY < shipY + spaceshipImage.getHeight() && powerX > shipX && powerX < shipX + spaceshipImage.getWidth())
			{
				powerAvailable = false; 
				 
			}
		}
	
	}

	public void moveUfos() {
		if (ufoHit == false) {
			ufoX += ufoXSpeed;
			ufoY += ufoYSpeed;
			if (ufoX >= SCREEN_WIDTH + 20) {
				ufoCount--;
				if (ufoCount > 0) {
					ufoX = random.nextInt(960 - 320) + 320;
					ufoY = random.nextInt(100);

					// Assign a random UFO speed based on the level
					if (ufoX > 640) {
						if (levelNum == 1) {
							ufoXSpeed = random.nextInt(-1 + 3) - 3;
						} else if (levelNum == 2) {
							ufoXSpeed = random.nextInt(-3 + 5) - 5;
						} else if (levelNum == 3) {
							ufoXSpeed = random.nextInt(-5 + 7) - 7;
						}

					} else if (ufoX > 0 && ufoX <= 640) {
						if (levelNum == 1) {
							ufoXSpeed = random.nextInt(3 - 1) + 1;
						} else if (levelNum == 2) {
							ufoXSpeed = random.nextInt(5 - 3) + 3;
						} else if (levelNum == 3) {
							ufoXSpeed = random.nextInt(7 - 5) + 5;
						} else if (levelNum > 3) {
							ufoXSpeed = 0;
							t.stop(); 
							
						}

					}

					// TRY CHANGING THE Y SPEED AS WELL TO SEE HOW IT AFFECTS THE SPEED OF THE UFO
					// BASED ON THE LEVELS
					if (levelNum == 1) {
						ufoYSpeed = random.nextInt(3 - 1) + 1;
					} else if (levelNum == 2) {
						ufoYSpeed = random.nextInt(5 - 3) + 3;
					} else if (levelNum == 3) {
						ufoYSpeed = random.nextInt(7 - 5) + 5;
					} else if (levelNum > 3) {
						ufoYSpeed = 0;
						t.stop(); 
						
					}

				} else if (ufoCount == 0) {
					levelNum++;
					levelNumber.setText(String.valueOf(levelNum));
					ufoCount = 11;
				}

			} else if (ufoX <= -20) {
				ufoCount--;
				if (ufoCount > 0) {
					ufoX = random.nextInt(960 - 320) + 320;
					ufoY = random.nextInt(100);

					// Assign a random ufo speed based on the level
					if (ufoX > 640) {
						if (levelNum == 1) {
							ufoXSpeed = random.nextInt(-1 + 3) - 3;
						} else if (levelNum == 2) {
							ufoXSpeed = random.nextInt(-3 + 5) - 5;
						} else if (levelNum == 3) {
							ufoXSpeed = random.nextInt(-5 + 7) - 7;
						} else if (levelNum > 3) {
							ufoXSpeed = 0;
							t.stop();
							
						}

					} else if (ufoX > 0 && ufoX <= 640) {
						if (levelNum == 1) {
							ufoXSpeed = random.nextInt(3 - 1) + 1;
						} else if (levelNum == 2) {
							ufoXSpeed = random.nextInt(5 - 3) + 3;
						} else if (levelNum == 3) {
							ufoXSpeed = random.nextInt(7 - 5) + 5;
						} else if (levelNum > 3) {
							ufoXSpeed = 0;
							t.stop(); 
							
						}

					}

					if (levelNum == 1) {
						ufoYSpeed = random.nextInt(3 - 1) + 1;
					} else if (levelNum == 2) {
						ufoYSpeed = random.nextInt(5 - 3) + 3;
					} else if (levelNum == 3) {
						ufoYSpeed = random.nextInt(7 - 5) + 5;
					} else if (levelNum > 3) {
						ufoYSpeed = 0;
						t.stop(); 
						
					}
				} else if (ufoCount == 0) {
					levelNum++;
					levelNumber.setText(String.valueOf(levelNum));
					ufoCount = 11;
				}
			} else if (ufoY >= SCREEN_HEIGHT + 20) {
				ufoCount--;
				if (ufoCount > 0) {
					ufoX = random.nextInt(960 - 320) + 320;
					ufoY = random.nextInt(100);

					// Assign a random UFO speed based on the level
					if (ufoX > 640) {
						if (levelNum == 1) {
							ufoXSpeed = random.nextInt(-1 + 3) - 3;
						} else if (levelNum == 2) {
							ufoXSpeed = random.nextInt(-3 + 5) - 5;
						} else if (levelNum == 3) {
							ufoXSpeed = random.nextInt(-5 + 7) - 7;
						} else if (levelNum > 3) {
							ufoXSpeed = 0;
							t.stop(); 
							
						}

					} else if (ufoX > 0 && ufoX <= 640) {
						if (levelNum == 1) {
							ufoXSpeed = random.nextInt(3 - 1) + 1;
						} else if (levelNum == 2) {
							ufoXSpeed = random.nextInt(5 - 3) + 3;
						} else if (levelNum == 3) {
							ufoXSpeed = random.nextInt(7 - 5) + 5;
						} else if (levelNum > 3) {
							ufoXSpeed = 0;
							t.stop(); 
							
						}

					}

					if (levelNum == 1) {
						ufoYSpeed = random.nextInt(3 - 1) + 1;
					} else if (levelNum == 2) {
						ufoYSpeed = random.nextInt(5 - 3) + 3;
					} else if (levelNum == 3) {
						ufoYSpeed = random.nextInt(7 - 5) + 5;
					} else if (levelNum > 3) {
						ufoYSpeed = 0;
						t.stop(); 
						
					}
				} else if (ufoCount == 0) {
					levelNum++;
					levelNumber.setText(String.valueOf(levelNum));
					ufoCount = 11;
				}
			} else if (ufoY + ufoHeight <= -20) {
				ufoCount--;
				if (ufoCount > 0) {
					ufoX = random.nextInt(960 - 320) + 320;
					ufoY = random.nextInt(100);

					// Assign a random UFO speed based on the level
					if (ufoX > 640) {
						if (levelNum == 1) {
							ufoXSpeed = random.nextInt(-1 + 3) - 3;
						} else if (levelNum == 2) {
							ufoXSpeed = random.nextInt(-3 + 5) - 5;
						} else if (levelNum == 3) {
							ufoXSpeed = random.nextInt(-5 + 7) - 7;
						} else if (levelNum > 3) {
							ufoXSpeed = 0;
							t.stop(); 
							
						}

					} else if (ufoX > 0 && ufoX <= 640) {
						if (levelNum == 1) {
							ufoXSpeed = random.nextInt(3 - 1) + 1;
						} else if (levelNum == 2) {
							ufoXSpeed = random.nextInt(5 - 3) + 3;
						} else if (levelNum == 3) {
							ufoXSpeed = random.nextInt(7 - 5) + 5;
						} else if (levelNum > 3) {
							ufoXSpeed = 0;
							t.stop(); 
							
						}

					}

					if (levelNum == 1) {
						ufoYSpeed = random.nextInt(3 - 1) + 1;
					} else if (levelNum == 2) {
						ufoYSpeed = random.nextInt(5 - 3) + 3;
					} else if (levelNum == 3) {
						ufoYSpeed = random.nextInt(7 - 5) + 5;
					} else if (levelNum > 3) {
						ufoYSpeed = 0;
						t.stop(); 
						
					}
				} else if (ufoCount == 0) {
					levelNum++;
					levelNumber.setText(String.valueOf(levelNum));
					ufoCount = 11;
					
				}
			}
		} else {
			ufoXSpeed = 0;
			ufoYSpeed = 0;
		}

	}

	public void addInfo() {
		// RELOAD
		reload.setBounds(1100, 600, 150, 40);
		reload.setForeground(Color.white);
		reload.setFont(new Font("Serif", Font.BOLD, 24));

		// ANGLE
		angle.setBounds(20, 300, 90, 40);
		angle.setForeground(Color.white);
		angle.setFont(new Font("Serif", Font.BOLD, 18));

		// SHIP ANGLE
		shipAngle.setBounds(100, 300, 150, 40);
		shipAngle.setForeground(Color.white);
		shipAngle.setFont(new Font("Serif", Font.BOLD, 18));

		// Location
		location.setBounds(20, 330, 150, 40);
		location.setForeground(Color.white);
		location.setFont(new Font("Serif", Font.BOLD, 18));

		// Ship Location
		shipLocation.setBounds(150, 330, 150, 40);
		shipLocation.setForeground(Color.white);
		shipLocation.setFont(new Font("Serif", Font.BOLD, 18));

		if (levelNum <= 3) {
			// Score text
			scoreText.setBounds(20, 20, 100, 40);
			scoreText.setForeground(Color.white);
			scoreText.setFont(new Font("Serif", Font.BOLD, 24));

			// Score
			score.setBounds(120, 20, 100, 40);
			score.setForeground(Color.white);
			score.setFont(new Font("Serif", Font.BOLD, 24));
			
			//Time Text
			timeText.setBounds(20, 50, 100, 40);
			timeText.setForeground(Color.white);
			timeText.setFont(new Font("Serif", Font.BOLD, 24));
			
			time.setBounds(120, 50, 150, 40);
			time.setForeground(Color.white);
			time.setFont(new Font("Serif", Font.BOLD, 24));

		} else if(levelNum > 3) {
			// Score text
			scoreText.setBounds(350, 100, 100, 40);
			scoreText.setForeground(Color.white);
			scoreText.setFont(new Font("Serif", Font.BOLD, 24));

			// Score
			score.setBounds(250, 100, 50, 40);
			score.setForeground(Color.white);
			score.setFont(new Font("Serif", Font.BOLD, 24));

			

		}

		// UFO Remaining text
		ufoRemaining.setBounds(20, 230, 150, 40);
		ufoRemaining.setForeground(Color.white);
		ufoRemaining.setFont(new Font("Serif", Font.BOLD, 18));

		// UFO COUNT
		ufoTotal.setBounds(170, 230, 100, 40);
		ufoTotal.setForeground(Color.white);
		ufoTotal.setFont(new Font("Serif", Font.BOLD, 18));

		// Level
		level.setBounds(1100, 20, 100, 40);
		level.setForeground(Color.white);
		level.setFont(new Font("Serif", Font.BOLD, 24));

		// Level Number
		levelNumber.setBounds(1200, 20, 100, 40);
		levelNumber.setForeground(Color.white);
		levelNumber.setFont(new Font("Serif", Font.BOLD, 24));

		gameOver.setBounds(240, 100, 800, 500);
		gameOver.setForeground(Color.white);
		gameOver.setFont(new Font("Serif", Font.BOLD, 120));

		//Highscore text
		highscoreText.setBounds(25, 100, 500, 40);
		highscoreText.setForeground(Color.white);
		highscoreText.setFont(new Font("Serif", Font.BOLD, 24));

		// Highscore
		highscore.setBounds(350, 100, 500, 40);
		highscore.setForeground(Color.white);
		highscore.setFont(new Font("Serif", Font.BOLD, 24));

	}

	public void displayInfo() {
		if (levelNum <= 3) {
			this.add(angle);
			this.add(shipAngle);
			this.add(location);
			this.add(shipLocation);
			this.add(scoreText);
			this.add(score);
			this.add(ufoRemaining);
			this.add(ufoTotal);
			this.add(level);
			this.add(levelNumber);
			this.add(timeText); 
			this.add(time); 
		} else if (levelNum > 3) {
			this.add(highscoreText);
			this.add(highscore);
			this.remove(angle);
			this.remove(shipAngle);
			this.remove(location);
			this.remove(shipLocation);
			this.remove(ufoRemaining);
			this.remove(ufoTotal);
			this.remove(level);
			this.remove(levelNumber);
			this.add(gameOver);
			
		}

	}

	// CONTROLS ARE HERE
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
		if (e.getKeyCode() == KeyEvent.VK_D) {
			shipX += shipXSpeed;
			if (shipX > SCREEN_WIDTH) {
				shipX = -spaceshipImage.getWidth();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			shipX -= shipXSpeed;
			if (shipX < 0 - spaceshipImage.getWidth()) {
				shipX = SCREEN_WIDTH;
			}
		}

		// Firing the bullet
		if (e.getKeyCode() == KeyEvent.VK_SPACE && bulletFired != true) {
			bulletFired = true;
			double shipCenterX = shipX + spaceshipImage.getWidth() / 2;
			double shipCenterY = shipY + spaceshipImage.getHeight() / 2;
			bullet.bulletX = bullet.setXLocation((int) (shipCenterX
					+ (spaceshipImage.getHeight() / 2) * Math.cos(Math.toRadians(shipRotation - 90))));
			bullet.bulletY = bullet.setYLocation((int) (shipCenterY
					+ (spaceshipImage.getHeight() / 2) * Math.sin(Math.toRadians(shipRotation - 90))));

		}
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

		int rotation = e.getWheelRotation();
		if (rotation > 0 && bulletFired != true) {
			if (levelNum == 1) {
				shipRotation -= 5;
			} else if (levelNum == 2) {
				shipRotation -= 7;
			} else if (levelNum >= 3) {
				shipRotation -= 9;
			}

			if (shipRotation <= -360 && bulletFired != true) {
				shipRotation = 0;

			}
		} else if (rotation < 0 && bulletFired != true) {
			if (levelNum == 1) {
				shipRotation += 5;
			} else if (levelNum == 2) {
				shipRotation += 7;
			} else if (levelNum >= 3) {
				shipRotation += 9;
			}

			if (shipRotation >= 360 && bulletFired != true) {
				shipRotation = 0;

			}
		}

		shipAngle.setText(Double.toString(shipRotation));

	}

}
