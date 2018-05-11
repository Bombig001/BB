package brickbreaker.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import brickbreaker.controller.GameController;
import brickbreaker.position.Strategy;
import brickbreaker.sound.Sound;
import brickbreaker.view.Game;

public class Paddle extends Item {
	private String name;
	private Integer score;
	private int stateCounter = 0;
	private Image img;
	private Image state0;
	private Image state1;
	private Image state2;
	private TastaturEingabe tast;
	private boolean extendedEffect;
	private int speed;
	private static Sound paddleToBallSound;
	private Players player;
	private Item ball;
	private Strategy strgy;

	public Paddle(JComponent game,String name, Integer x, Integer y, Integer w, Integer h, Players player, Item ball) {
		super(x, y, w, h, 3);
		extendedEffect = false;
		this.name = name;
		score = 0;
		speed = 7;
		tast = new TastaturEingabe(game);
		state0  = new ImageIcon(this.getClass().getResource("/res/images/paddle/state0.png")).getImage();
		state1  = new ImageIcon(this.getClass().getResource("/res/images/paddle/state1.png")).getImage();
		state2  = new ImageIcon(this.getClass().getResource("/res/images/paddle/state2.png")).getImage();
		paddleToBallSound = new Sound("/res/sounds/bouncePaddle.wav",-10.0f);
		this.player = player;
		this.ball = ball;
	}
	
	public Integer getScore() {
		return score;
	}



	public void setScore(Integer score) {
		this.score = score;
	}

	public static Sound getPaddleToBallSound() {
		return paddleToBallSound;
	}
	
	
	public boolean isExtendedEffect() {
		return extendedEffect;
	}

	public void setExtendedEffect(boolean extendedEffect) {
		this.extendedEffect = extendedEffect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean colission(Item i) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		int x_ = i.getPos().getPosX().intValue();
		int y_ = i.getPos().getPosY().intValue();
		int w_ = i.getPos().getWidth().intValue();
		int h_ = i.getPos().getHeight().intValue();
		
		if (Game.multiplayerGameStarted && player == Players.PLAYER1) {
		
			if (x <= GameController.windowWidth/2+8) {
				this.getPos().setPosX(GameController.windowWidth/2+8);
			}
			
			if (x+w+speed >= GameController.windowWidth) {
				this.getPos().setPosX(GameController.windowWidth - w-speed);
			}
			
		}
		if (player == Players.PLAYER2 || player == Players.COMPUTER || Game.singleplayerGameStarted) {
			if (x <= 0) {
				this.getPos().setPosX(0);
			}
			
			if (x+w+speed >= Math.max(720,GameController.windowWidth/2)) {
				this.getPos().setPosX(Math.max(720,GameController.windowWidth/2) - w-speed);
			}
		}
		
		// colission test
		  if (x_+w_ >= x && x_ <= x+w && y_+h_ >= y && y_ <= y+h){
			    if (y_ >= y) {
			    	i.setVelY(((Ball) i).getSpeed());
			    }else{
			    	i.setVelY(-((Ball) i).getSpeed());
			    }
			    if(x_+w_ <= x+10) {
			    	i.setVelX(-((Ball) i).getSpeed());
			    } else if(x_ >= x+w-10) {
			    	i.setVelX(((Ball) i).getSpeed());
			    }
			    paddleToBallSound.start();
			    return true;
			  }
		
		return false;
	}
	
	@Override
	public void draw(Graphics gfx) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		if (stateCounter <= 10) {
			img = state0;
		} else if (stateCounter <= 20) {
			img = state1;
		} else if (stateCounter <= 30) {
			img = state2;
		} else if (stateCounter <= 40) {
			stateCounter = 0;
		}
		gfx.drawImage(img, x, y, w, h, null);
		stateCounter++;
	}
	
	@Override
	public void update() {
		if (player == Players.PLAYER1) {
			tast.tasteGedrückt(KeyEvent.VK_LEFT,"linksPlayer1", (evt) -> {
				this.setVelX(-speed);
			});
			tast.tasteLosgelassen(KeyEvent.VK_LEFT,"linksStopPlayer1", (evt) -> {
				this.setVelX(0);
			});
			
			tast.tasteGedrückt(KeyEvent.VK_RIGHT,"rechtsPlayer1", (evt) -> {
				this.setVelX(speed);
			});
			tast.tasteLosgelassen(KeyEvent.VK_RIGHT,"rechtsStopPlayer1", (evt) -> {
				this.setVelX(0);
			});
		} else if (player == Players.PLAYER2) {
			tast.tasteGedrückt(KeyEvent.VK_A,"linksPlayer2", (evt) -> {
				this.setVelX(-speed);
			});
			tast.tasteLosgelassen(KeyEvent.VK_A,"linksStopPlayer2", (evt) -> {
				this.setVelX(0);
			});
			
			tast.tasteGedrückt(KeyEvent.VK_D,"rechtsPlayer2", (evt) -> {
				this.setVelX(speed);
			});
			tast.tasteLosgelassen(KeyEvent.VK_D,"rechtsStopPlayer2", (evt) -> {
				this.setVelX(0);
			});
		} else if (player == Players.COMPUTER) {
			if (strgy != null) {
				if (!strgy.getRandomChance()) {
					if (ball.getPos().getPosX() > (this.getPos().getPosX() + this.getPos().getHeight().intValue())) {
						this.setVelX(speed);
					} else {
						this.setVelX(-speed);
					}
				} else {
					this.setVelX(0);
				}
			} else {
				strgy = new Strategy(Game.schwierigkeitsgrad);
			}
		}
		this.getPos().setPosX(this.getPos().getPosX() + getVelX());
	}

}
