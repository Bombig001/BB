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
import brickbreaker.player.Player;
import brickbreaker.player.Players;
import brickbreaker.powerup.PaddleExtended;
import brickbreaker.powerup.PowerUp;

public class Game extends JComponent implements ActionListener {
	public static boolean singleplayerGameStarted = false;
	public static boolean multiplayerGameStarted = false;
	public static Player player1;
	public static Player player2;
	public static int level = 1;
	public static Graphics gfx;
	Image background;
	Image background2;
	Image splitter;
	Image p1winns;
	Image p2winns;
	private boolean gameOver;
	private Menu mainMenu;
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
		mainMenu.start();
		background  = new ImageIcon(this.getClass().getResource("/res/images/background/1.jpg")).getImage();
		background2 = new ImageIcon(this.getClass().getResource("/res/images/background/3.jpg")).getImage();
		splitter = new ImageIcon(this.getClass().getResource("/res/images/background/splitter.jpg")).getImage();
		p1winns = new ImageIcon(this.getClass().getResource("/res/images/player1winns.png")).getImage();
		p2winns = new ImageIcon(this.getClass().getResource("/res/images/player2winns.png")).getImage();
		player1 = new Player(this,Players.PLAYER1);
		player2 = new Player(this,Players.PLAYER2);
		setupSingleplayerMode();
	}
	
	private void gameOverScreen() {
		timeStop = Instant.now();
		timePastBetween = Duration.between(timeStart, timeStop);
		
		if (multiplayerGameStarted) {
			if (player1.getScore() > player2.getScore()) {
				gfx.drawImage(player1.getPlayerwinns(), GameController.windowWidth/4, 300, null);
			} else if (player1.getScore() < player2.getScore()) {
				gfx.drawImage(player2.getPlayerwinns(), GameController.windowWidth/4, 300, null);			
			}
			
			if (timePastBetween.getSeconds() >= 5) {
				player1.resetEffects();
				player2.resetEffects();
				setupMultiplayerMode();
				gameOver = false;
			}
		} else {
			gfx.drawImage(p1winns, 35, 300, null);
			if (timePastBetween.getSeconds() >= 5) { 
				player1.resetEffects();
				setupSingleplayerMode();
				gameOver = false;
			}
		}
	}
	
	private void setupSingleplayerMode() {
		player1.getCurrentLevel().setCurrentLevel(Math.min(level, player1.getCurrentLevel().getMaxLevel()));
		player1.loadUpStage();
	}
	
	private void setupMultiplayerMode() {
		setupSingleplayerMode();
		
		GameController.windowWidth = 1456;
		window.setSize(GameController.windowWidth, GameController.windowHeight);
		window.setLocationRelativeTo(null);
		
		
		player2.getCurrentLevel().setCurrentLevel(Math.min(level, player2.getCurrentLevel().getMaxLevel()));
		player2.loadUpStage();
		for(Item it : player1.getEntities()) {
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
			gfx.drawImage(background, 0, 0, GameController.windowWidth, GameController.windowHeight, null);
			gfx.drawImage(background2,0, 0, GameController.windowWidth, 50,null);
			gfx.setColor(Color.lightGray);
			gfx.setFont(new Font("Times", Font.BOLD, 17));
			gfx.drawString("Punktzahl: "+ player1.getScore(), 0, 20);
			gfx.drawString("Spielername: \n"+ player1.getName(), 500, 20);
		} else if (multiplayerGameStarted) {
			if (GameController.windowWidth <= 720) {
				setupMultiplayerMode();
			}
			gfx.drawImage(background, 0, 0, GameController.windowWidth/2-8, GameController.windowHeight, null);
			gfx.drawImage(background2,0, 0, GameController.windowWidth/2-8, 50,null);
			gfx.setColor(Color.lightGray);
			gfx.setFont(new Font("Times", Font.BOLD, 17));
			gfx.drawString("Punktzahl: "+ player2.getScore(), 0, 20);
			gfx.drawString("Spieler 2: \n"+ player2.getName(), 500, 20);
			
			gfx.drawImage(splitter,GameController.windowWidth/2-8, 0, 16, GameController.windowHeight,null);
			
			gfx.drawImage(background, GameController.windowWidth/2+8, 0, GameController.windowWidth/2-8, GameController.windowHeight, null);
			gfx.drawImage(background2,GameController.windowWidth/2+8, 0, GameController.windowWidth/2-8, 50,null);
			gfx.setColor(Color.lightGray);
			gfx.setFont(new Font("Times", Font.BOLD, 17));
			gfx.drawString("Punktzahl: "+ player1.getScore(), GameController.windowWidth/2+8, 20);
			gfx.drawString("Spieler 1: \n"+ player1.getName(), GameController.windowWidth/2+8 + 500, 20);
			
		}
		gameDraw();
	}
	
	private void gameDraw() {
		
		if (singleplayerGameStarted || multiplayerGameStarted) {
			
			if (multiplayerGameStarted) {
				for(Item it : player2.getEntities()) {
					it.draw(gfx);
				}
				
				for (PowerUp pu : player2.getPowerups()) {
					pu.draw(gfx);
				}
			}
			
			for(Item it : player1.getEntities()) {
				it.draw(gfx);
			}
			
			for (PowerUp pu : player1.getPowerups()) {
				pu.draw(gfx);
			}
		}
		
		if (gameOver) {
			if (timeStart == null) {
				timeStart = Instant.now();
				level += 1;
			}
			gameOverScreen();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(!gameOver && (singleplayerGameStarted || multiplayerGameStarted)) {
			
			player1.updateEntities();
			
			if (multiplayerGameStarted) {
				player2.updateEntities();
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
		
		for (Item it : /*player1Entities*/player1.getEntities()) {
			if (it instanceof Brick) {
				player1Bricks++;
			}
		}
		
		if(player1Bricks <= 0) {
			gameOver = true;
			timeStart = null;
		}
		
		if (!player2.getEntities().isEmpty()) {
			for (Item it : player2.getEntities()) {
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
