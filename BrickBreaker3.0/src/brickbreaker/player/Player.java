package brickbreaker.player;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import brickbreaker.level.Level;
import brickbreaker.model.Ball;
import brickbreaker.model.Brick;
import brickbreaker.model.Item;
import brickbreaker.model.Paddle;
import brickbreaker.powerup.PaddleExtended;
import brickbreaker.powerup.PaddleMissile;
import brickbreaker.powerup.PaddleShortened;
import brickbreaker.powerup.BallFast;
import brickbreaker.powerup.BallMelting;
import brickbreaker.powerup.BallSlow;
import brickbreaker.powerup.PowerUp;

public class Player {
	private String name;
	private int difficultyLevel;
	Players playertyp;
	private Level currentLevel;
	private ArrayList<Item> entities;
	private ArrayList<PowerUp> powerups;
	private Item ball;
	private Item paddle;
	private int score;
	Image playerwinns;
	private Random rand;
	
	public Player(JComponent game, Players playertyp) {
		this.playertyp = playertyp;
		if (playertyp == Players.PLAYER1) {
			playerwinns = new ImageIcon(this.getClass().getResource("/res/images/player1winns.png")).getImage();
		} else if (playertyp == Players.PLAYER2) {
			playerwinns = new ImageIcon(this.getClass().getResource("/res/images/player2winns.png")).getImage();
		} else {
			playerwinns = new ImageIcon(this.getClass().getResource("/res/images/verloren.png")).getImage();
		}
		name = "";
		difficultyLevel = 0;
		currentLevel = new Level();
		entities = new ArrayList<Item>();
		powerups = new ArrayList<PowerUp>();
		ball = new Ball(260, 100, 18, 18,0, playertyp);
		paddle = new Paddle(game,name,300, 660, 97, 26, playertyp,ball);
		score = 0;
		rand = new Random();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public ArrayList<Item> getEntities() {
		return entities;
	}

	public void loadUpStage() {
		entities.clear();
		powerups.clear();
		score = 0;
		entities = currentLevel.getBrickList();
		ball.setPos(300, 500);
		((Ball) ball).setBallStoped(true);
//		((Ball) ball).respawn();
		entities.add(ball);
		entities.add(paddle);
	}

	public Players getPlayertyp() {
		return playertyp;
	}
	
	public void resetEffects() {
		((Ball)ball).resetEffects();
		((Paddle)paddle).resetEffects();
	}

	public void setPlayertyp(Players playertyp) {
		this.playertyp = playertyp;
		((Paddle) paddle).setPlayertyp(playertyp);
		((Ball) ball).setPlayertyp(playertyp);
		
		if (playertyp == Players.PLAYER1) {
			playerwinns = new ImageIcon(this.getClass().getResource("/res/images/player1winns.png")).getImage();
		} else if (playertyp == Players.PLAYER2) {
			playerwinns = new ImageIcon(this.getClass().getResource("/res/images/player2winns.png")).getImage();
		} else {
			playerwinns = new ImageIcon(this.getClass().getResource("/res/images/verloren.png")).getImage();
		}
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void updateEntities() {
		for (int i = 0; i < entities.size(); i++) {
			Item it = entities.get(i);
			if ( it instanceof Brick) {
				if (!((Brick) it).getIsSmashed()) {
					it.colission(ball);
					if (((Brick) it).getIsSmashed()) {
						if(rand.nextInt(2) == 1) {
							PowerUp pwp = new PaddleMissile(entities.get(i).getPos().getPosX(), entities.get(i).getPos().getPosY(), paddle,entities);
							powerups.add(pwp);
						}
						entities.remove(i);
						score += 10;
						i--;
					}
				}
			} else {
				it.update();
				it.colission(ball);
			}
		}
		
		for (int j = 0; j < powerups.size(); j++) {
		PowerUp pup = powerups.get(j);
			if (pup.isDead()) {
				powerups.remove(j);
				j--;
			} else {
				pup.update();
				pup.colission(paddle);
			}
		}
	}

	public ArrayList<PowerUp> getPowerups() {
		return powerups;
	}

	public Image getPlayerwinns() {
		return playerwinns;
	}
	
}

