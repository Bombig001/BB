package brickbreaker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import brickbreaker.controller.GameController;
import brickbreaker.level.Level;
import brickbreaker.model.Ball;
import brickbreaker.model.Brick;
import brickbreaker.model.Item;
import brickbreaker.model.Paddle;
import brickbreaker.model.Players;
import brickbreaker.powerup.ExtendedPaddle;
import brickbreaker.powerup.PowerUp;

public class Game extends JComponent implements ActionListener {
	public static boolean singleplayerGameStarted = false;
	public static boolean multiplayerGameStarted = false;
	public static String spieler1;
	public static String spieler2;
	public static int schwierigkeitsgrad;
	private Graphics gfx;
	private int level = 0;
	private Level player1CurrentLevel;
	private Level player2CurrentLevel;
	private ArrayList<Item> player1Entities;
	private ArrayList<Item> player2Entities;
	private ArrayList<PowerUp> powerups;
	private Item player1Ball = new Ball(260, 100, 18, 18,0, Players.PLAYER1);
	private Item player1Paddle = new Paddle(this,spieler1,300, 650, 97, 26, Players.PLAYER1,player1Ball);
	private Item player2Ball = new Ball(260, 100, 18, 18,0, Players.PLAYER2);
	private Item player2Paddle = new Paddle(this,spieler1,300, 650, 97, 26, Players.COMPUTER,player2Ball);
	Image background;
	Image background2;
	Image splitter;
	Image p1winns;
	Image p2winns;
	private boolean gameOver;
	private Menu mainMenu;
	private Random rand;
	private JFrame window;
	private Instant timeStart;
	private Instant timeStop;
	private Duration timePastBetween;
	
	public Game(JFrame window) {
		this.window = window;
//		mainMenu = new Menu(this, true);
//		window.addKeyListener((KeyListener) p1);
//		window.addMouseMotionListener(this);
		mainMenu = new Menu(this);
		background  = new ImageIcon(this.getClass().getResource("/res/images/background/1.jpg")).getImage();
		background2 = new ImageIcon(this.getClass().getResource("/res/images/background/3.jpg")).getImage();
		splitter = new ImageIcon(this.getClass().getResource("/res/images/background/splitter.jpg")).getImage();
		p1winns = new ImageIcon(this.getClass().getResource("/res/images/player1winns.png")).getImage();
		p2winns = new ImageIcon(this.getClass().getResource("/res/images/player2winns.png")).getImage();
		player1CurrentLevel = new Level();
		player2CurrentLevel = new Level();
		player1Entities = new ArrayList<Item>();
		player2Entities = new ArrayList<Item>();
		powerups = new ArrayList<PowerUp>();
		setupSingleplayerMode();
		rand = new Random();
	}
	
	private void gameOverScreen() {
		timeStop = Instant.now();
		timePastBetween = Duration.between(timeStart, timeStop);
		System.out.println(timePastBetween.getSeconds());
			
		if (multiplayerGameStarted) {
			if (((Paddle) player1Paddle).getScore() > ((Paddle) player2Paddle).getScore()) {
				gfx.drawImage(p1winns, GameController.windowWidth/4, 300, null);
			} else if (((Paddle) player1Paddle).getScore() < ((Paddle) player2Paddle).getScore()) {
				gfx.drawImage(p2winns, GameController.windowWidth/4, 300, null);			
			}
			
			if (timePastBetween.getSeconds() >= 5) { 
				setupMultiplayerMode();
				gameOver = false;
			}
		} else {
			gfx.drawImage(p1winns, 35, 300, null);
			if (timePastBetween.getSeconds() >= 5) { 
				setupSingleplayerMode();
				gameOver = false;
			}
		}
	}
	
	private void setupSingleplayerMode() {
		player1CurrentLevel.setCurrentLevel(Math.min(level += 1, player1CurrentLevel.getMaxLevel()));
		player1Entities.clear();
		powerups.clear();
		player1Entities = player1CurrentLevel.getBrickList();
		player1Paddle.setPos(300, 650);
		((Ball) player1Ball).respawn();
		player1Entities.add(player1Ball);
		player1Entities.add(player1Paddle);
	}
	
	private void setupMultiplayerMode() {
		setupSingleplayerMode();
		
		GameController.windowWidth = 1456;
		window.setSize(GameController.windowWidth, GameController.windowHeight);
		window.setLocationRelativeTo(null);
		
		player2CurrentLevel.setCurrentLevel(Math.min(level += 1, player2CurrentLevel.getMaxLevel()));
		player2Entities.clear();
		player2Entities = player2CurrentLevel.getBrickList();
		player2Paddle.setPos(300, 650);
		((Ball) player2Ball).respawn();
		player2Entities.add(player2Ball);
		player2Entities.add(player2Paddle);
		for(Item it : player1Entities) {
			it.getPos().setPosX(it.getPos().getPosX()+GameController.windowWidth/2+8);
			if (it instanceof Ball) {
				it.setVelX(-it.getVelX());
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
		gfx = g;
		if(singleplayerGameStarted) {
			//this.removeAll();
			gfx.drawImage(background, 0, 0, GameController.windowWidth, GameController.windowHeight, null);
			gfx.drawImage(background2,0, 0, GameController.windowWidth, 50,null);
			//gfx.drawImage(splitter,GameController.windowWidth/2-8, 0, 16, GameController.windowHeight,null);
			gfx.setColor(Color.lightGray);
			gfx.setFont(new Font("Times", Font.BOLD, 17));
			gfx.drawString("Punktzahl: "+ ((Paddle) player1Paddle).getScore(), 0, 20);
			gfx.drawString("Spielername: \n"+ ((Paddle) player1Paddle).getName(), 500, 20);
		} else if (multiplayerGameStarted) {
			if (GameController.windowWidth <= 720) {
				setupMultiplayerMode();
			}
			gfx.drawImage(background, 0, 0, GameController.windowWidth/2-8, GameController.windowHeight, null);
			gfx.drawImage(background2,0, 0, GameController.windowWidth/2-8, 50,null);
			gfx.setColor(Color.lightGray);
			gfx.setFont(new Font("Times", Font.BOLD, 17));
			gfx.drawString("Punktzahl: "+ ((Paddle) player2Paddle).getScore(), 0, 20);
			gfx.drawString("Spieler 2: \n"+ ((Paddle) player2Paddle).getName(), 500, 20);
			
			gfx.drawImage(splitter,GameController.windowWidth/2-8, 0, 16, GameController.windowHeight,null);
			
			gfx.drawImage(background, GameController.windowWidth/2+8, 0, GameController.windowWidth/2-8, GameController.windowHeight, null);
			gfx.drawImage(background2,GameController.windowWidth/2+8, 0, GameController.windowWidth/2-8, 50,null);
			gfx.setColor(Color.lightGray);
			gfx.setFont(new Font("Times", Font.BOLD, 17));
			gfx.drawString("Punktzahl: "+ ((Paddle) player1Paddle).getScore(), GameController.windowWidth/2+8, 20);
			gfx.drawString("Spieler 1: \n"+ ((Paddle) player1Paddle).getName(), GameController.windowWidth/2+8 + 500, 20);
			
		}
		gameUpdate();
	}
	
	private void gameUpdate() {
		
		if (singleplayerGameStarted || multiplayerGameStarted) {
			((Paddle) player1Paddle).setName(spieler1);
			((Paddle) player2Paddle).setName(spieler2);
			
			if (multiplayerGameStarted) {
				for(Item it : player2Entities) {
					it.draw(gfx);
				}
			}
			
			for(Item it : player1Entities) {
				it.draw(gfx);
			}
			
			for (PowerUp pu : powerups) {
				pu.draw(gfx);
			}
		}
		
		if (gameOver) {
			if (timeStart == null) {
				timeStart = Instant.now();
			}
			gameOverScreen();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(!gameOver && (singleplayerGameStarted || multiplayerGameStarted)) {
			
			for (int i = 0; i < player1Entities.size(); i++) {
				Item it = player1Entities.get(i);
				if ( it instanceof Brick) {
					if (!((Brick) it).getIsSmashed()) {
						if(((Brick) it).colission(player1Ball)) {
							((Brick) it).dealDamage();
							if (((Brick) it).getIsSmashed()) {
								if(rand.nextInt(2) == 1) {
									PowerUp pwp = new ExtendedPaddle(player1Entities.get(i).getPos().getPosX()+player1Entities.get(i).getPos().getWidth()/2-20, player1Entities.get(i).getPos().getPosY(), 40, 11, player1Paddle);
									powerups.add(pwp);
								}
								player1Entities.remove(i);
								((Paddle) player1Paddle).setScore(((Paddle) player1Paddle).getScore() + 10);
								i--;
							}
						}
					}
				} else {
					it.update();
					it.colission(player1Ball);
				}
			}
			
			if (multiplayerGameStarted) {
				for (int i = 0; i < player2Entities.size(); i++) {
					Item it = player2Entities.get(i);
					if ( it instanceof Brick) {
						if (!((Brick) it).getIsSmashed()) {
							if(((Brick) it).colission(player2Ball)) {
								((Brick) it).dealDamage();
								if (((Brick) it).getIsSmashed()) {
									if(rand.nextInt(2) == 1) {
										PowerUp pwp = new ExtendedPaddle(player2Entities.get(i).getPos().getPosX()+player2Entities.get(i).getPos().getWidth()/2-20, player2Entities.get(i).getPos().getPosY(), 40, 11, player2Paddle);
										powerups.add(pwp);
									}
									player2Entities.remove(i);
									((Paddle) player2Paddle).setScore(((Paddle) player2Paddle).getScore() + 10);
									i--;
								}
							}
						}
					} else {
						it.update();
						it.colission(player2Ball);
					}
				}
			}
			
			for (int j = 0; j < powerups.size(); j++) {
				PowerUp pup = powerups.get(j);
				if (pup.isDead()) {
					powerups.remove(j);
					j--;
				} else {
					pup.update();
					pup.colission(player1Paddle);
					if (multiplayerGameStarted) {
						pup.colission(player2Paddle);
					}
				}
			}
			
			countBricksAndCheckWin();
			repaint();
		}
		if (gameOver) {
			repaint();
		}
		
	}
	
	private void countBricksAndCheckWin() {
		int player1Bricks = 0;
		int player2Bricks = 0;
		
		for (Item it : player1Entities) {
			if (it instanceof Brick) {
				player1Bricks++;
			}
		}
		
		if(player1Bricks <= 0) {
			gameOver = true;
			timeStart = null;
		}
		
		if (!player2Entities.isEmpty()) {
			for (Item it : player2Entities) {
				if (it instanceof Brick) {
					player2Bricks++;
				}
			}
			
			if(player2Bricks <= 0) {
				gameOver = true;
				timeStart = null;
			}
		}
	}
}
