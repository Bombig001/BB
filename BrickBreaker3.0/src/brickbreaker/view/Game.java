package brickbreaker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
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
import brickbreaker.position.Position;
import brickbreaker.powerup.EnlargePaddle;
import brickbreaker.powerup.PowerUp;
import brickbreaker.sound.Sound;

public class Game extends JComponent implements ActionListener, MouseMotionListener{
	private Graphics gfx;
	private Level currentLevel;
	private ArrayList<Item> entities;
	private ArrayList<PowerUp> powerups;
	private Item p1 = new Paddle(this,GameController.spieler1,400, 650, 97, 26);
	private Item b1 = new Ball(260, 100, 18, 18,0);
	private Position btn1 = new Position(350,300,200,40);
	Image background;
	Image background2;
	private boolean gameOver;
	public static boolean gameStarted = false;
	private int points = 0;
	private Menu mainMenu;
	private Random rand;
	
	public Game(JFrame window) {
//		mainMenu = new Menu(this, true);
//		window.addKeyListener((KeyListener) p1);
//		window.addMouseMotionListener(this);
		background  = new ImageIcon(this.getClass().getResource("/res/images/background/1.jpg")).getImage();
		background2 = new ImageIcon(this.getClass().getResource("/res/images/background/3.jpg")).getImage();
		currentLevel = new Level(1);
		entities = new ArrayList<Item>();
		powerups = new ArrayList<PowerUp>();
		entities = currentLevel.getBrickList();
		entities.add(b1);
		entities.add(p1);
		mainMenu = new Menu(this);
		rand = new Random();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
		gfx = g;
		if(gameStarted) {
			//this.removeAll();
			gfx.drawImage(background, 0, 0, GameController.windowWidth, GameController.windowHeight, null);
			gfx.drawImage(background2,0, 0, GameController.windowWidth, 50,null);
			gfx.setColor(Color.lightGray);
			gfx.setFont(new Font("Times", Font.BOLD, 17));
			gfx.drawString("Punktzahl: "+points, 0, 20);
			gfx.drawString("Spielername: \n"+ GameController.spieler1, 500, 20);
			gameUpdate();
			if (entities.isEmpty()) {
				gameOver = true;
			}
		} else {
		}
	}
	
	private void gameUpdate() {
		for(Item it : entities) {
			it.draw(gfx);
		}
		
		for (PowerUp pu : powerups) {
			pu.draw(gfx);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(!gameOver && gameStarted) {
			for (int i = 0; i < entities.size(); i++) {
				Item it = entities.get(i);
				if ( it instanceof Brick) {
					if (!((Brick) it).getIsSmashed()) {
						if(((Brick) it).colission(b1)) {
							((Brick) it).dealDamage();
							if (((Brick) it).getIsSmashed()) {
								if(rand.nextInt(2) == 1) {
									PowerUp pwp = new EnlargePaddle(entities.get(i).getPos().getPosX()+entities.get(i).getPos().getWidth()/2-20, entities.get(i).getPos().getPosY(), 40, 11, p1);
									powerups.add(pwp);
								}
								entities.remove(i);
								points += 10;
								i--;
							}
						}
					}
				} else {
					it.update();
					it.colission(b1);
				}
			}
			for (int j = 0; j < powerups.size(); j++) {
				PowerUp pup = powerups.get(j);
				if (pup.isDead()) {
					powerups.remove(j);
					j--;
				} else {
					pup.update();
					pup.colission(p1);
				}
			}
			countBricksAndCheckWin();
			repaint();
		}
	}
	
	private void countBricksAndCheckWin() {
		int bricks = 0;
		
		for (Item it : entities) {
			if (it instanceof Brick) {
				bricks++;
			}
		}
		
		if(bricks <= 0) {
			gameOver = true;
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		p1.setPos(e.getX(), e.getY());
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
