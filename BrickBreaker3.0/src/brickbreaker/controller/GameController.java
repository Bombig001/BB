package brickbreaker.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import brickbreaker.view.Game;
import brickbreaker.view.Menu;

public class GameController {
	private static float gameVersion = 4.7f;
	private static Game game;
	private static JFrame window;
	private static Menu mainMenu;
	private static Integer defWidth;
	private static Integer width;
	private static Integer height;
	
	public static void main(String[] args) {
		window = new JFrame("Brick Breaker v"+gameVersion);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		defWidth = (int)screenSize.getWidth();
		width = (int)(screenSize.getWidth() -16) / 2;
		height = 720;
		game = new Game(window);
		mainMenu = new Menu(game);
		window.setSize(width, height);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.add(game);
		mainMenu.start();
		window.setVisible(true);
		Timer t = new Timer(15,game);
		t.start();
	}

	public static float getGameVersion() {
		return gameVersion;
	}

	public static JFrame getWindow() {
		return window;
	}

	public static Menu getMainMenu() {
		return mainMenu;
	}

	public static Integer getDefWidth() {
		return defWidth;
	}

	public static Integer getWidth() {
		return width;
	}

	public static void setWidth(Integer width) {
		GameController.width = width;
	}

	public static Integer getHeight() {
		return height;
	}

	public static void setHeight(Integer height) {
		GameController.height = height;
	}

}