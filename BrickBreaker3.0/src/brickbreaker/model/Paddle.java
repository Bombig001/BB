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
import brickbreaker.player.Players;
import brickbreaker.position.Strategy;
import brickbreaker.sound.Sound;
import brickbreaker.view.Game;

public class Paddle extends Item {
	private Game game;
	private int stateCounter = 0;
	private Image img;
	private Image state0;
	private Image state1;
	private Image state2;
	private KeyboardInput taste;
	private int speed;
	private static Sound paddleToBallSound = new Sound("/res/sounds/bouncePaddle.wav",-10.0f);
	private Players playertyp;
	private Item ball;
	private Strategy strgy;
	private boolean effectExtended;
	private boolean effectShortened;
	private boolean effectMissile;

	public Paddle(Integer x, Integer y, Integer w, Integer h, Players playertyp, Item ball, Game game, Strategy strgy) {
		super(x, y, w, h);
		this.game = game;
		this.strgy = strgy;
		effectExtended = false;
		speed = 9;
		taste = new KeyboardInput(game);
		if (playertyp == Players.PLAYER1) {
		state0  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle1state0.png")).getImage();
		state1  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle1state1.png")).getImage();
		state2  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle1state2.png")).getImage();
		} else {
			state0  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle2state0.png")).getImage();
			state1  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle2state1.png")).getImage();
			state2  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle2state2.png")).getImage();
		}
		this.playertyp = playertyp;
		this.ball = ball;
	}

	public boolean isEffectExtended() {
		return effectExtended;
	}

	public void setEffectExtended(boolean effectExtended) {
		this.effectExtended = effectExtended;
	}

	public boolean isEffectShortened() {
		return effectShortened;
	}

	public void setEffectShortened(boolean effectShortened) {
		this.effectShortened = effectShortened;
	}

	public boolean isEffectMissile() {
		return effectMissile;
	}

	public void setEffectMissile(boolean effectMissile) {
		this.effectMissile = effectMissile;
	}
	
	public void resetEffects() {
		effectExtended = false;
		effectMissile = false;
		effectShortened = false;
	}

	public static Sound getPaddleToBallSound() {
		return paddleToBallSound;
	}

	public void setPlayertyp(Players playertyp) {
		this.playertyp = playertyp;
	}
	
	public Strategy getStrgy() {
		return strgy;
	}

	@Override
	public void colission(Item i) {
		int windowwidth;
		if (GameController.getWidth().intValue() == GameController.getDefWidth().intValue()) {
			windowwidth = GameController.getWidth().intValue()/2;
		} else {
			windowwidth = GameController.getWidth().intValue();
		}
		
		
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		int x_ = i.getPos().getPosX().intValue();
		int y_ = i.getPos().getPosY().intValue();
		int w_ = i.getPos().getWidth().intValue();
		int h_ = i.getPos().getHeight().intValue();
		
		if (game.isMultiplayerGame() && playertyp == Players.PLAYER1) {
		
			if (x <= (GameController.getWidth().intValue() + 16) / 2) {
				this.getPos().setPosX((GameController.getWidth().intValue() + 16) / 2);
			}
			
			if (x+w+speed >= GameController.getDefWidth().intValue()) {
				this.getPos().setPosX(GameController.getDefWidth().intValue() - w-speed);
			}
			
		}
		if (playertyp == Players.PLAYER2 || playertyp == Players.COMPUTER || game.isSingleplayerGame()) {
			if (x <= 0) {
				this.getPos().setPosX(0);
			}
			if (x+w+speed >= windowwidth) {
				this.getPos().setPosX(windowwidth - w-speed);
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
			  }
	}
	
	@Override
	public void draw(Graphics gfx) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		if (effectMissile) {
			if (playertyp == Players.PLAYER1) {
				state0  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle1MissileState0.png")).getImage();
				state1  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle1MissileState1.png")).getImage();
				state2  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle1MissileState2.png")).getImage();
			} else {
				state0  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle2MissileState0.png")).getImage();
				state1  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle2MissileState1.png")).getImage();
				state2  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle2MissileState2.png")).getImage();
			}
		} else {
			if (playertyp == Players.PLAYER1) {
				state0  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle1state0.png")).getImage();
				state1  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle1state1.png")).getImage();
				state2  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle1state2.png")).getImage();
			} else {
				state0  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle2state0.png")).getImage();
				state1  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle2state1.png")).getImage();
				state2  = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle2state2.png")).getImage();
			}
		}
		
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
		if (playertyp == Players.PLAYER1) {
			taste.tasteGedrückt(KeyEvent.VK_LEFT,"player1Links", (evt) -> {
				this.setVelX(-speed);
			});
			taste.tasteLosgelassen(KeyEvent.VK_LEFT,"player1LinksStop", (evt) -> {
				this.setVelX(0);
			});
			
			taste.tasteGedrückt(KeyEvent.VK_RIGHT,"player1Rechts", (evt) -> {
				this.setVelX(speed);
			});
			taste.tasteLosgelassen(KeyEvent.VK_RIGHT,"player1RechtsStop", (evt) -> {
				this.setVelX(0);
			});
			taste.tasteLosgelassen(KeyEvent.VK_ENTER,"player1BallGainSpeed", (evt) -> {
				if (((Ball) ball).isBallStoped()) {
					((Ball) ball).setBallStoped(false);
					((Ball) ball).setSpeed(((Ball) ball).getDefSpeed());
					((Ball) ball).initVelocity();
				}
			});
		} else if (playertyp == Players.PLAYER2) {
			taste.tasteGedrückt(KeyEvent.VK_A,"player2Links", (evt) -> {
				this.setVelX(-speed);
			});
			taste.tasteLosgelassen(KeyEvent.VK_A,"player2LinksStop", (evt) -> {
				this.setVelX(0);
			});
			
			taste.tasteGedrückt(KeyEvent.VK_D,"player2Rechts", (evt) -> {
				this.setVelX(speed);
			});
			taste.tasteLosgelassen(KeyEvent.VK_D,"player2RechtsStop", (evt) -> {
				this.setVelX(0);
			});
			
			taste.tasteLosgelassen(KeyEvent.VK_SPACE,"player2BallGainSpeed", (evt) -> {
				if (((Ball) ball).isBallStoped()) {
					((Ball) ball).setBallStoped(false);
					((Ball) ball).setSpeed(((Ball) ball).getDefSpeed());
					((Ball) ball).initVelocity();
				}
			});
		} else if (playertyp == Players.COMPUTER) {
			if (strgy.getRandomChance()) {
				if (((Ball) ball).isBallStoped()) {
					if (this.getPos().getPosX() <= ((int)GameController.getDefWidth().intValue()/4) && this.getPos().getPosX() >= ((int)GameController.getDefWidth().intValue()/10)) {
						((Ball) ball).setBallStoped(false);
						((Ball) ball).setSpeed(((Ball) ball).getDefSpeed());
						((Ball) ball).initVelocity();
					} else if (this.getPos().getPosX() < 300){
						this.setVelX(speed);
					} else {
						this.setVelX(-speed);
					}
				} else if (!((Ball) ball).isBallStoped() && ((Ball) ball).getSpeed() != 0) {
					if (ball.getPos().getPosX() > (this.getPos().getPosX() + this.getPos().getHeight().intValue())) {
						this.setVelX(speed);
					} else {
						this.setVelX(-speed);
					}
				}
			} else {
				this.setVelX(0);
			}
		}
		
		// damit ball immer auf paddle bleibt
		if (((Ball) ball).isBallStoped()) {
			int x = this.getPos().getPosX().intValue();
			int y = this.getPos().getPosY().intValue();
			int w = this.getPos().getWidth().intValue();
			int h = this.getPos().getHeight().intValue();
			ball.setPos(x+w/2, y-h);
			
		}
		this.getPos().setPosX(this.getPos().getPosX() + getVelX());
	}

}
