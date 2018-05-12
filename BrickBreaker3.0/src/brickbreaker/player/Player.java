package brickbreaker.player;

import java.util.ArrayList;

import javax.swing.JComponent;

import brickbreaker.level.Level;
import brickbreaker.model.Ball;
import brickbreaker.model.Item;
import brickbreaker.model.Paddle;

public class Player {
	private static String name;
	private static int difficultyLevel;
	Players playertyp;
	private Level CurrentLevel;
	private ArrayList<Item> entities;
	private Item ball;
	private Item paddle;
	
	public Player(JComponent game, Players playertyp) {
		this.playertyp = playertyp;
		name = "";
		difficultyLevel = 0;
		CurrentLevel = new Level();
		entities = new ArrayList<Item>();
		ball = new Ball(260, 100, 18, 18,0, Players.PLAYER1);
		paddle = new Paddle(game,name,300, 650, 97, 26, playertyp,ball);
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Player.name = name;
	}

	public static int getDifficultyLevel() {
		return difficultyLevel;
	}

	public static void setDifficultyLevel(int difficultyLevel) {
		Player.difficultyLevel = difficultyLevel;
	}

	public ArrayList<Item> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Item> entities) {
		this.entities = entities;
	}

	public Players getPlayertyp() {
		return playertyp;
	}

	public void setPlayertyp(Players playertyp) {
		this.playertyp = playertyp;
	}

	public Level getCurrentLevel() {
		return CurrentLevel;
	}

}

